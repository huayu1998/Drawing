package com.example.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCanvas extends View {

    HashMap<Integer, Path> activePaths;
    public static Paint pathPaint;
    public static Path path;
    public static ArrayList<Draw> paths;
    int currentColor;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        paths = new ArrayList<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        currentColor = Color.BLACK;
        pathPaint.setColor(Color.BLACK);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Draw draw : paths) {
            pathPaint.setColor(draw.color);
            //pathPaint.setMaskFilter(null);
            canvas.drawPath(draw.path, pathPaint);

        }
        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        path = new Path();
        Draw draw = new Draw(currentColor, path);
        paths.add(draw);
        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void touchUp() {

    }


    public void setRed() {
        currentColor = Color.RED;
    }

    public void setBlue() {
        currentColor = Color.BLUE;
    }

    public void setGreen() {
        currentColor = Color.GREEN;
    }

    public void undo() {
        if (paths.size() != 0) {

            if (paths.size() == 1) {
                paths.remove(paths.size() - 1);
                paths.clear();
                path.reset();
                pathPaint.setColor(Color.BLACK);
            }
            else {
                paths.remove(paths.size() - 1);
            }

            invalidate(); // add
        } else {
            path.reset();
        }
    }

    public void clearAll() {
        if (paths.size() != 0) {
            paths.remove(paths.size() - 1);
            invalidate();
        }
        paths.clear();
        path.reset();
        currentColor = Color.BLACK;
        pathPaint.setColor(Color.BLACK);
    }

}
