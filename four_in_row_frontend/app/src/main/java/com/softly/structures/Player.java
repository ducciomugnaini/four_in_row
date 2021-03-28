package com.softly.structures;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Player {

    public String Name;
    public int Score;

    public JSONObject ToJSON() throws JSONException {
        Gson gson = new Gson();
        String jsonPlayer = gson.toJson(this, Object.class);
        return new JSONObject(jsonPlayer);
    }
}
