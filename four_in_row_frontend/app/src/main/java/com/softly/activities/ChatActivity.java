package com.softly.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import com.softly.R;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        HubConnection hubConnection = HubConnectionBuilder.create("http://10.0.2.2:57507/chat").build();
        TextView textView = findViewById(R.id.tvMain);
        ListView listView = findViewById(R.id.lvMessages);
        Button sendButton = findViewById(R.id.bSend);
        EditText editText = findViewById(R.id.etMessageText);

        List<String> messageList = new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChatActivity.this,
                android.R.layout.simple_list_item_1, messageList);
        listView.setAdapter(arrayAdapter);

        hubConnection.on("Send", (message)-> {
            runOnUiThread(() -> {
                arrayAdapter.add(message);
                arrayAdapter.notifyDataSetChanged();
            });
        }, String.class);

        sendButton.setOnClickListener(view -> {
            String message = editText.getText().toString();
            editText.setText("");
            try {
                hubConnection.send("Send", "JavaClient", message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        new HubConnectionTask().execute(hubConnection);
    }

    class HubConnectionTask extends AsyncTask<HubConnection, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(HubConnection... hubConnections) {
            HubConnection hubConnection = hubConnections[0];
            hubConnection.start().blockingAwait();
            return null;
        }
    }
}