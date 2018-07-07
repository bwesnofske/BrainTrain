package com.braintrain.me.braintrain;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {


MediaPlayer mPlayer;
ImageView titleView;

CountDownTimer animateTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playbeep();
    }


    public void onClick(View v) {
        mPlayer.release();
        Intent intent = new Intent(MainActivity.this, Menu.class);
        startActivity(intent);
    }

    private void playbeep()
    {
        mPlayer = MediaPlayer.create(this, R.raw.sixteenbitsound);
        mPlayer.start();
    }

}