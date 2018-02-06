package eu.epitech.epicture.api.imgur;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eu.epitech.epicture.api.IPictureServices;

/**
 * Created by Louis Giraud on 05/02/2018.
 */

public class Client implements IPictureServices {

    public static String _ID = "4347b0730ed2272";
    public static String _SECRET = "308ada6dacfaa4cbc4ccd128c859cde7b8677725";

    @Override
    public LinkedList<String> SearchContentByName(Context ctxt, String ContentName, int MaximumResults) {
        LinkedList<String> Results = new LinkedList<String>();
        RequestQueue queue = Volley.newRequestQueue(ctxt);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.imgur.com/3/gallery/search/?q=" + ContentName,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonAnswer = new JSONObject(response);
                            JSONArray data = jsonAnswer.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject post = data.optJSONObject(i);
                                try {
                                    JSONArray images = post.getJSONArray("images");
                                    for (int j = 0; j < images.length(); j++) {
                                        JSONObject image = images.optJSONObject(j);
                                        image.getString("link");
                                    }
                                }
                                catch (JSONException e) {

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SearchContentByName :/", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Client-ID " + _ID);
                return params;
            }
        };;
// Add the request to the RequestQueue.
        queue.add(stringRequest);
        return null;
    }


    @Override
    public Boolean UploadImage(String FileLocation) {
        return null;
    }
}
