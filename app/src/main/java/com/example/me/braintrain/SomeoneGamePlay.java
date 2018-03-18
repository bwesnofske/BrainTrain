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

    CountDownTimer gameClock;

    TextView scoreView;
    TextView timerView;

    ImageView offView;
    ImageView noGlowView;
    ImageView glowView;


    Animation animationLight;
    Animation animationIn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        gameClock = new CountDownTimer(20000, 1000) {

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
                Intent intent = new Intent(SomeoneGamePlay.this, GameOver.class);
                intent.putExtra("problemCorrectCount", problemCorrectCount);
                intent.putExtra("questionCount", questionCount);
                intent.putExtra("gameName", gameName);
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


        Glide.with(this)
                .load(R.drawable.ssoff)
                .into(offView);


        //playLight();

        //Light animation.  Should only need to have lighton(noglow) become visable and not.  Then at the end make glow visable.
        // setelevation to make sure correct views are layered right.  Z value




    }


    public void saysGameStart(View view){
        final View curView = view;

        playLight();

        Glide.with(this)
                .load(R.drawable.ssnoglow)
                .into(noGlowView);
        /*
        Glide.with(this)
                .load(R.drawable.ssglow)
                .into(glowView);

        */
        noGlowView = (ImageView) findViewById(R.id.noGlowView);
        glowView = (ImageView) findViewById(R.id.glowView);
        animationLight = AnimationUtils.loadAnimation(this,R.anim.animationlight);
        animationLight.setFillAfter(true);
        //animationLight.setDuration(1500);

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

        noGlowView = (ImageView) findViewById(R.id.noGlowView);
        glowView = (ImageView) findViewById(R.id.glowView);
        animationLight = AnimationUtils.loadAnimation(this, R.anim.animationlight);
        animationLight.setFillAfter(true);

        glowView.setAnimation(animationLight);
    }

    private void playLight() {
        mPlayer = MediaPlayer.create(this, R.raw.neonedit);
        mPlayer.start();
    }

}
