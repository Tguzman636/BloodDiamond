package com.example.blooddiamond;

import android.graphics.Rect;
import android.util.Log;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private final GameObject centerObject;
    private final double displayCenterX;
    private final double displayCenterY;
    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;
    private double gameCenterX;
    private double gameCenterY;


    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {
        Log.d("Bug-Exterminator", "GameDisplay.java - GameDisplay()");
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);

        this.centerObject = centerObject;

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;

        update();
    }

    public void update() {
        Log.d("Bug-Exterminator", "GameDisplay.java - update()");
        gameCenterX = centerObject.getPosX();
        gameCenterY = centerObject.getPosY();

        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }

    public Rect getGameRect() {
        Log.d("Bug-Exterminator", "GameDisplay.java - getGameRect()");
        return new Rect(
                (int) (gameCenterX - widthPixels/2),
                (int) (gameCenterY - heightPixels/2),
                (int) (gameCenterX + widthPixels/2),
                (int) (gameCenterY + heightPixels/2)
        );
    }

    public double gameToDisplayCoordinatesX(double posX) {
        return posX + gameToDisplayCoordinatesOffsetX;
    }

    public double gameToDisplayCoordinatesY(double posY) {
        return posY + gameToDisplayCoordinatesOffsetY;
    }
}
