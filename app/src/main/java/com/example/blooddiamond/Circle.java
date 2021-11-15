package com.example.blooddiamond;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public abstract class Circle extends GameObject {
    protected double radius;
    protected Paint paint;

    public Circle(Context context, int color, double posX, double posY, double radius) {
        super(posX, posY);

        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) posX,(float) posY,(float) radius, paint);
    }
}
