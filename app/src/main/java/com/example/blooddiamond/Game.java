package com.example.blooddiamond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewDebug;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Tilemap tilemap;
    private Player player;
    private GameLoop gameLoop;
    private Enemy enemy;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private GameDisplay gameDisplay;
    private GameOver gameOver;

    public Game(Context context) {
        super(context);
        //Log.d("Bug-Exterminator", "Game.java - Game()");
        getContext();

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        SpriteSheet spriteSheet = new SpriteSheet(context);

        gameLoop = new GameLoop(this, surfaceHolder);

        gameOver = new GameOver(context);

        player = new Player(getContext(), 1500, 500, 40);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        tilemap = new Tilemap(spriteSheet);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d("Bug-Exterminator", "Game.java - onTouchEvent()");
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //Log.d("Bug-Exterminator", "Game.java - MotionEvent");
                double XOffset = gameDisplay.getDisplayOffsetX();
                double YOffset = gameDisplay.getDisplayOffsetY();
                double x = event.getX()-XOffset;
                double y = event.getY()-YOffset;
                //Log.d("Bug-ExterminatorDepth", "X:"+ String.valueOf(x));
                //Log.d("Bug-ExterminatorDepth", "X:"+ String.valueOf(y));
                Player tap= new Player(getContext(), x, y, 1);
                Iterator<Enemy> enemyIterator = enemyList.iterator();
                while (enemyIterator.hasNext()) {
                    if (Circle.isColliding(enemyIterator.next(), tap)) {
                        enemyIterator.remove();
                    }
                }
                return true;
        }

        return super.onTouchEvent(event);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Log.d("Bug-Exterminator", "Game.java - surfaceCreated()");
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //Log.d("Bug-Exterminator", "Game.java - surfaceChanged()");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //Log.d("Bug-Exterminator", "Game.java - surfaceDestroyed()");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //Log.d("Bug-Exterminator", "Game.java - draw()");
        drawUPS(canvas);
        drawFPS(canvas);

        tilemap.draw(canvas, gameDisplay);

        player.draw(canvas, gameDisplay);

        if(player.getX() >= 3840) {
            gameOver.draw(canvas);
        }


        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }
    }

    public void drawUPS(Canvas canvas) {
        //Log.d("Bug-Exterminator", "Game.java - drawUPS()");
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS" + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        //Log.d("Bug-Exterminator", "Game.java - drawFPS()");
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS" + averageFPS, 100, 200, paint);
    }

    public void update() {
        //Log.d("Bug-Exterminator", "Game.java - update()");

        if(player.getX() >= 3840) {
            return;
        }

        if(Enemy.readyToSpawn()) {
            enemyList.add(new Enemy(getContext(), player, gameDisplay.gameCenterX(), gameDisplay.gameCenterY()));
        }

        for (Enemy enemy : enemyList) {
            enemy.update();
        }

        Iterator<Enemy> enemyIterator = enemyList.iterator();
        while (enemyIterator.hasNext()) {
            if (Circle.isColliding(enemyIterator.next(), player)) {
                player.pull();
            }
        }
        gameDisplay.update();
    }


}