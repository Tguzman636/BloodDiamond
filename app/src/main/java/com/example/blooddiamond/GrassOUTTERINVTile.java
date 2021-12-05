package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.ViewDebug;

public class GrassOUTTERINVTile extends Tile {
    private final Sprite sprite;

    public GrassOUTTERINVTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        //Log.d("Bug-Exterminator", "GrassTile.java - GrassTile()");
        sprite = spriteSheet.getGrassOUTTERINVSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        //Log.d("Bug-Exterminator", "GrassTile.java - draw()");
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
