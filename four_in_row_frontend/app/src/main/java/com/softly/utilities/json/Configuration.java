package com.softly.utilities.json;

import com.google.gson.annotations.SerializedName;

public class Configuration {

    @SerializedName("AuthorizeUrl")
    private String authorizeUrl;

    @SerializedName("MoveRequestUrl")
    private String moveRequestUrl;

    @SerializedName("LobbyNameRequestUrl")
    private String getLobbyNameRequestUrl;

    @SerializedName("SignalRUrl")
    private String signalRUrl;

    public String getAuthorizeUrl() { return authorizeUrl; }

    public String getMoveRequestUrl() { return moveRequestUrl; }

    public String getGetLobbyNameRequestUrl() { return getLobbyNameRequestUrl; }

    public String getSignalRUrl(){ return signalRUrl; }
}
