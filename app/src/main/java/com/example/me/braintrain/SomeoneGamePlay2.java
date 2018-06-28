package com.example.me.braintrain;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.animation.ObjectAnimator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Me on 3/5/2018.
 */

/** for the pattern animation.  Create on animation (OOP) We'll call that animation for glowing images in the pattern.
*   In a grid view.  Will be two images.  one glowing one not.  We'll fade the glowing image in and out depending on the pattern.
 * */


public class SomeoneGamePlay2 extends Activity {

   // Declaring Instance Variables
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

    ImageView colorView0Glow;
    ImageView colorView1Glow;
    ImageView colorView2Glow;
    ImageView colorView3Glow;


    Animation animationPattern;
    Animation animationLight;
    ImageSwitcher imageSwitcher0;
    ImageSwitcher imageSwitcher1;
    ImageSwitcher imageSwitcher2;
    ImageSwitcher imageSwitcher3;



    int[] myImageList;

    public ArrayList<Integer> patternArray;

    Random random = new Random();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        patternArray = new ArrayList<Integer>();

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

        //Views for the four symbols

        colorView0 = (ImageView) findViewById(R.id.colorView0);
        colorView1 = (ImageView) findViewById(R.id.colorView1);
        colorView2 = (ImageView) findViewById(R.id.colorView2);
        colorView3 = (ImageView) findViewById(R.id.colorView3);


        // Glow Views for four symbols
        colorView0Glow = (ImageView) findViewById(R.id.colorView0Glow);
        colorView1Glow = (ImageView) findViewById(R.id.colorView1Glow);
        colorView2Glow = (ImageView) findViewById(R.id.colorView2Glow);
        colorView3Glow = (ImageView) findViewById(R.id.colorView3Glow);

        //colorView0Glow.setAlpha(0);
        //colorView1Glow.setAlpha(0);
        //colorView2Glow.setAlpha(0);
        //colorView3Glow.setAlpha(0);

       //animationPattern.start();
       // colorView0Glow.setAnimation(animationpattern);


        // This Works!
        // colorView0Glow.animate().alpha(1);

        //animationPattern.setAnimationListener();
        //animationPattern.setFillAfter(true);

        // Add First Number to the Array
        addNextNumber();

        myImageList = new int[]{
                R.drawable.bglow, R.drawable.rglow, R.drawable.gglow, R.drawable.yglow };



        // This works, but Image is not the right size
        /*
        imageSwitcher0 = (ImageSwitcher)findViewById(R.id.colorView0);

        imageSwitcher0.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                ImageView IV = new ImageView(getApplicationContext());
                IV.setScaleType(ImageView.ScaleType.CENTER);
                IV.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                return IV;
            }

        });
        */


    }


    public void test(View view){
        // next try an xml animation
        colorView0Glow.animate().alpha(1);

    }


    // Read the pattern, the number will correlate to a view.  If patternArray = 0 {


    public void gameStart(View view0, View view1, View view2, View view3 ) {

        //answerView.setText(" " + patternArray.size() + " test");

        view0.setAnimation(animationPattern);
        /*
        int i = 0;
        while (i < patternArray.size()) {

            if (patternArray.get(i) == 0) {
                view0.setAnimation(animationPattern);
            } else if (patternArray.get(i) == 1) {
                view1.setAnimation(animationPattern);
            } else if (patternArray.get(i) == 2) {
                view2.setAnimation(animationPattern);
            } else if (patternArray.get(i) == 3) {
                view3.setAnimation(animationPattern);
            }

        }

        /*
        Object obj = new Object();
        try {
            synchronized (obj) {
                int i = 0;
                while (i < patternArray.size()) {
                    if (patternArray.get(i) == 0) {
                        view0.setAnimation(animationPattern);
                    } else if (patternArray.get(i) == 1) {
                        view1.setAnimation(animationPattern);
                    } else if (patternArray.get(i) == 2) {
                        view2.setAnimation(animationPattern);
                    } else if (patternArray.get(i) == 3) {
                        view3.setAnimation(animationPattern);
                    }

                    i++;
                    obj.wait(500);
                }
            }
        } catch (InterruptedException ex) {
            //SomeFishCatching
        }
        */



    }

    // Attempt to use Object Animator to change Alpha of Glow View



    //adds next number in the array
    public void addNextNumber() {
        Integer randomNumber = random.nextInt(4);
        patternArray.add(randomNumber);
    }




/*
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
    */



    // Check Pattern




}
