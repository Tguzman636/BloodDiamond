package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.ViewDebug;

public class GrassINNERTile extends Tile {
    private final Sprite sprite;

    public GrassINNERTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        //Log.d("Bug-Exterminator", "GrassTile.java - GrassTile()");
        sprite = spriteSheet.getGrassINNERSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        //Log.d("Bug-Exterminator", "GrassTile.java - draw()");
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
