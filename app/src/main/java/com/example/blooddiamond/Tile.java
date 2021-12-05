package com.example.blooddiamond;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

abstract class Tile {

    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect) {
        //Log.d("Bug-Exterminator", "Tile.java - Tile()");
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        GRASS,
        DIRT,
        GRASSUP,
        GRASSDOWN,
        GRASSRIGHT,
        GRASSLEFT,
        GRASSINNER,
        GRASSINNERINV,
        GRASSOUTTER,
        GRASSOUTTERINV
    }

    public static Tile getTile(int TileID, SpriteSheet spriteSheet, Rect mapLocationRect) {
        //Log.d("Bug-Exterminator", "Tile.java - getTile()");
        switch(TileType.values()[TileID]) {
            case GRASS:
                return new GrassTile(spriteSheet, mapLocationRect);
            case DIRT:
                return new DirtTile(spriteSheet, mapLocationRect);
            case GRASSUP:
                return new GrassUPTile(spriteSheet, mapLocationRect);
            case GRASSDOWN:
                return new GrassDOWNTile(spriteSheet, mapLocationRect);
            case GRASSRIGHT:
                return new GrassRIGHTTile(spriteSheet, mapLocationRect);
            case GRASSLEFT:
                return new GrassLEFTTile(spriteSheet, mapLocationRect);
            case GRASSINNER:
                return new GrassINNERTile(spriteSheet, mapLocationRect);
            case GRASSINNERINV:
                return new GrassINNERINVTile(spriteSheet, mapLocationRect);
            case GRASSOUTTER:
                return new GrassOUTTERTile(spriteSheet, mapLocationRect);
            case GRASSOUTTERINV:
                return new GrassOUTTERINVTile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);
}
