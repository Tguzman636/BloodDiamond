package com.example.blooddiamond;

import android.content.Context;
import com.example.blooddiamond.GameLoop;

import androidx.core.content.ContextCompat;

public class Enemy extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private final Player player;

    public Enemy(Context context, Player player, double posX, double posY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.white), posX, posY, radius);
        this.player = player;
    }

    public void update() {
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
}
