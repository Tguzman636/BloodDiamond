package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.ViewDebug;

public class GrassDOWNTile extends Tile {
    private final Sprite sprite;

    public GrassDOWNTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        //Log.d("Bug-Exterminator", "GrassTile.java - GrassTile()");
        sprite = spriteSheet.getGrassDOWNSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        //Log.d("Bug-Exterminator", "GrassTile.java - draw()");
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
