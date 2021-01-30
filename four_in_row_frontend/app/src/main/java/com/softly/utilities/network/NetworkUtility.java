package com.softly.utilities.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.softly.activities.MenuActivity;
import com.softly.utilities.VolleyRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkUtility {

    public static String token = "";
    public static String username = "test";
    public static String password = "test";
    public static String authorizeUrl = "http://10.0.2.2:57507/users/authenticate";
    public static String networkTag = "NETWORK";

    public static void RequireToken(Context context) {
        Log.d(networkTag, "RequireToken started.");
        try {
            JSONObject authorizeContent = new JSONObject();
            authorizeContent.put("Username", username);
            authorizeContent.put("Password", password);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    authorizeUrl,
                    authorizeContent,
                    (JSONObject response) -> {
                        try {
                            token = response.getString("token");
                            Log.e(networkTag, "received token: " + token);
                        } catch (JSONException e) {
                            Log.e(networkTag, "token missing.");
                            e.printStackTrace();
                        }
                    },
                    (VolleyError error) -> {
                        Log.e(networkTag, "onErrorResponse fired: "+error);
                    }
            );

            VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);

        } catch (JSONException e) {
            Log.e(networkTag, "RequireToken error");
            e.printStackTrace();
        }
    }


}
