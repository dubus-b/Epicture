package eu.epitech.epicture;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;

import android.os.Bundle;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;

import eu.epitech.epicture.api.IPictureSearchingServices;
import eu.epitech.epicture.api.ISearchingPicturesServicesCallback;
import eu.epitech.epicture.database.Query;
import eu.epitech.epicture.database.table.*;

public class MainActivity extends AppCompatActivity {

    // DATABASE ACCESS DECLARATION
    public static Query database;

    // API INTERFACE DECLARATION
    private IPictureSearchingServices _pixabay = new eu.epitech.epicture.api.pixabay.Client();
    private IPictureSearchingServices _flickr = new eu.epitech.epicture.api.flickr.Client();
    private IPictureSearchingServices _imgur = new eu.epitech.epicture.api.imgur.Client();

    // FAVORITE DECLARATION
    public static ArrayList<String> favoriteList;
    private ArrayList<String> imageList;

    // GALLERY DECLARATION
    private RecyclerView recyclerView;
    private CardImageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Query(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onSearchError() {
    }

    public void onFavoriteButtonClick(View view) {
        Intent favoriteIntent = new Intent(MainActivity.this, FavoriteActivity.class);
        startActivityForResult(favoriteIntent, 0);
    }

    public void onUploadButtonClick(View view) {
    }


    public void onSettingClick(View view) {

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

    private void search(IPictureSearchingServices service, final View view) {
        final ProgressBar CircleIt = findViewById(R.id.Loading);
        String Text = ((EditText) findViewById(R.id.search_input)).getText().toString();
        CircleIt.setVisibility(View.VISIBLE);
        service.SearchContentByName(this, Text, 0, new ISearchingPicturesServicesCallback() {

            @Override
            public void onSuccess(ArrayList<String> imageUrlList) {
                Log.i("Image Gallery", imageUrlList.size() + " images have been found");
                imageList = imageUrlList;
                updateUI();
                CircleIt.setVisibility(View.GONE);
                onSearchClick(view);
            }

            @Override
            public void onError() {
                CircleIt.setVisibility(View.GONE);
                Log.d("error ", ":/");
            }
        });
    }

    public void updateUI() {
        recyclerView = findViewById(R.id.favorite_gallery);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new CardImageAdapter(getApplicationContext(), imageList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
