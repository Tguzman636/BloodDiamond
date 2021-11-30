package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Player extends Circle{

    private Sprite sprite;

    public Player(Context context, double posX, double posY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.teal_200), posX, posY, radius);
        Log.d("Bug-Exterminator", "Player.java - Player()");
        this.posX = posX;
        this.posY = posY;
    }

    public void update(double posX, double posY) {
        Log.d("Bug-Exterminator", "Player.java - update()");
        this.posX = posX;
        this.posY = posY;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
       super.draw(canvas, gameDisplay);
    }

    public void pull() {
        Log.d("Bug-Exterminator", "Player.java - pull()");
        this.posX = posX+1;
    }
}
