package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Rect;

abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) { this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        GRASS,
        DIRT
    }

    public static Tile getTile(int TileID, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch(TileType.values()[TileID]) {
            case GRASS:
                return new GrassTile(spriteSheet, mapLocationRect);
            case DIRT:
                return new DirtTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
