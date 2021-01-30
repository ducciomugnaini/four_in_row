package com.softly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.softly.R;
import com.softly.utilities.VolleyRequestQueue;

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

        Button restButton = findViewById(R.id.btn_callRest);
        restButton.setOnClickListener(v -> {
            final TextView textView = findViewById(R.id.txt_result);

            String url = "https://jsonplaceholder.typicode.com/posts";

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    (String response) -> {
                        textView.setText("Response is: " + response.substring(0, 500));
                    },
                    (VolleyError error) -> {
                        textView.setText("That didn't work!");
                    }
            );
            VolleyRequestQueue.getInstance(MenuActivity.this).addToRequestQueue(stringRequest);
        });
    }
}