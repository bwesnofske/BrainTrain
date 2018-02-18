package com.example.me.braintrain;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Me on 2/12/2018.
 */


public class PhaseGamePlay extends Activity {

    List<Image> phases;

    ImageView outView;
    ImageView titleView;
    ImageView moonView0;
    ImageView moonView1;
    ImageView moonView2;

    TextView sameView;
    TextView differentView;
    TextView answerView;
    TextView answerView2;

    TextView gameStart;

    int[] myImageList;

    int previousPhase;
    int currentPhase;
    int imageViewTurn;

    Random random = new Random();


    MediaPlayer mPlayer;

    //@GlideModule
    //public final class MyAppGlideModule extends AppGlideModule {}


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        imageViewTurn = 0;

        super.onCreate(savedInstanceState);

        setContentView(R.layout.phase_out_screen);

        myImageList = new int[]{
                R.drawable.moonphase1, R.drawable.moonphase2, R.drawable.moonphase3, R.drawable.moonphase4,
                R.drawable.moonphase5, R.drawable.moonphase6, R.drawable.moonphase7};


        outView = (ImageView) findViewById(R.id.outView);
        titleView = (ImageView) findViewById(R.id.titleView);
        sameView = (TextView) findViewById(R.id.sameView);
        differentView = (TextView) findViewById(R.id.differentView);
        answerView = (TextView) findViewById(R.id.answerView);
        answerView2 = (TextView) findViewById(R.id.answerView2);
        moonView0 = (ImageView) findViewById(R.id.moonView0);
        moonView1 = (ImageView) findViewById(R.id.moonView1);
        moonView2 = (ImageView) findViewById(R.id.moonView2);

        outView.bringToFront();

        sameView.setTranslationX(-2000f);
        differentView.setTranslationX(2000f);

        moonView1.setTranslationX(2000f);
        moonView2.setTranslationX(2000f);

        Glide.with(this)
                .load(R.drawable.phase)
                .into(titleView);


        Glide.with(this)
                .load(R.drawable.outaltered)
                .into(outView);


    }


    public void gameStart(View view){

        outView = (ImageView) findViewById(R.id.outView);
        titleView = (ImageView) findViewById(R.id.titleView);
        sameView = (TextView) findViewById(R.id.sameView);
        differentView = (TextView) findViewById(R.id.differentView);
        answerView = (TextView) findViewById(R.id.answerView);

        answerView.setVisibility(View.GONE);
        answerView2.setVisibility(View.VISIBLE);


        outView.animate().translationXBy(-2000);
        titleView.animate().translationXBy(2000);

        //playPhaser();


        sameView.setText("SAME");
        sameView.setBackgroundColor(Color.parseColor("#FFAE2050"));
        sameView.animate().translationXBy(2000f);


        differentView.setText("DIFFERENT");
        differentView.setBackgroundColor(Color.parseColor("#FF1F35C3"));
        differentView.animate().translationXBy(-2000f);

        answerView2.setText("Remember This Image! Click Here To Begin");

        currentPhase = random.nextInt(6);

        Glide.with(this)
                .load(myImageList[currentPhase])
                .into(moonView0);

        //mPlayer.release();
    }


    public void generatePhase(View view){

        answerView2.setText("Is This Image The Same?");
        previousPhase = currentPhase;
        currentPhase = random.nextInt(6);

        //button listener

        if (imageViewTurn == 0) {
            Glide.with(this)
                    .load(myImageList[currentPhase])
                    .into(moonView0);
        } else if (imageViewTurn == 1) {
            Glide.with(this)
                    .load(myImageList[currentPhase])
                    .into(moonView1);
        } else if (imageViewTurn == 2) {
            Glide.with(this)
                    .load(myImageList[currentPhase])
                    .into(moonView2);
        }

        if ()



    }

    public void animateImageOff(View view, ImageView imageView){
        imageView.setTranslationX(-2000f);

    }

    public void animateImageOn(View view, ImageView imageView) {
        imageView.setTranslationX(-2000f);
    }

    private void playPhaser()
    {
        mPlayer = MediaPlayer.create(this, R.raw.phaseout);
        mPlayer.start();

    }


}

