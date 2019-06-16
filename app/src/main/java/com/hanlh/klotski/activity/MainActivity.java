package com.hanlh.klotski.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hanlh.klotski.R;
import com.hanlh.klotski.activity.GameSelectActivity;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void selectGame(View view) {
        Intent intent = new Intent(this, GameSelectActivity.class);
        startActivity(intent);
    }
}
