package com.softly.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.softly.R;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {

    private ArrayList<ArrayList<ImageView>> structureBoard;
    private GridLayout gridLayoutBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        gridLayoutBoard = findViewById(R.id.layout_board);
        structureBoard = SetupBoard(6, 7);
    }

    private ArrayList<ArrayList<ImageView>> SetupBoard(int rows, int columns) {

        ArrayList<ArrayList<ImageView>> board = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            ArrayList<ImageView> imageViewsRow = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                ImageView currCell = GenerateCell(i, j, rows, columns);
                imageViewsRow.add(currCell);
                gridLayoutBoard.addView(currCell);
            }
            board.add(imageViewsRow);
        }

        return board;
    }

    private ImageView GenerateCell(int currRow, int currColumn, int rows, int columns) {

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        ImageView iwCell = new ImageView(this);
        iwCell.setImageResource(R.drawable.circle_white);

        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.height = GridLayout.LayoutParams.WRAP_CONTENT;
        param.width = GridLayout.LayoutParams.WRAP_CONTENT;
        param.topMargin = 5;
        param.rightMargin = 5;
        param.bottomMargin = 5;
        param.leftMargin = 5;
        param.width = screenWidth/columns;
        param.height = screenWidth/columns;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(currRow);
        param.columnSpec = GridLayout.spec(currColumn);

        iwCell.setLayoutParams(param);
        return iwCell;
    }
}