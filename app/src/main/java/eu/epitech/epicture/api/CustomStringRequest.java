package eu.epitech.epicture.api;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

/**
 * Created by oscar on 07/02/2018.
 */

public class CustomStringRequest {
    private StringRequest _request = null;
    private Boolean _status = false;
    private ArrayList<String> _answers = null;
    public CustomStringRequest(StringRequest req) {
        _request = req;
    }

    public void SetAnswers(ArrayList<String> answers) {
        _status = true;
        _answers = answers;
    }

    public ArrayList<String> GetAnswers() {
        return _answers;
    }

    public String GetTag() {
        return (String)_request.getTag();
    }

    public Boolean GetStatus() {
        return _status;
    }
}
