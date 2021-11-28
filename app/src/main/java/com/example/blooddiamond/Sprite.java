package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Sprite {
    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        Log.d("Bug-Exterminator", "Sprite.java - Sprite()");
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int x, int y) {
        Log.d("Bug-Exterminator", "Sprite.java - draw()");
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(x,y,x+getWidth(),y+getHeight()),
                null
        );
    }

    public int getWidth() {
        Log.d("Bug-Exterminator", "Sprite.java - getWidth()");
        return rect.width();
    }

    public int getHeight() {
        Log.d("Bug-Exterminator", "Sprite.java - getHeight()");
        return rect.height();
    }

}
