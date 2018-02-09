package eu.epitech.epicture;

import android.support.constraint.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import eu.epitech.epicture.api.IPictureSearchingServices;
import eu.epitech.epicture.api.ISearchingPicturesServicesCallback;
import eu.epitech.epicture.api.imgur.Client;

public class MainActivity extends AppCompatActivity {

    // déclaré interface imgur en private
    // déclaré interfacxe flickr en private
    // déclaré interface pixabay en private
    private eu.epitech.epicture.api.IPictureSearchingServices _pixabay = new eu.epitech.epicture.api.pixabay.Client();
    private eu.epitech.epicture.api.IPictureSearchingServices _flickr = new eu.epitech.epicture.api.flickr.Client();
    private eu.epitech.epicture.api.IPictureSearchingServices _imgur = new eu.epitech.epicture.api.imgur.Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void onSearchSuccess(ArrayList<String> images) {
        for (String imgAddrs : images) {

        }
    }

    public void onSearchError() {
    }

    public void searchDisplay(int visibility) {
    }

    public void onFavoriteButtonClick(View view) {

    }

    public void onUploadButtonClick(View view) {
    }


    public void onSettingClick(View view) {

    }

    private void search(IPictureSearchingServices service, final View view) {
        final ProgressBar CircleIt = findViewById(R.id.Loading);
        String Text = ((EditText)findViewById(R.id.search_input)).getText().toString();
        CircleIt.setVisibility(View.VISIBLE);
        service.SearchContentByName(this, Text, 0, new ISearchingPicturesServicesCallback() {
            @Override
            public void onSuccess(ArrayList<String> Results) {
                CircleIt.setVisibility(View.GONE);
                int id = 0;
                Log.d("Nb results: ", String.valueOf(Results.size()));
                for (String Item : Results) {
                    Log.d("Content id " + id++ + " = ", Item);
                }
                onSearchClick(view);
            }

            @Override
            public void onError() {
                CircleIt.setVisibility(View.GONE);
                Log.d("error ", ":/");
            }
        });
    }

    public void onImgurSearch(final View view) {
        search(_imgur, view);
    }


    public void onFlickrSearch(View view) {
        search(_flickr, view);
    }


    public void onPixabaySearch(View view) {
        search(_pixabay, view);
    }

    public void onSearchClick(View view) {
        FloatingActionButton button = findViewById(R.id.floating_search_button);
        ConstraintLayout searchLayout = findViewById(R.id.search_layout);
        if (searchLayout.getVisibility() == View.INVISIBLE) {
            searchLayout.setVisibility(View.VISIBLE);
            button.setImageResource(R.drawable.ic_close);
        } else {
            searchLayout.setVisibility(View.INVISIBLE);
            button.setImageResource(R.drawable.ic_search);
        }
    }
}
