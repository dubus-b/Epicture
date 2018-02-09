package eu.epitech.epicture.api.flickr;

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
import eu.epitech.epicture.api.IPictureSearchingServices;
import eu.epitech.epicture.api.ISearchingPicturesServicesCallback;

/**
 * Created by Louis Giraud on 05/02/2018.
 */

public class Client implements IPictureSearchingServices {

    public static String _ID = "266eedbcdf1fa75587fc4490595a51fd";
    public static String _SECRET = "48eb4bfd34c9ef85";

    private ArrayList<Pair<RequestQueue, CustomStringRequest>> _requests = new ArrayList<>();


    @Override
    public String SearchContentByName(Context ctxt, String ContentName, int maximum, ISearchingPicturesServicesCallback Callback) {
        String Content_encoded = null;
        try {
            Content_encoded = URLEncoder.encode(ContentName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(ctxt);
        final String Tag = UUID.randomUUID().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.flickr.com/services/rest/?method=flickr.photos.search&content_type=4&api_key=" + _ID + "&format=json&text=" + Content_encoded,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Answer: ", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("SearchContentByName :/", error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Client-ID " + _ID);
                return params;
            }
        };
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
