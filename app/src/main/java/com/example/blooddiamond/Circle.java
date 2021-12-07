package com.example.blooddiamond;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import androidx.core.content.ContextCompat;

public abstract class Circle extends GameObject {
    private final Sprite sprite;
    protected double radius;
    protected Paint paint;

    public Circle(Context context, int color, double posX, double posY, double radius, Sprite sprite) {
        super(posX, posY);
        //Log.d("Bug-Exterminator", "Circle.java - Circle()");

        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
        this.sprite = sprite;
    }

    public static boolean isColliding(Circle enemy, Circle player) {
        //Log.d("Bug-Exterminator", "Circle.java - isColliding()");
        double distance = CalcDistance(enemy, player);
        if (distance < enemy.getRadius()+player.getRadius()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isTapColliding(Circle enemy, Rect tap) {
        double distance = CalcTapDistance(enemy, tap);
        if (distance < enemy.getRadius()+1) {
            return true;
        } else {
            return false;
        }
    }

    private double getRadius() {
        //Log.d("Bug-Exterminator", "Circle.java - getRadius()");
        return radius;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(posX - sprite.getWidth() / 2),
                (int) gameDisplay.gameToDisplayCoordinatesY(posY - sprite.getHeight() / 2)
        );
    }
}
