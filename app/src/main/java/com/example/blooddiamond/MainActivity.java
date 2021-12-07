package com.example.blooddiamond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private Game game;
    private MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d("Bug-Exterminator", "MainActivity.java - onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow(); //Takes away app-header, FULLSCREEN
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        game = new Game(this);
        setContentView(game);
    }
}