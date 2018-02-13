package com.example.me.braintrain;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.w3c.dom.Text;

/**
 * Created by Me on 12/9/2017.
 */

public class GameOver extends Activity {

    TextView resultsView;
    TextView highScoreView;
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
        highScoreView = (TextView) findViewById(R.id.highScoreView);

        resultsView.setText("You Got " + problemCorrectCount + " Correct Out of " + questionCount + "!!!");

        checkHighScore();



        //String[] highScores = { "Brian: 2", "Mike: 1", "John: 0" };

        //saveHighScores(highScores);

        //checkHighScores(highScores, problemCorrectCount);

    }

    public void checkHighScore() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.me.sharedpreferences", Context.MODE_PRIVATE);

        //sharedPreferences.edit().putInt("HIGHSCORE", 3).apply();
        int currentHighScore = sharedPreferences.getInt("HIGHSCORE", -1);

        if (problemCorrectCount > currentHighScore) {
            sharedPreferences.edit().putInt("HIGHSCORE", problemCorrectCount).apply();
            highScoreView.setText("New High Score!!! " + problemCorrectCount);
        } else {
            highScoreView.setText("Current High Score " + currentHighScore);
        }

        Log.i("Current High Score", (" " + currentHighScore));

        //Log.i("HighScore", sharedPreferences.getInt("CURHIGHSCORE","HIGHSCORE"));


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

/* Things Got Weird Here, but may still be useful.  Keeping a log of high scores in an array


    //Works!!!!!!!!!!!!!!!!!!
    public void saveHighScores(String[] highScores){

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.me.sharedpreferences", Context.MODE_PRIVATE);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < highScores.length; i++) {
            sb.append(highScores[i]).append(",");
        }

        sharedPreferences.edit().putString("HIGHSCORES", sb.toString()).apply();

        displayHighScores();

    }

    // Works!!!!!
    public void displayHighScores(){

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.me.sharedpreferences", Context.MODE_PRIVATE);

        String compiledString = sharedPreferences.getString("HIGHSCORES","HIGHSCORES");

        String[] highScores = compiledString.split(",");

        Log.i("HIGHSCORES", compiledString);

        Log.i("Test", "Hello");
        checkHighScores(highScores, problemCorrectCount);

    }

    // Works!!!!!!!!
    public void checkHighScores(String[] highScores, int problemCorrectCount ) {
        String currentScore;
        String[] currentStringArray;
        int currentScoreInt;


        for (int i =0; i < highScores.length; i++) {  //Brian: 12
            currentStringArray = highScores[i].split(" ");  // Array[0] = Brian, Array[1] = 12
                currentScore = currentStringArray[1];
                currentScoreInt = Integer.parseInt(currentScore);

                Log.i("currentScoreInt", " " + currentScore);

                if (problemCorrectCount > currentScoreInt) {
                    Log.i("New High Score?","Yes!");
                } else {
                    Log.i("New High Score?", "No");
                }


        }


    }
    */




