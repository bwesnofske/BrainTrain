package com.example.me.braintrain;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Me on 12/9/2017.
 */

public class GameOver extends Activity {

    TextView resultsView;
    int problemCorrectCount;
    int questionCount;
    MediaPlayer mPlayer3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.end_screen);

        victory();

        problemCorrectCount = getIntent().getIntExtra("problemCorrectCount", 0);
        questionCount = getIntent().getIntExtra("questionCount", 0);

        resultsView = (TextView) findViewById(R.id.resultsView);

        resultsView.setText("You Got " + problemCorrectCount + " Correct Out of " + questionCount + "!!!");
    }


    public void playAgain(View v) {
        Intent intent = new Intent(GameOver.this, GamePlay.class);
        startActivity(intent);
    }

    private void victory()
    {
        mPlayer3 = MediaPlayer.create(this, R.raw.win);
        mPlayer3.start();
    }

}
