package com.example.me.braintrain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Me on 12/9/2017.
 */

public class Menu extends Activity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu);


    }

    public void onClickAddUp(View v) {

        Intent intent = new Intent(Menu.this, GamePlay.class);
        startActivity(intent);

    }

    public void onClickPhaseOut(View v) {

        Intent intent = new Intent(Menu.this, PhaseGamePlay.class);
        startActivity(intent);

    }

    /*
    public void onClickSomeoneSays(View v) {

        Intent intent = new Intent(Menu.this, SomeoneGamePlay.class);
        startActivity(intent);

    }
    */


}
