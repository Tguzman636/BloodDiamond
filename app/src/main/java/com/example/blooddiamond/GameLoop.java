package com.example.blooddiamond;

import android.graphics.Canvas;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{ //Thread allows start() to work
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double averageUPS;
    private double averageFPS;
    public static final double MAX_UPS = 30.0;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        Log.d("Bug-Exterminator", "GameLoop.java - GameLoop()");
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop() {
        Log.d("Bug-Exterminator", "GameLoop.java - startLoop()");
        isRunning = true;
        start();
    }

    @Override
    public void run() {
        Log.d("Bug-Exterminator", "GameLoop.java - run()");
        super.run();

        int updateCount = 0;
        int frameCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning) { // Game loop
            //Update/Render Game
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                };
                surfaceHolder.unlockCanvasAndPost(canvas);
                frameCount++;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime);
                averageFPS = frameCount / (1E-3 * elapsedTime);
                updateCount = 0;
                frameCount = 0;
                startTime = System.currentTimeMillis();
            }

        }
    }
}
