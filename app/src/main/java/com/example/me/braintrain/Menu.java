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

public class Menu extends Activity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);


    }

    public void onClick(View v) {

        Intent intent = new Intent(Menu.this, GamePlay.class);
        startActivity(intent);

    }



}
