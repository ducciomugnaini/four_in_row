package com.softly.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.softly.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    public static String GroupName = "GROUP_CODE";
    public static String ClientName = "Android Client";

    HubConnection hubConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        hubConnection = HubConnectionBuilder.create("http://10.0.2.2:57507/chat").build();
        TextView textView = findViewById(R.id.tvMain);
        ListView listView = findViewById(R.id.lvMessages);
        Button sendButton = findViewById(R.id.bSend);
        EditText editText = findViewById(R.id.etMessageText);

        List<String> messageList = new ArrayList<String>();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ChatActivity.this,
                android.R.layout.simple_list_item_1, messageList);
        listView.setAdapter(arrayAdapter);

        // on Server.Send receiving
        hubConnection.on("Send", (message) -> {
            runOnUiThread(() -> {
                arrayAdapter.add(message);
                arrayAdapter.notifyDataSetChanged();
            });
        }, String.class);

        // on Server.SendToGroup receiving
        hubConnection.on("SendToGroup", (name, message, groupId) -> {
            runOnUiThread(() -> {
                arrayAdapter.add(name + ": " + message + " @" + groupId);
                arrayAdapter.notifyDataSetChanged();
            });
        }, String.class, String.class, String.class);

        sendButton.setOnClickListener(view -> {
            String message = editText.getText().toString();
            editText.setText("");
            try {
                // hubConnection.send("Send", "JavaClient", message);
                hubConnection.invoke(Void.class, "SendToGroup", ClientName, message, GroupName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        new HubConnectionTask().execute(hubConnection);
    }

    @Override
    public void onBackPressed() {
        hubConnection.invoke(Void.class, "RemoveFromGroup", ClientName, GroupName);
        super.onBackPressed();
    }

    class HubConnectionTask extends AsyncTask<HubConnection, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(HubConnection... hubConnections) {
            HubConnection hubConnection = hubConnections[0];
            hubConnection.start().blockingAwait();
            hubConnection.invoke(Void.class, "AddToGroup", ClientName, GroupName);
            return null;
        }
    }
}