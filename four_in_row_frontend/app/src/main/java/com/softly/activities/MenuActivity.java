package com.softly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.softly.R;
import com.softly.structures.Grid;
import com.softly.utilities.network.NetworkUtility;

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

            // this vs MenuActivity.this
            // https://stackoverflow.com/questions/10102151/whats-the-difference-between-this-and-activity-this
            NetworkUtility.GetMove(MenuActivity.this, new Grid("dummyField"));

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