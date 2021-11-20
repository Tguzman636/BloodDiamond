package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.content.Context;

import androidx.core.content.ContextCompat;

public class Player extends Circle{

    private Sprite sprite;

    public Player(Context context, double posX, double posY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.teal_200), posX, posY, radius);
        this.posX = posX;
        this.posY = posY;
    }

    public void update(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    //public void draw(Canvas canvas, GameDisplay gameDisplay) {
    //    sprite.draw(canvas);
    //}

    public void pull() {
        this.posX = posX+1;
    }
}
