package com.example.blooddiamond;

import android.graphics.Canvas;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{ //Thread allows start() to work
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    public static final double MAX_UPS = 30.0;
    int trigger;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        //Log.d("Bug-Exterminator", "GameLoop.java - GameLoop()");
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public void startLoop() {
        //Log.d("Bug-Exterminator", "GameLoop.java - startLoop()");
        isRunning = true;
        trigger = 1;
        start();
    }

    @Override
    public void run() {
        //Log.d("Bug-Exterminator", "GameLoop.java - run()");
        super.run();

        long startTime;
        long elapsedTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning) { // Game loop
            //Update/Render Game
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    if (trigger == 1) {
                        game.update();
                        game.draw(canvas);
                    }
                };
                surfaceHolder.unlockCanvasAndPost(canvas);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                startTime = System.currentTimeMillis();
                Enemy.WaveUp();
            }

        }
    }

    public void removetrigger(int i) {
        this.trigger = i;
    }
}
