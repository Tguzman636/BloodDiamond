package com.example.blooddiamond;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewDebug;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private final Bitmap bitmap;

    public SpriteSheet(Context context) {
        //Log.d("Bug-Exterminator", "SpriteSheet.java - SpriteSheet()");
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.spritesheet1, bitmapOptions);
    }

    public Sprite getEnemySprite() {
        return new Sprite(this, new Rect(0,0,64,64));
    }

    public Sprite getPlayerSprite() {
        int Row = 0;
        int Col = 2;
        return new Sprite(this, new Rect(
                Col*SPRITE_WIDTH_PIXELS,
                Row*SPRITE_HEIGHT_PIXELS,
                (Col+2)*SPRITE_WIDTH_PIXELS,
                (Row+2)*SPRITE_HEIGHT_PIXELS
        ));
    }

    public Sprite getTapSprite() {
        return new Sprite(this, new Rect(0,0,1,1));
    }

    public Bitmap getBitmap() {
        //Log.d("Bug-Exterminator", "SpriteSheet.java - getBitmap()");
        return bitmap;
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 0);
    }

    public Sprite getDirtSprite() {
        return getSpriteByIndex(1, 1);
    }

    public Sprite getGrassUPSprite() {
        return getSpriteByIndex(2, 0);
    }

    public Sprite getGrassDOWNSprite() {
        return getSpriteByIndex(2, 1);
    }

    public Sprite getGrassRIGHTSprite() {
        return getSpriteByIndex(3, 0);
    }

    public Sprite getGrassLEFTSprite() {
        return getSpriteByIndex(3, 1);
    }

    public Sprite getGrassINNERSprite() {
        return getSpriteByIndex(4, 0);
    }

    public Sprite getGrassINNERINVSprite() {
        return getSpriteByIndex(4, 1);
    }

    public Sprite getGrassOUTTERSprite() {
        return getSpriteByIndex(4, 2);
    }

    public Sprite getGrassOUTTERINVSprite() {
        return getSpriteByIndex(4, 3);
    }

    private Sprite getSpriteByIndex(int Row, int Col) {
        //Log.d("Bug-Exterminator", "SpriteSheet.java - getSpriteByIndex()");
        //Log.d("Bug-ExterminatorDepth", "Col " + String.valueOf(Col*SPRITE_WIDTH_PIXELS));
        //Log.d("Bug-ExterminatorDepth", "Row " + String.valueOf(Row*SPRITE_HEIGHT_PIXELS));
        //Log.d("Bug-ExterminatorDepth", "Col+1 " + String.valueOf((Col+1)*SPRITE_WIDTH_PIXELS));
        //Log.d("Bug-ExterminatorDepth", "Row+1 " + String.valueOf((Row+1)*SPRITE_HEIGHT_PIXELS));
        return new Sprite(this, new Rect(
                Col*SPRITE_WIDTH_PIXELS,
                Row*SPRITE_HEIGHT_PIXELS,
                (Col+1)*SPRITE_WIDTH_PIXELS,
                (Row+1)*SPRITE_HEIGHT_PIXELS
        ));
    }
}
