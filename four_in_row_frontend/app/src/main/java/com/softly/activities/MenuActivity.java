package com.softly.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.softly.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button clickButton = findViewById(R.id.btn_go_to_chat);
        clickButton.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ChatActivity.class);
            startActivity(intent);
        });
    }
}