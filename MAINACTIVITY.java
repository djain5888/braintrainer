package com.example.deepakjain.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    int flag=1;
    TextView score;
    Button b1;
    Button play_again;
    Button option_1;
    Button option_2;
    Button option_3;
    Button option_4;
    TextView time;
    TextView ques;
    TextView display;
    GridLayout g1;
    ArrayList<Integer> a1;
    int Score=0;
    int totalques=0;
    int location_of_correct_answer;

    public void start(View view)
    {

        b1.setVisibility(View.INVISIBLE);
        play(b1);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a1=new ArrayList(4);
        g1=findViewById(R.id.gridLayout);
        b1=findViewById(R.id.button);
        ques=findViewById(R.id.textView);
        option_1=findViewById(R.id.button01);
        option_2=findViewById(R.id.button02);
        option_3=findViewById(R.id.button03);
        option_4=findViewById(R.id.button04);
        display=findViewById(R.id.textView4);
        time=findViewById(R.id.textView2);
        score=findViewById(R.id.textView3);
        play_again=findViewById(R.id.playagain);

        display.setText("HELLO");
        ques.setVisibility(View.INVISIBLE);
        g1.setVisibility(View.INVISIBLE);
        time.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);






    }
    public void evaluate(View view) {

            String a = (String) view.getTag();
            String check = Integer.toString(location_of_correct_answer);
            if (a.equals(check)) {
                display.setText("Correct :)");
                Score++;
            } else {
                display.setText("wrong :(");
            }
            totalques++;
            score.setText(Integer.toString(Score) + "/" + Integer.toString(totalques));
            generate();
        }

    public void generate()
    {
        display.setText("START!");
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        ques.setText("QUES:"+Integer.toString(a)+"+"+Integer.toString(b));
        location_of_correct_answer=rand.nextInt(4);

        for(int i=0;i<4;i++)
        {
            if(i==location_of_correct_answer)
            {
                a1.add(i,a+b);
            }
            else
            {
                int incorrect_answer=rand.nextInt(42);
                while(incorrect_answer==(a+b))
                {
                    incorrect_answer=rand.nextInt(42);
                }
                a1.add(i,incorrect_answer);
            }


        }
        option_1.setText((a1.get(0)).toString());
        option_2.setText(a1.get(1).toString());
        option_3.setText(a1.get(2).toString());
        option_4.setText(a1.get(3).toString());


    }
    public void play(View view)
    {
        Score=0;
        totalques=0;
        time.setText("30s");
       ques.setVisibility(View.VISIBLE);
        g1.setVisibility(View.VISIBLE);
        time.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        display.setText("HELLO");
        score.setText(Integer.toString(Score) + "/" + Integer.toString(totalques));
        play_again.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                time.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                play_again.setVisibility(View.VISIBLE);
                display.setText("DONE your score is "+Integer.toString(Score)+"/"+Integer.toString(totalques));
                ques.setVisibility(View.INVISIBLE);
                g1.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);

            }
        }.start();
        generate();
    }

}
