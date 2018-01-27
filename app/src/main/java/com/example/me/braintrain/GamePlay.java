package com.example.me.braintrain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Me on 11/22/2017.
 */

/*
Timer loop that updates TimerView.
Randomizing Method.
ProblemView - Displays two random numbers to be added together
ScoreKeeperView - Keeps track of how many right out of total problems attempted
GridView - With 4 Text Views - One view will have correct answer - 3 will be random.
    Placement of correct answer needs to be Random.
checkAnswer method - will compare value of imageview with correct answer.

Random Number generated for each text view after each new question.  One of four views will randomly get the
correct answer over written.

 */



public class GamePlay extends Activity {

TextView timerView;
TextView textView0;
TextView textView1;
TextView textView2;
TextView textView3;
TextView testView;
TextView problemView;
TextView scoreView;
TextView answerView;

int numberA = 0;
int numberB = 0;
int answer = 0;
int problemCorrectCount = 0;
int correctView = 0;
int questionCount = 0;

MediaPlayer mPlayer2;

Random rand = new Random();

CountDownTimer gameClock;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_screen);

        gameClock = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                long seconds = l / 1000;
                if (seconds < 10) {
                    timerView.setText("00:0" + l / 1000);
                } else {
                    timerView.setText("00:" + l / 1000);
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(GamePlay.this,GameOver.class);
                intent.putExtra("problemCorrectCount", problemCorrectCount);
                intent.putExtra("questionCount", questionCount);
                startActivity(intent);
            }
        };

    }

    public void startGame(View view) {
        timerView = (TextView) findViewById(R.id.timerView);
        textView0 = (TextView) findViewById(R.id.textView0);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        problemView = (TextView) findViewById(R.id.problemView);
        scoreView = (TextView) findViewById(R.id.scoreView);
        answerView = (TextView) findViewById(R.id.answerView);

        gameClock.start();
        nextQuestion(view);
        answerView.setText(" ");


    }

    public void nextQuestion(View view2){

        textView0.setText(Integer.toString(generateNumber()));
        textView1.setText(Integer.toString(generateNumber()));
        textView2.setText(Integer.toString(generateNumber()));
        textView3.setText(Integer.toString(generateNumber()));
        numberA = generateNumber();
        numberB = generateNumber();
        answer = (numberA + numberB);

        problemView.setText(numberA + " + " + numberB);

        int m = rand.nextInt(3);
        correctView = m;

        List<TextView> tv = new ArrayList<TextView>();
        tv.add(textView0);
        tv.add(textView1);
        tv.add(textView2);
        tv.add(textView3);

        tv.get(m).setText(Integer.toString(answer));



    }


    // When an answer(textView) is selected
    public void selection(View view) {

        TextView selection = (TextView) view;

        int clickedImage = Integer.parseInt(selection.getTag().toString());

        if (clickedImage == correctView) {
            playCorrect();
            problemCorrectCount++;
            questionCount++;
            scoreView.setText(problemCorrectCount + "/" + questionCount);
            nextQuestion(view);
            answerView.setText("Correct!!!");
        } else {
            playWrong();
            questionCount++;
            scoreView.setText(problemCorrectCount + "/" + questionCount);
            nextQuestion(view);
            answerView.setText("Math is Hard!");
        }

    }

    public int generateNumber() {

        int  n = rand.nextInt(49) + 1;
        return n;
    }

    private void playCorrect()
    {
        mPlayer2 = MediaPlayer.create(this, R.raw.correct);
        mPlayer2.start();
    }

    private void playWrong()
    {
        mPlayer2 = MediaPlayer.create(this, R.raw.wrong);
        mPlayer2.start();
    }

}
