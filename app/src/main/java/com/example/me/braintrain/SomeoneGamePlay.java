package com.example.me.braintrain;

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


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Me on 3/5/2018.
 */

public class SomeoneGamePlay extends Activity {

    MediaPlayer mPlayer;

    String gameName;

    int problemCorrectCount;
    int questionCount;
    int randomPattern;

    CountDownTimer startDelay;

    TextView scoreView;
    TextView timerView;
    TextView answerView;

    ImageView offView;
    ImageView noGlowView;
    ImageView glowView;


    Animation animationLight;
    Animation animationPattern;
    Animation animationOff;

    ArrayList<Integer> patternArrayList;
    int[] myImageList;

    Random random = new Random();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        startDelay = new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SomeoneGamePlay.this, SomeoneGamePlay2.class);
                startActivity(intent);
            }
        };


        gameName = "someonesays";
        problemCorrectCount = 0;
        questionCount = 0;


        super.onCreate(savedInstanceState);

        setContentView(R.layout.somesays_screen);

        offView = (ImageView) findViewById(R.id.offView);
        noGlowView = (ImageView) findViewById(R.id.noGlowView);
        glowView = (ImageView) findViewById(R.id.glowView);

        answerView = (TextView) findViewById(R.id.answerView);


        animationLight = AnimationUtils.loadAnimation(this, R.anim.animationlight);
        animationLight.setFillAfter(true);

        patternArrayList = new ArrayList<Integer>();

        patternArrayList.add(0);
        patternArrayList.add(1);


        Glide.with(this)
                .load(R.drawable.ssoff)
                .into(offView);

        myImageList = new int[]{
                R.drawable.bglow, R.drawable.rglow, R.drawable.gglow, R.drawable.yglow};

        //playLight();

    }


    public void saysGameStart(View view) {
        final View curView = view;

        playLight();

        Glide.with(this)
                .load(R.drawable.ssnoglow)
                .into(noGlowView);


        animationLight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                signAnimateFinal(curView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        noGlowView.setAnimation(animationLight);
        //mPlayer.release();
        //gameClock.start();
    }

    public void signAnimateFinal(View view) {
        Glide.with(this)
                .load(R.drawable.ssglow)
                .into(glowView);

        animationLight = AnimationUtils.loadAnimation(this, R.anim.animationlight);
        animationLight.setFillAfter(true);

        glowView.setAnimation(animationLight);

        startDelay.start();
    }



    private void playLight() {
        mPlayer = MediaPlayer.create(this, R.raw.neonedit);
        mPlayer.start();
    }



}
