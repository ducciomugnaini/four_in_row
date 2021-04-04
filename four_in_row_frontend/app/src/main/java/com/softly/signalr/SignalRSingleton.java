package com.softly.signalr;

import android.app.Application;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.microsoft.signalr.Action2;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.orhanobut.logger.Logger;
import com.softly.structures.Lobby;
import com.softly.structures.Player;
import com.softly.utilities.json.Configuration;
import com.softly.utilities.json.JsonUtility;

import io.reactivex.functions.Function3;

public class SignalRSingleton extends Application {

    private HubConnection hubConnection;
    private Lobby hubConnectionLobby;

    public static Configuration configuration = null;

    public HubConnection GetHubConnection(){ return hubConnection; }

    public HubConnection GetHubConnection(Player clientPlayer, Lobby lobby,
                                          Action2<String, String> onReceiveSubscriptionConfirm,
                                          Action2<String, String> onReceiveStartGame) {

        // todo forse un resetHubConnection ?
        hubConnectionLobby = lobby;
        if (hubConnection == null) {

            if (configuration == null) {
                configuration = JsonUtility.GetConfiguration(this);
                if (TextUtils.isEmpty(configuration.getSignalRUrl())) {
                    Logger.e("SignalRHubUrl keys missing");
                    throw new RuntimeException("SignalRHubUrl keys missing");
                }
            }

            hubConnection = HubConnectionBuilder.create(configuration.getSignalRUrl()).build();
            hubConnection.on("ReceiveSubscriptionConfirm", onReceiveSubscriptionConfirm, String.class, String.class);
            hubConnection.on("ReceiveStartGame", onReceiveStartGame, String.class, String.class);

            new HubConnectionTask().execute(() -> {
                hubConnection.start().blockingAwait();
                hubConnection.invoke(Void.class, "SendSubscriptionToGroup", clientPlayer.Name, hubConnectionLobby.Name);
            });
        }

        return hubConnection;
    }

    public void RemoveFromLobby(Player player){
        if(hubConnection != null)
            hubConnection.invoke(Void.class, "SendRemoveFromLobby", player.Name, hubConnectionLobby.Name);
    }

    public void ResetHubConnection(){
        hubConnection = null;
        hubConnectionLobby = null;
    }

    static class HubConnectionTask extends AsyncTask<Runnable, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Runnable... hubHandlers) {
            Runnable hubConnection = hubHandlers[0];
            hubConnection.run();
            return null;
        }
    }
}
