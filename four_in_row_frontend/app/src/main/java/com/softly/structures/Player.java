package com.softly.structures;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class Player {

    public String Nickname;
    public int Wins;
    public int Loses;

    public JSONObject ToJSON() throws JSONException {
        Gson gson = new Gson();
        String jsonPlayer = gson.toJson(this, Object.class);
        return new JSONObject(jsonPlayer);
    }
}
