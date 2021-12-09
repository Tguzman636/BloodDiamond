package com.example.blooddiamond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
    private MainActivity mainActivity;
    private SpriteSheet spriteSheet = new SpriteSheet(getContext());
    private int Counter;
    private int Enable;
    private int Missed;
    private int GameOver;
    private int Wave;
    private int End;
    private int WaveTrigger = 10;

    public Game(Context context) {
        super(context);
        //Log.d("Bug-Exterminator", "Game.java - Game()");
        getContext();
        Counter = 0;
        Enable = 1;
        Missed = 0;
        GameOver = 0;
        Wave = 0;
        End = 0;
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        gameOver = new GameOver(context);
        player = new Player(getContext(), 1000, 1920, 40, spriteSheet.getPlayerSprite());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);
        tilemap = new Tilemap(spriteSheet);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (GameOver != 1) {
                    double XOffset = gameDisplay.getDisplayOffsetX();
                    double YOffset = gameDisplay.getDisplayOffsetY();
                    double x = event.getX() - XOffset;
                    double y = event.getY() - YOffset;
                    Rect tap = new Rect((int) x, (int) y, (int) (x + 1), (int) (y + 1));
                    if (Enable == 1) {
                        gameLoop.removetrigger(0);
                        Iterator<Enemy> enemyIterator = enemyList.iterator();
                        while (enemyIterator.hasNext()) {
                            if (Circle.isTapColliding(enemyIterator.next(), tap)) {
                                Counter++;
                                enemyIterator.remove();
                                gameLoop.removetrigger(1);
                                return true;
                            }
                        }
                        Missed++;
                        gameLoop.removetrigger(1);
                        return true;
                    }
                    Missed++;
                    return true;
                }
        }
        return super.onTouchEvent(event);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Log.d("Bug-Exterminator", "Game.java - surfaceCreated()");
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
        gameOver.InitializeFirebase();
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
        tilemap.draw(canvas, gameDisplay);

        UpdateCounter(canvas);

        player.draw(canvas, gameDisplay);

        if((player.getX() >= 3838) && (End == 0)) {
            gameOver.firebase(Counter);
            End = 1;
        }

        if(player.getX() >= 3838) {
            gameOver.draw(canvas);
            return;
        }

        if (Counter == WaveTrigger) {
            Enemy.WaveUp();
            Wave++;
            WaveTrigger+=WaveTrigger;
        }

        Enable = 0;
        for (Enemy enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }
        Enable = 1;
    }

    private void UpdateCounter(Canvas canvas) {
        float x = 1900;
        float y = 120;
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.red);
        paint.setColor(color);
        float textSize = 50;
        paint.setTextSize(textSize);
        canvas.drawText("Enemies Killed:",x-395,y,paint);
        canvas.drawText(String.valueOf(Counter),x,y,paint);
        canvas.drawText("Missed:",x-230,y+60,paint);
        canvas.drawText(String.valueOf(Missed),x,y+60,paint);
        canvas.drawText("Wave:",x-185,y+120,paint);
        canvas.drawText(String.valueOf(Wave),x,y+120,paint);
    }

    public void update(Canvas canvas) {
        //Log.d("Bug-Exterminator", "Game.java - update()");
        if(player.getX() >= 3838) {
            gameOver.draw(canvas);
            GameOver = 1;
            return;
        }

        Enable = 0;
        if (Enemy.readyToSpawn()) {
            if (enemyList.size() < 30) {
                enemyList.add(new Enemy(getContext(), player, gameDisplay.gameCenterX(), gameDisplay.gameCenterY(), spriteSheet.getEnemySprite()));
            }
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
        Enable = 1;
        gameDisplay.update();

    }


    public void pause() {
        gameLoop.stopLoop();
    }
}