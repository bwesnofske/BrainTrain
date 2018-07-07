package com.braintrain.me.braintrain;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;
import java.util.Random;




/**
 * Created by Me on 2/12/2018.
 */


// Problem with two image view so far is the images don't stay alpha 0
// Two Image Views.
    // animate out alpha to 0
        //set visability to gone
    // animate in alpha to 1
        //set visability to visable
    // animateIn with no setfillafter.


//could try to load image off screen in view.  See if that helps

    // Might be able to fromXDelta then set Alpha to Zero.  Seems to cause animation to come XDelta distance, to starting location.

public class PhaseGamePlay extends Activity {

    List<Image> phases;

    ImageView outView;
    ImageView titleView;
    ImageView moonView0;
    ImageView moonView1;

    TextView sameView;
    TextView differentView;
    TextView answerView;
    TextView answerView2;
    TextView scoreView;
    TextView timerView;

    TextView gameStart;

    int[] myImageList;

    int previousPhase;
    int currentPhase;
    int imageViewTurn;
    int problemCorrectCount;
    int correctView = 0;
    int questionCount = 0;

    Random random = new Random();

    String phaseCompare;
    String answer;
    String gameName;

    boolean firstRound;

    MediaPlayer mPlayer;

    Animation animationIn;
    Animation animationOut;
    Animation animationReset;

    CountDownTimer gameClock;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        gameClock = new CountDownTimer(20000,1000) {

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
                Intent intent = new Intent(PhaseGamePlay.this, GameOver.class);
                intent.putExtra("problemCorrectCount", problemCorrectCount);
                intent.putExtra("questionCount", questionCount);
                intent.putExtra("gameName", gameName);
                startActivity(intent);
            }
        };


        problemCorrectCount = 0;
        imageViewTurn = 0;
        firstRound = true;
        gameName = "phaseout";

        super.onCreate(savedInstanceState);

        setContentView(R.layout.phase_out_screen);

        animationIn = AnimationUtils.loadAnimation(this,R.anim.animatein);
        //animationIn.setFillAfter(true);
        animationOut = AnimationUtils.loadAnimation(this,R.anim.animateout);
        //animationOut.setFillAfter(true);
        animationReset = AnimationUtils.loadAnimation(this,R.anim.animationreset);
        animationReset.setFillAfter(true);

        myImageList = new int[]{
                R.drawable.moonphase1, R.drawable.moonphase2, R.drawable.moonphase4, R.drawable.moonphase6, R.drawable.moonphase7};


        outView = (ImageView) findViewById(R.id.outView);
        titleView = (ImageView) findViewById(R.id.titleView);
        sameView = (TextView) findViewById(R.id.sameView);
        differentView = (TextView) findViewById(R.id.differentView);
        answerView = (TextView) findViewById(R.id.answerView);
        answerView2 = (TextView) findViewById(R.id.answerView2);
        moonView0 = (ImageView) findViewById(R.id.moonView0);
        moonView1 = (ImageView) findViewById(R.id.moonView1);
        scoreView = (TextView) findViewById(R.id.scoreView);

        Log.i("moonView1", ("MoonView 0 position setup " + moonView1.getX()));

        outView.bringToFront();

        sameView.setTranslationX(-2000f);
        differentView.setTranslationX(2000f);

        moonView1.setAnimation(animationReset);

        Glide.with(this)
                .load(R.drawable.phase)
                .into(titleView);


        Glide.with(this)
                .load(R.drawable.outaltered)
                .into(outView);

    }


    public void gameStart(View view){

        playPhaser();

        outView = (ImageView) findViewById(R.id.outView);
        titleView = (ImageView) findViewById(R.id.titleView);
        sameView = (TextView) findViewById(R.id.sameView);
        differentView = (TextView) findViewById(R.id.differentView);
        answerView = (TextView) findViewById(R.id.answerView);
        timerView = (TextView) findViewById(R.id.timerView);

        answerView.setVisibility(View.GONE);
        answerView2.setVisibility(View.VISIBLE);

        outView.animate().translationXBy(-2000);
        titleView.animate().translationXBy(2000);

        Log.i("moonView1", ("MoonView 1 position setup " + moonView0.getX()));

        answerView2.setText("Remember This Image! Click Here To Begin");

        currentPhase = random.nextInt(4);

        Glide.with(this)
                .load(myImageList[currentPhase])
                .into(moonView0);

        gameClock.start();
    }



    public void generatePhase(View view) {
        mPlayer.release();
        TextView answerChosen = (TextView) view;
        // Identifies which imageview was clicked for token placement
        answer = answerChosen.getTag().toString();
        Log.i("answer", answer);

        if (firstRound == true) {

            moonView0.setAnimation(animationOut);

            sameView.setText("SAME");
            sameView.setBackgroundColor(Color.parseColor("#FFAE2050"));
            sameView.animate().translationXBy(2000f);


            differentView.setText("DIFFERENT");
            differentView.setBackgroundColor(Color.parseColor("#FF1F35C3"));
            differentView.animate().translationXBy(-2000f);

            answerView2.setText("Is This Image The Same?");

            // we get previous phase and current phase for first round
            previousPhase = currentPhase;
            currentPhase = random.nextInt(4);

            Glide.with(this)
                    .load(myImageList[currentPhase])
                    .into(moonView1);

            moonView1.setAnimation(animationIn);

            firstRound = false;

            Log.i("moonView1", ("MoonView 1 after animation " + moonView1.getX()));

        } else {

            animationIn = AnimationUtils.loadAnimation(this,R.anim.animatein);
            animationIn.setFillAfter(true);
            animationOut = AnimationUtils.loadAnimation(this,R.anim.animateout);
            animationOut.setFillAfter(true);

            if (currentPhase == previousPhase) {
                phaseCompare = "same";
            } else {
                phaseCompare = "different";
            }


            if ((answer.equals("same")) && (phaseCompare.equals("same"))) {
                answerView2.setText("Correct!");
                problemCorrectCount++;
                questionCount++;
            } else if ((answer.equals("different")) && (phaseCompare.equals("different"))) {
                answerView2.setText("Correct!");
                problemCorrectCount++;
                questionCount++;

            } else {
                answerView2.setText("Sorry!");
                questionCount++;
            }

            scoreView.setText(problemCorrectCount + "/" + questionCount);

            Log.i("imageViewTurn", ("check " + imageViewTurn));

            previousPhase = currentPhase;
            currentPhase = random.nextInt(4);

            if (imageViewTurn == 0) {

                Glide.with(this)
                        .load(myImageList[currentPhase])
                        .into(moonView0);

                moonView0.setAnimation(animationIn);

                moonView1.setAnimation(animationOut);


                imageViewTurn = 1;

            } else {

                Glide.with(this)
                        .load(myImageList[currentPhase])
                        .into(moonView1);

                moonView0.setAnimation(animationOut);

                moonView1.setAnimation(animationIn);


                imageViewTurn = 0;


            }
        }
    }


    private void playPhaser()
    {
        mPlayer = MediaPlayer.create(this, R.raw.phaseout);
        mPlayer.start();

    }


}

