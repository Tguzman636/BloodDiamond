package com.example.blooddiamond;

import static com.example.blooddiamond.MapLayout.NUM_COL;
import static com.example.blooddiamond.MapLayout.NUM_ROWS;
import static com.example.blooddiamond.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.blooddiamond.MapLayout.TILE_WIDTH_PIXELS;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.Map;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public Tilemap(SpriteSheet spriteSheet) {
        Log.d("Bug-Exterminator", "Tilemap.java - Tilemap()");
        mapLayout = new MapLayout();
        this.spriteSheet = spriteSheet;
        initializeTilemap();
    }

    private void initializeTilemap() {
        Log.d("Bug-Exterminator", "Tilemap.java - initializeTilemap()");
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[NUM_ROWS][NUM_COL];
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COL; j++) {
                tilemap[i][j] = Tile.getTile(
                        layout[i][j],
                        spriteSheet,
                        getRectByIndex(i,j)
                        );
            }
        }

        Bitmap.Config config= Bitmap.Config.ARGB_8888; //Store each pixel into 4-bits (4*8 bytes)(RGB & Alpha)
        mapBitmap = Bitmap.createBitmap(
                NUM_COL*TILE_WIDTH_PIXELS,
                NUM_ROWS*TILE_HEIGHT_PIXELS,
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COL; j++) {
                tilemap[i][j].draw(mapCanvas);
            }
        }

        System.out.println();
    }

    private Rect getRectByIndex(int i, int j) {
        Log.d("Bug-Exterminator", "Tilemap.java - getRectByIndex()");
        return new Rect(
                j*TILE_WIDTH_PIXELS,
                i*TILE_HEIGHT_PIXELS,
                (j+1)*TILE_WIDTH_PIXELS,
                (i+1)*TILE_HEIGHT_PIXELS
        );
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Log.d("Bug-Exterminator", "Tilemap.java - draw()");
        canvas.drawBitmap(
                mapBitmap,
                gameDisplay.getGameRect(),
                gameDisplay.DISPLAY_RECT,
                null
        );
        Log.d("Bug-Exterminator", "Tilemap.java - draw-post()");
    }
}
