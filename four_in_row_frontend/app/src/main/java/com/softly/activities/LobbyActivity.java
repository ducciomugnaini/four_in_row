package com.softly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.signalr.Action2;
import com.microsoft.signalr.HubConnection;
import com.orhanobut.logger.Logger;
import com.softly.R;
import com.softly.signalr.SignalRSingleton;
import com.softly.structures.Player;
import com.softly.utilities.network.NetworkUtility;

import java.util.concurrent.atomic.AtomicReference;

public class LobbyActivity extends AppCompatActivity {

    public static String lobbyActivityTag = "ACTIVITY_LOBBY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        Player player = new Player();
        player.Name = "Android";
        player.Score = 0;

        SignalRSingleton app = (SignalRSingleton) getApplication();
        app.ResetHubConnection();
        AtomicReference<HubConnection> hubConnection = new AtomicReference<>();

        NetworkUtility.GetLobbyName(this, player, lobby -> {

            Logger.d(lobbyActivityTag, "Lobby name received: " + lobby.Name);

            // mostrare la conferma di sottoscrizione

            Action2<String, String> onReceiveSubscriptionConfirm = (clientName, message) -> {
                // la connessione successiva immediata allo startgame potrebbe causare problemi
                // sul thread della gui
                /*runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                });*/
                Logger.d( "=======================> onReceiveSubscriptionConfirm");
            };
            Action2<String, String> onReceiveStartGame = (clientName, message) -> {
                runOnUiThread(() -> {
                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    Logger.d("======================> onReceiveStartGame");
                    Intent intent = new Intent(LobbyActivity.this, BoardActivity.class);
                    startActivity(intent);
                });
            };

            hubConnection.set(app.getHubConnection(lobby, onReceiveSubscriptionConfirm, onReceiveStartGame));

            return null;
        });
    }

    @Override
    public void onBackPressed() {

        // todo remove from group
        // hubConnection.invoke(Void.class, "RemoveFromGroup", ClientName, GroupName);

        SignalRSingleton app = (SignalRSingleton) getApplication();
        app.ResetHubConnection();
        super.onBackPressed();
    }
}