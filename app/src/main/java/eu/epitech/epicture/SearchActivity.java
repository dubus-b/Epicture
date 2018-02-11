package eu.epitech.epicture;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;

import eu.epitech.epicture.api.ISearchingPicturesServicesCallback;
import eu.epitech.epicture.api.imgur.Client;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ProgressBar CircleIt = findViewById(R.id.Loading);
        CircleIt.setVisibility(View.INVISIBLE);
    }

    public void SearchContent(View vue) {
        final ProgressBar CircleIt = findViewById(R.id.Loading);
        String Text = ((EditText)findViewById(R.id.ContentText)).getText().toString();
        eu.epitech.epicture.api.IPictureSearchingServices ImgurClient = new eu.epitech.epicture.api.flickr.Client();
        CircleIt.setVisibility(View.VISIBLE);
        ImgurClient.SearchContentByName(this, Text, 0, new ISearchingPicturesServicesCallback() {
            @Override
            public void onSuccess(ArrayList<String> Results) {
                CircleIt.setVisibility(View.GONE);
                int id = 0;
                for (String Item : Results) {
                    Log.d("Content id " + id++ + " = ", Item);
                }
            }

            @Override
            public void onError() {
                CircleIt.setVisibility(View.GONE);
                Log.d("error ", ":/");
            }
        });
    }

}
