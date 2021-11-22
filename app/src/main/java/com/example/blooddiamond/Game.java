package com.example.blooddiamond;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Tilemap tilemap;
    private Player player;
    private GameLoop gameLoop;
    private Enemy enemy;
    private List<Enemy> enemyList = new ArrayList<Enemy>();

    public Game(Context context) {
        super(context);
        getContext();

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        SpriteSheet spriteSheet = new SpriteSheet(context);

        gameLoop = new GameLoop(this, surfaceHolder);

        tilemap = new Tilemap(spriteSheet);

        player = new Player(getContext(), 500, 500, 30);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) { //Temporary
            case MotionEvent.ACTION_DOWN:
                double x = event.getX();
                double y = event.getY();
                //if calcdistance = 0 -> remove enemy
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
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        player.draw(canvas);
        for (Enemy enemy : enemyList) {
            enemy.draw(canvas);
        }
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS" + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS" + averageFPS, 100, 200, paint);
    }

    public void update() {
        if(Enemy.readyToSpawn()) {
            enemyList.add(new Enemy(getContext(), player));
        }

        for (Enemy enemy : enemyList) {
            enemy.update();
        }
        //If player.getX,  >= Screen Size => End Game
        Iterator<Enemy> enemyIterator = enemyList.iterator();
        while (enemyIterator.hasNext()) {
            if (Circle.isColliding(enemyIterator.next(), player)) {
                player.pull();
            }
        }
    }

}