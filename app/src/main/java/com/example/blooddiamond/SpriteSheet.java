package com.example.blooddiamond;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private final Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.spritesheet1);
    }

    private Sprite getSpriteByIndex(int Row, int Col) {
        return new Sprite(this, new Rect(
                Col*SPRITE_WIDTH_PIXELS,
                Row*SPRITE_HEIGHT_PIXELS,
                (Col+1)*SPRITE_WIDTH_PIXELS,
                (Row+1)*SPRITE_HEIGHT_PIXELS
        ));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 0);
    }

    public Sprite getDirtSprite() {
        return getSpriteByIndex(1, 1);
    }
}
