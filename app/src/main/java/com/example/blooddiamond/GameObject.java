package com.example.blooddiamond;

import android.graphics.Canvas;
import android.util.Log;

public abstract class GameObject {
    protected double posX;
    protected double posY;
    protected double VeloX;
    protected double VeloY;

    public GameObject(double posX, double posY) {
        Log.d("Bug-Exterminator", "GameObject.java - GameObject()");
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);

    public double getPosX() {
        Log.d("Bug-Exterminator", "GameObject.java - getPosX()");
        return posX;
    }

    public double getPosY() {
        Log.d("Bug-Exterminator", "GameObject.java - getPosY()");
        return posY ;
    }

    protected static double CalcDistance(GameObject enem, GameObject play) {
        Log.d("Bug-Exterminator", "GameObject.java - CalcDistance()");
        return Math.sqrt(
                Math.pow(enem.getPosX() - play.getPosX(),2) +
                Math.pow(enem.getPosY() - play.getPosY(),2)
        );
    }

}


