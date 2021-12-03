package com.example.blooddiamond;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
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

    public Bitmap getBitmap() {
        //Log.d("Bug-Exterminator", "SpriteSheet.java - getBitmap()");
        return bitmap;
    }

    public Sprite getGrassSprite() {
        //Log.d("Bug-Exterminator", "SpriteSheet.java - getGrassSprite()");
        return getSpriteByIndex(1, 0);
    }

    public Sprite getDirtSprite() {
        //Log.d("Bug-Exterminator", "SpriteSheet.java - getDirtSprite()");
        return getSpriteByIndex(1, 1);
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
