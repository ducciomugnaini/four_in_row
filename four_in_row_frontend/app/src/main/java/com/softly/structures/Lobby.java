package com.softly.structures;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Lobby {

    @SerializedName("name")
    public String Name;

    public static Lobby FromJSON(JSONObject lobby) {
        Gson gson = new Gson();
        return gson.fromJson(lobby.toString(), Lobby.class);
    }
}
