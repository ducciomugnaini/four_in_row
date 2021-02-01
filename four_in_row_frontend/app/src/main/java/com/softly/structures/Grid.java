package com.softly.structures;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class Grid {

    public String field;

    public Grid(String field) {
        this.field = field;
    }

    public JSONObject ToJSON() throws JSONException {
        Gson gson = new Gson();
        String jsonGrid = gson.toJson(this, Object.class);
        return new JSONObject(jsonGrid);
    }

}
