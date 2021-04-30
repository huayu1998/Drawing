package com.example.drawing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;
import static com.example.drawing.MyCanvas.paths;
import static com.example.drawing.MyCanvas.pathPaint;
import static com.example.drawing.MyCanvas.path;

public class DrawingActivity extends AppCompatActivity {

    MyCanvas myCanvas;
    TouchListener touchListener;
    Random rd = new Random();
    static final int REQUEST_IMAGE= 1;
    Button red, blue, green, undo, clear, done;

    // Deal with icon part
    public static ConstraintLayout constraintLayout;
    public static ArrayList<ImageView> iconList = new ArrayList<>();
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);

        // Deal with icon part
        constraintLayout = (ConstraintLayout) findViewById(R.id.layout);

        // Get the bitmap/pic source from mainActivity and
        // set myCanvas's background with the image
        Bitmap bitmap = getIntent().getParcelableExtra("picture");
        myCanvas.setBackground(new BitmapDrawable(getResources(), bitmap));

        red = (Button) findViewById(R.id.red);
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.setRed();
            }
        });

        blue = (Button) findViewById(R.id.blue);
        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.setBlue();
            }
        });

        green = (Button) findViewById(R.id.green);
        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.setGreen();
            }
        });

        undo = (Button) findViewById(R.id.undo);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.undo();
                undoIcon();
            }
        });

        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCanvas.clearAll();
                for (int i = 0; i < iconList.size(); i++) {
                    constraintLayout.removeView(iconList.get(i));
                }
            }
        });

        done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void addPath(int id, float x, float y) {
        mp = MediaPlayer.create(DrawingActivity.this, R.raw.song2);
        myCanvas.addPath(id, x, y);
    }


    public void updatePath(int id, float x, float y) {
        mp.start();
        myCanvas.updatePath(id, x, y);
    }

    public void touchUp() {
        mp.release();
        myCanvas.touchUp();
    }

    public void undoIcon() {
        if (iconList.size() > 0) {
            constraintLayout.removeView(iconList.get(iconList.size() - 1));
            iconList.remove(iconList.size() - 1);
        }
    }

    public void onDoubleTap(float x, float y) {
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.ic_baseline_masks_50);
        imageView.setX(x);
        imageView.setY(y);
        constraintLayout.addView(imageView);
        iconList.add(imageView);

        /*
        RotateAnimation anim = new RotateAnimation(0f, 5f, 10f, 10f);
        anim.setInterpolator(new LinearInterpolator());
        //anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(1000);
         */

        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);

        // Start animating the image
        imageView.startAnimation(shake);
    }

    public void onLongPress(float x, float y) {
        //Toast.makeText(this, "long press", Toast.LENGTH_SHORT).show();
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.ic_baseline_ac_unit_60);
        imageView.setX(x);
        imageView.setY(y);
        constraintLayout.addView(imageView);
        iconList.add(imageView);

        Animation shake;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.vib);

        imageView.startAnimation(shake); // starts animation
    }

}