package eu.epitech.epicture.api.imgur;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

/*import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/


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

import eu.epitech.epicture.api.IPictureSearchingServices;
import eu.epitech.epicture.api.CustomStringRequest;
import eu.epitech.epicture.api.IPictureUploadServices;
import eu.epitech.epicture.api.ISearchingPicturesServicesCallback;
import eu.epitech.epicture.api.IUserManager;
import eu.epitech.epicture.api.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eu.epitech.epicture.api.IPictureServices;
import eu.epitech.epicture.api.User;

/**
 * Created by Louis Giraud on 05/02/2018.
 */

public class Client implements IPictureSearchingServices, IPictureUploadServices, IUserManager {

    public static String _ID = "8247912c0e0c59d";
    public static String _SECRET = "4a8211234046ca174d66124a49c0c913efd00de0";
    private ArrayList<Pair<RequestQueue, CustomStringRequest>> _requests = new ArrayList<>();

    public String SearchContentByName(Context ctxt, String ContentName, int Max, final ISearchingPicturesServicesCallback Callback) {
        String Content_encoded = null;
        try {
            Content_encoded = URLEncoder.encode(ContentName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(ctxt);
        final String Tag = UUID.randomUUID().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.imgur.com/3/gallery/search/?q=" + Content_encoded, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                try {
                    ArrayList<String> Answers = new ArrayList<>();
                    JSONObject jsonAnswer = new JSONObject(response);
                    JSONArray data = jsonAnswer.getJSONArray("data");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject post = data.optJSONObject(i);
                        try {
                            JSONArray images = post.getJSONArray("images");
                            for (int j = 0; j < images.length(); j++) {
                                JSONObject image = images.optJSONObject(j);
                                Answers.add(image.getString("link"));
                                image.getString("link");
                            }
                        } catch (JSONException e) {

                        }
                    }
                    Callback.onSuccess(Answers);
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
        Queue.add(stringRequest);
        return Tag;
    }

    @Override
    public int ClearCache() {
        return 0;
    }

    @Override
    public boolean UploadImage(String FileLocator, int UserId) {

        return false;
    }

    @Override
    public boolean AddUser() {
        return false;
    }

    @Override
    public void DeleteUser(int UserId) {

    }

    @Override
    public User GetUser(int UserId) {
        return null;
    }

    @Override
    public ArrayList<User> GetUsers() {
        return null;
    }
}
