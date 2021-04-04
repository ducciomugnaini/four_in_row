package com.softly.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.microsoft.signalr.Action2;
import com.microsoft.signalr.HubConnection;
import com.orhanobut.logger.Logger;
import com.softly.R;
import com.softly.signalr.SignalRSingleton;
import com.softly.structures.Player;
import com.softly.utilities.network.NetworkUtility;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class LobbyActivity extends AppCompatActivity {

    private SignalRSingleton signalRManager;
    private static String lobbyActivityTag = "ACTIVITY_LOBBY";

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        player = new Player();
        player.Name = "Android Player " + UUID.randomUUID().toString().substring(0, 4);
        player.Score = 0;

        signalRManager = (SignalRSingleton) getApplication();
        signalRManager.ResetHubConnection();
        AtomicReference<HubConnection> hubConnection = new AtomicReference<>();

        NetworkUtility.GetLobbyName(this, player, lobby -> {

            Logger.d(lobbyActivityTag, "Lobby name received: " + lobby.Name);

            // todo mostrare la conferma di sottoscrizione

            Action2<String, String> onReceiveSubscriptionConfirm = (clientName, message) -> {
                // la connessione successiva immediata allo startgame potrebbe causare problemi
                // sul thread della gui
                /*runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                });*/
                Logger.d("=======================> onReceiveSubscriptionConfirm");
            };
            Action2<String, String> onReceiveStartGame = (clientName, message) -> {
                runOnUiThread(() -> {
                    //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    Logger.d("======================> onReceiveStartGame");
                    Intent intent = new Intent(LobbyActivity.this, BoardActivity.class);
                    startActivity(intent);
                });
            };

            hubConnection.set(signalRManager.GetHubConnection(player, lobby, onReceiveSubscriptionConfirm, onReceiveStartGame));

            return null;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("Lobby Activity Resumed");
    }

    @Override
    public void onBackPressed() {
        Logger.d("Lobby Activity Back Pressed");
        signalRManager.RemoveFromLobby(player);
        signalRManager.ResetHubConnection();
        super.onBackPressed();
    }
}