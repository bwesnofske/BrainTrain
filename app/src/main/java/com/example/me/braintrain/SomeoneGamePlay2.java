package com.example.me.braintrain;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Me on 3/5/2018.
 */

public class SomeoneGamePlay2 extends Activity {

    MediaPlayer mPlayer;

    String gameName;

    int problemCorrectCount;
    int questionCount;
    int randomPattern;

    CountDownTimer startDelay;

    TextView scoreView;
    TextView timerView;
    TextView answerView;
    TextView testView;

    ImageView colorView0;
    ImageView colorView1;
    ImageView colorView2;
    ImageView colorView3;

    Animation animationPattern;

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
                //firstRound();

            }
        };


        gameName = "someonesays";
        problemCorrectCount = 0;
        questionCount = 0;


        super.onCreate(savedInstanceState);

        setContentView(R.layout.somesays_screen2);

        answerView = (TextView) findViewById(R.id.answerView);

        colorView0 = (ImageView) findViewById(R.id.colorView0);
        colorView1 = (ImageView) findViewById(R.id.colorView1);
        colorView2 = (ImageView) findViewById(R.id.colorView2);
        colorView3 = (ImageView) findViewById(R.id.colorView3);


        animationPattern = AnimationUtils.loadAnimation(this, R.anim.animationpattern);
        animationPattern.setDuration(1000);
        //animationPattern.setFillAfter(true);

        patternArrayList = new ArrayList<Integer>();

        patternArrayList.add(0);
        //patternArrayList.add(1);


        myImageList = new int[]{
                R.drawable.bglow, R.drawable.rglow, R.drawable.gglow, R.drawable.yglow};

        //playPattern();


    }


    public void testLoad (View view){
        Glide.with(this)
                .load(R.drawable.bnoglow)
                .into(colorView0);

    }

    private void playLight() {
        mPlayer = MediaPlayer.create(this, R.raw.neonedit);
        mPlayer.start();
    }



    // Generate pattern class
    public void generatePattern() {
        randomPattern = random.nextInt(4);
        patternArrayList.add(randomPattern);
        //playPattern();
    }



    // Play pattern class
    public void playPattern(View view) {
        final View curView = view;
        int count = 0;
        while (patternArrayList.size() > count) {
            if (patternArrayList.get(count) == 0) {
                colorView0.setImageResource(R.drawable.bglow);
                Glide.with(this)
                        .load(myImageList[0])
                        .into(colorView0);
                colorView0.setAnimation(animationPattern);
            } else if (patternArrayList.get(count) == 1) {
                Glide.with(this)
                        .load(myImageList[1])
                        .into(colorView1);
                //colorView1.setAnimation(animationPattern);
            } else if (patternArrayList.get(count) == 2) {
                Glide.with(this)
                        .load(myImageList[2])
                        .into(colorView2);
                //colorView2.setAnimation(animationPattern);
            } else if (patternArrayList.get(count) == 3) {
                Glide.with(this)
                        .load(myImageList[3])
                        .into(colorView3);
                //colorView3.setAnimation(animationPattern);
            }

            count++;
        }

    }



    // Check Pattern




}
