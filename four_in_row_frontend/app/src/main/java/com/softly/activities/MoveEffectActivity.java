package com.softly.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.softly.R;

public class MoveEffectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_effect);

        /*int cellWidth = 100;
        int cellHeight = 100;
        int halfCellWidth = cellWidth / 2;
        int halfCellHeight = cellHeight / 2;

        ImageView iwCell = new ImageView(this);
        Bitmap mBitmap = Bitmap.createBitmap(cellWidth, cellHeight, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.TRANSPARENT);
        Paint mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawCircle(halfCellWidth, halfCellHeight, (float) (halfCellWidth * 0.9), mPaint);
        iwCell.setImageBitmap(mBitmap);

        FrameLayout fl = findViewById(R.id.frame);
        fl.addView(iwCell);*/


        ImageView iwCell = new ImageView(this);
        iwCell.setImageResource(R.drawable.circle_white);
        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(150,150);
        iwCell.setLayoutParams(param);

        FrameLayout fl = findViewById(R.id.frame);
        fl.addView(iwCell);

        Path path = new Path();
        path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
        path.moveTo(500,500);
        path.addCircle(750,1000,250, Path.Direction.CW);
        ObjectAnimator animator = ObjectAnimator.ofFloat(iwCell, View.X, View.Y, path);
        animator.setDuration(5000);
        animator.start();

    }
}