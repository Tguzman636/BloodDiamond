package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class Player extends Circle{

    private Sprite sprite;

    public Player(Context context, double posX, double posY, double radius, Sprite sprite) {
        super(context, ContextCompat.getColor(context, R.color.teal_200), posX, posY, radius, sprite);
        //Log.d("Bug-Exterminator", "Player.java - Player()");
        this.posX = posX;
        this.posY = posY;
        this.sprite = sprite;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
       sprite.draw(canvas,
               (int) gameDisplay.gameToDisplayCoordinatesX(getX())-sprite.getWidth()/2,
               (int) gameDisplay.gameToDisplayCoordinatesY(getY()-sprite.getHeight()/2)
       );
    }

    public void pull() {
        //Log.d("Bug-Exterminator", "Player.java - pull()");
        if (posX > 1700 && posY < 3000) {
            this.posY = posY+1;
        } else {
            this.posX = posX + 1;
        }
    }

    public double getX() {
        return posX;
    }
    public double getY() {
        return posY;
    }

}
