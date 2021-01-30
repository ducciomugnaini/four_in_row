package com.softly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.softly.R;
import com.softly.utilities.VolleyRequestQueue;
import com.softly.utilities.network.NetworkUtility;

import org.json.JSONArray;
import org.json.JSONObject;

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

            NetworkUtility.RequireToken(MenuActivity.this);

            /*String url = "https://jsonplaceholder.typicode.com/posts";
            Log.d("HEI", "bau");
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url,
                    null,
                    (JSONArray response) -> {
                        textView.setText(response.toString());
                    },
                    (VolleyError error) -> {
                        Log.e("ERROR", "onErrorResponse fired");
                    }
            );
            VolleyRequestQueue.getInstance(MenuActivity.this).addToRequestQueue(jsonObjectRequest);
            */
        });
    }
}