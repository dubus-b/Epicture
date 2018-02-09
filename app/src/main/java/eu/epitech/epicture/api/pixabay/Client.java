package eu.epitech.epicture.api.pixabay;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import eu.epitech.epicture.api.CustomStringRequest;
import eu.epitech.epicture.api.ISearchingPicturesServicesCallback;

/**
 * Created by oscar on 09/02/2018.
 */

public class Client implements eu.epitech.epicture.api.IPictureSearchingServices {

    private static String _API_KEY = "7953316-bab619b1203dacd2d92c9397c";
    private ArrayList<Pair<RequestQueue, CustomStringRequest>> _requests = new ArrayList<>();

    @Override
    public String SearchContentByName(Context ctxt, String content, int maximum, final ISearchingPicturesServicesCallback callback) {
        String Content_encoded = null;
        try {
            Content_encoded = URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(ctxt);
        final String Tag = UUID.randomUUID().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://pixabay.com/api/?key=" + _API_KEY + "&q=" + Content_encoded,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            ArrayList<String> Answers = new ArrayList<>();
                            JSONObject jsonAnswer = new JSONObject(response);
                            JSONArray hits = jsonAnswer.getJSONArray("hits");
                            for (int IdHits = 0; IdHits < hits.length(); ++IdHits) {
                                JSONObject hit = hits.optJSONObject(IdHits);
                                Log.d("IdHits " + IdHits + ": ", hit.getString("previewURL"));
                            }
                            callback.onSuccess(Answers);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SearchContentByName :/", error.toString());
            }
        });
        RequestQueue Queue = Volley.newRequestQueue(ctxt);
        stringRequest.setTag(Tag);
        CustomStringRequest Creq = new CustomStringRequest(stringRequest);
        Pair<RequestQueue, CustomStringRequest> new_pair = new Pair<>(Queue, Creq);
        _requests.add(new_pair);
        Log.d("tag returned1 = ", Tag);
        Queue.add(stringRequest);
        return Tag;
    }
}
