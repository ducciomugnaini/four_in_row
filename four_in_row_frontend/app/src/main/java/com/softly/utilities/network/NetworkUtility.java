package com.softly.utilities.network;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.softly.R;
import com.softly.structures.Grid;
import com.softly.structures.Move;
import com.softly.utilities.VolleyRequestQueue;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NetworkUtility {

    public static String token = "";
    public static String username = "test";
    public static String password = "test";
    public static String authorizeUrl = "http://10.0.2.2:57507/users/authenticate";
    public static String moveRequestUrl = "http://10.0.2.2:57507/api/move";
    public static String networkTag = "NETWORK";
    public static int UNAUTHORIZED = 401;

    public static void RefreshToken(Context context, Grid grid) {
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
                            GetMove(context, grid);
                        } catch (JSONException e) {
                            Log.e(networkTag, "token missing.");
                            e.printStackTrace();
                        }
                    },
                    (VolleyError error) -> {
                        Log.e(networkTag, "onErrorResponse fired: " + error);
                    }
            );

            VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);

        } catch (JSONException e) {
            Log.e(networkTag, "RequireToken error");
            e.printStackTrace();
        }
    }

    public static void GetMove(Context context, Grid grid) {

        Log.d(networkTag, "GetMove started.");
        try {

            final TextView textView = ((Activity) context).findViewById(R.id.txt_result);

            JSONObject moveContent = grid.ToJSON();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    moveRequestUrl,
                    moveContent,
                    (JSONObject response) -> {
                        Move move = Move.FromJSON(response);
                        textView.setText("Move received on column: "+move.getColumn());
                        Log.e(networkTag, "received move: " + response);
                    },
                    (VolleyError error) -> {
                        Log.e(networkTag, "onErrorResponse fired: " + error);
                        if (error.networkResponse.statusCode == UNAUTHORIZED) {
                            RefreshToken(context, grid);
                        } else {
                            // irrecoverable errors. show error to user.
                            textView.setText("irrecoverable errors. show error to user");
                        }
                    }
            ){
                @Override
                public Map<String, String> getHeaders() {
                    Map<String, String>  headers = new HashMap<>();
                    headers.put("Authorization", "Bearer " + token);
                    return headers;
                }
            };

            VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);

        } catch (JSONException e) {
            Log.e(networkTag, "RequireToken error");
            e.printStackTrace();
        }
    }


}
