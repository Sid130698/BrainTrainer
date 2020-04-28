package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView goButton,appLogo;
    Button button0,button1,button2,button3,playAgainButton;
    TextView timerTextView,scoreTextView,ResultsTextView,questionTextView;
    RelativeLayout gameRelativeLayout;
    ArrayList <Integer> answers=new ArrayList<Integer>();
    int locationOfCorrectAnswer,score=0,noOfQuestion=0;
    MediaPlayer mediaPlayer;
    public void  startGame(){
        goButton.setVisibility(View.INVISIBLE);
        appLogo.setVisibility(View.VISIBLE);
        appLogo.animate().alpha(0f).setDuration(2000);
        showAnimation();

    }

    private void showAnimation() {

        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        PlayAgain(findViewById(R.id.playAgainButton));
    }

    public void chooseAnswer(View view) {
        String chosenAnswer;
        chosenAnswer = view.getTag().toString();
        int chosAns = Integer.parseInt(chosenAnswer);

        if (playAgainButton.getVisibility() == View.INVISIBLE) {
            if (chosAns == locationOfCorrectAnswer) {
                score++;
                ResultsTextView.setText("correct Answer!");

            } else {
                ResultsTextView.setText("Wrong Answer!");
            }
            noOfQuestion++;
            scoreTextView.setText(score + "/" + noOfQuestion);
            generateQuestion();

        }
    }
    public void PlayAgain(View view){
        mediaPlayer=MediaPlayer.create(this,R.raw.coffindance);
        mediaPlayer.start();
        score=0;
        noOfQuestion=0;
        timerTextView.setText("30 sec");
        scoreTextView.setText("0/0");
        ResultsTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"sec");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                playAgainButton.setText("Play again");
                timerTextView.setText("0 sec");
                ResultsTextView.setText("Your Score is :"+Integer.toString(score) +"/"+Integer.toString(noOfQuestion));

            }
        }.start();


    }

    private void generateQuestion() {
        if (playAgainButton.getVisibility() == View.INVISIBLE) {
            Random rand = new Random();
            int a, b, incorrectAnswer;
            a = rand.nextInt(21);
            b = rand.nextInt(21);
            questionTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
            locationOfCorrectAnswer = rand.nextInt(4);
            answers.clear();
            for (int i = 0; i < 4; i++) {
                if (i == locationOfCorrectAnswer) {
                    answers.add(a + b);
                } else {
                    incorrectAnswer = rand.nextInt(41);
                    while (incorrectAnswer == a + b) {
                        incorrectAnswer = rand.nextInt(41);
                    }
                    answers.add(incorrectAnswer);


                }
            }
            button0.setText(Integer.toString(answers.get(0)));
            button1.setText(Integer.toString(answers.get(1)));
            button2.setText(Integer.toString(answers.get(2)));
            button3.setText(Integer.toString(answers.get(3)));

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitField();
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();

            }
        });
    }

    private void InitField() {
        goButton=(ImageView) findViewById(R.id.goButtonImageView);
        appLogo=(ImageView)findViewById(R.id.logoImageView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        gameRelativeLayout=findViewById(R.id.gameRelativeLayout);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timerTextView);
        ResultsTextView=findViewById(R.id.answerStatusTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        questionTextView=findViewById(R.id.questionTextView);


    }
}
