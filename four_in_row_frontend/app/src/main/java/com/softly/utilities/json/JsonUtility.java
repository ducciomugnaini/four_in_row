package com.softly.utilities.json;

import android.content.Context;

import com.google.gson.Gson;
import com.softly.R;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtility {

    public static String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            return new String(bytes);
        } catch (IOException ex) {
            return null;
        }
    }

    public static Configuration GetConfiguration(Context context) {
        String myJson = inputStreamToString(context.getResources().openRawResource(R.raw.config));
        return new Gson().fromJson(myJson, Configuration.class);
    }

}
