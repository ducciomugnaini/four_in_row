package com.softly.activities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.orhanobut.logger.Logger;
import com.softly.R;
import com.softly.signalr.SignalRSingleton;
import com.softly.structures.Player;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {

    private SignalRSingleton signalRSingleton;

    private ArrayList<ArrayList<ImageView>> structureBoard;
    private GridLayout gridLayoutBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        signalRSingleton = (SignalRSingleton) getApplication();

        gridLayoutBoard = findViewById(R.id.layout_board);
        structureBoard = SetupBoard(6, 7);

        Logger.d("Hello");
    }

    @Override
    public void onBackPressed() {

        // todo utilizzare il player corrente
        Player player = new Player();
        player.Nickname = "Android Player FAKE-LEAVING";
        player.Wins = 0;

        // todo return to menu activity
        Logger.d("Board Activity Back Pressed");
        signalRSingleton.RemoveFromLobby(player);
        signalRSingleton.ResetHubConnection();
        super.onBackPressed();
    }

    private ArrayList<ArrayList<ImageView>> SetupBoard(int rows, int columns) {

        ArrayList<ArrayList<ImageView>> board = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            ArrayList<ImageView> imageViewsRow = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                ImageView ivCurrCell = GenerateCell(i, j, rows, columns);
                ivCurrCell.setOnClickListener(new CellClickListener(i, j));
                imageViewsRow.add(ivCurrCell);
                gridLayoutBoard.addView(ivCurrCell);
            }
            board.add(imageViewsRow);
        }

        return board;
    }

    class CellClickListener implements View.OnClickListener {

        private int row;
        private int col;

        CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Hello Four in Row " + row + " " + col,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private ImageView GenerateCell(int currRow, int currColumn, int rows, int columns) {

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;
        float cellWidth = (float) Math.floor(screenWidth / (columns + 1));
        float cellHeight = cellWidth;
        float halfCellWidth = (float) Math.floor(cellWidth / 2);
        float halfCellHeight = (float) Math.floor(cellHeight / 2);

        ImageView iwCell = new ImageView(this);

        //--------------

        //iwCell.setImageResource(R.drawable.circle_white);

        //--------------

        Bitmap mBitmap = Bitmap.createBitmap((int) cellWidth, (int) cellHeight,
                Bitmap.Config.ARGB_8888);
        // Create a Canvas with the bitmap.
        Canvas mCanvas = new Canvas(mBitmap);
        // Fill the entire canvas with this solid color.
        mCanvas.drawColor(Color.TRANSPARENT);

        Paint mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        mCanvas.drawCircle(halfCellWidth, halfCellHeight, (float) (halfCellWidth * 0.9), mPaint);

        // Associate the bitmap to the ImageView.
        iwCell.setImageBitmap(mBitmap);

        //--------------

        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.height = GridLayout.LayoutParams.WRAP_CONTENT;
        param.width = GridLayout.LayoutParams.WRAP_CONTENT;
        param.topMargin = 3;
        param.rightMargin = 3;
        param.bottomMargin = 3;
        param.leftMargin = 3;
        param.width = (int) cellWidth;
        param.height = (int) cellHeight;
        param.setGravity(Gravity.CENTER);
        param.rowSpec = GridLayout.spec(currRow);
        param.columnSpec = GridLayout.spec(currColumn);

        iwCell.setLayoutParams(param);
        return iwCell;
    }
}