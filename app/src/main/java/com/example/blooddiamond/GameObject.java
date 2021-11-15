package com.example.blooddiamond;

import android.graphics.Canvas;

public abstract class GameObject {
    protected double posX;
    protected double posY;
    protected double VeloX;
    protected double VeloY;

    public GameObject(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void draw(Canvas canvas);

    protected double getPosX() {
        return posX;
    }

    protected double getPosY() {
        return posY ;
    }

    protected static double CalcDistance(GameObject enem, GameObject play) {
        return Math.sqrt(
                Math.pow(enem.getPosX() - play.getPosX(),2) +
                Math.pow(enem.getPosY() - play.getPosY(),2)
        );
    }

}


