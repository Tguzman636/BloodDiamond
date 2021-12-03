package com.example.blooddiamond;

import android.content.Context;
import android.util.Log;

import com.example.blooddiamond.GameLoop;

import androidx.core.content.ContextCompat;

public class Enemy extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 80.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 20;
    private static double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private static double wave;

    private final Player player;
    private Sprite sprite;

    public Enemy(Context context, Player player, double getDisplayOffsetX, double getDisplayOffsetY, Sprite sprite) {
        super(
                context,
                ContextCompat.getColor(context, R.color.white),
                Math.random()*2000-1000+getDisplayOffsetX,
                Math.random()*2000-1000+getDisplayOffsetY,
                30,
                sprite
        );
        //Log.d("Bug-Exterminator", "Enemy.java - Enemy()");
        this.player = player;
        this.sprite = sprite;
    }

    public static boolean readyToSpawn() {
        //Log.d("Bug-Exterminator", "Enemy.java - readyToSpawn()");
        if (updatesUntilNextSpawn <= 0 ) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn --;
            updatesUntilNextSpawn = updatesUntilNextSpawn - wave;
            return false;
        }
    }

    public static void WaveUp() {
        //Log.d("Bug-Exterminator", "Enemy.java - WaveUp()");
        wave = wave + 0.169;
    }

    public void update() {
        //Log.d("Bug-Exterminator", "Enemy.java - update()");
        double distanceToPlayerX = player.getPosX() - posX;
        double distanceToPlayerY = player.getPosY() - posY;
        double distanceToPlayer = GameObject.CalcDistance(this, player);
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        if (distanceToPlayer > 0) {
            VeloX = directionX*MAX_SPEED;
            VeloY = directionY*MAX_SPEED;
        } else {
            VeloX = 0;
            VeloY = 0;
        }

        posX += VeloX;
        posY += VeloY;
    }

    public Sprite getSprite() {
        return this.sprite;
    }
}
