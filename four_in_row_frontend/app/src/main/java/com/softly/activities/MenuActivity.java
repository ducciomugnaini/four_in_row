package com.softly.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.softly.R;
import com.softly.utilities.VolleyRequestQueue;

import java.net.URL;

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
            /*AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute();*/

            final TextView textView = (TextView) findViewById(R.id.txt_result);

            // Instantiate the RequestQueue.
            //RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
            RequestQueue queue = VolleyRequestQueue.getInstance(MenuActivity.this).getRequestQueue();
            String url = "https://jsonplaceholder.typicode.com/posts";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            textView.setText("Response is: " + response.substring(0, 500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("That didn't work!");
                }
            });

            // Add the request to the RequestQueue.
            //queue.add(stringRequest);
            VolleyRequestQueue.getInstance(MenuActivity.this).addToRequestQueue(stringRequest);
        });
    }

    private class AsyncTaskRunner extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... params) {
            return "ciao";
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = findViewById(R.id.txt_result);
            textView.setText(result);
        }
    }


}