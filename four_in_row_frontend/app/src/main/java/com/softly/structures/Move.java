package com.softly.structures;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Move {

    @SerializedName("column")
    private int Column;

    public int getColumn() {
        return Column;
    }

    public void setColumn(int column) {
        this.Column = column;
    }

    public static Move FromJSON(JSONObject jsonMove) {
        Gson gson = new Gson();
        return gson.fromJson(jsonMove.toString(), Move.class);
    }


}
