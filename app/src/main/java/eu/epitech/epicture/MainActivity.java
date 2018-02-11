package eu.epitech.epicture;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.erikagtierrez.multiple_media_picker.Gallery;

import java.util.ArrayList;

import eu.epitech.epicture.api.IPictureSearchingServices;
import eu.epitech.epicture.api.ISearchingPicturesServicesCallback;
import eu.epitech.epicture.database.Query;
import eu.epitech.epicture.database.table.*;

public class MainActivity extends AppCompatActivity {

    // DATABASE ACCESS DECLARATION
    public static Query database;

    // API INTERFACE DECLARATION
    public static IPictureSearchingServices _pixabay = new eu.epitech.epicture.api.pixabay.Client();
    public static IPictureSearchingServices _flickr = new eu.epitech.epicture.api.flickr.Client();
    public static IPictureSearchingServices _imgur = new eu.epitech.epicture.api.imgur.Client();

    // FAVORITE DECLARATION
    public static ArrayList<CardImage> favoriteList;
    private ArrayList<CardImage> imageList;

    // GALLERY DECLARATION
    private RecyclerView recyclerView;
    private CardImageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Query(this);
        favoriteList = new ArrayList<>();
        imageList = new ArrayList<>();
        dbFavorite.getAllUrl(favoriteList);
    }

    public void onSearchError() {
    }

    public void onFavoriteButtonClick(View view) {
        Intent favoriteIntent = new Intent(MainActivity.this, FavoriteActivity.class);
        startActivityForResult(favoriteIntent, 0);
    }

    public void realUploadActivity() {
        Intent intent= new Intent(this, Gallery.class);
        // Set the title
        intent.putExtra("title","Selection des images");
        // Mode 1 for both images and videos selection, 2 for images only and 3 for videos!
        intent.putExtra("mode",2);
        intent.putExtra("maxSelection",10);
        startActivityForResult(intent,42);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 42) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> selectionResult = data.getStringArrayListExtra("result");
                Intent upload_activity = new Intent(this, UploadActivity.class);
                upload_activity.putExtra("FilePaths", selectionResult);
                startActivityForResult(upload_activity, 1);
            }
        }
    }

    public void onImgurSearch(final View view) {
        search(_imgur, view);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 12: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(this, "Merci pour les droits de lecture", Toast.LENGTH_LONG).show();


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Merci d'accorder les droits de lecture pour vos photos", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


    public void onUploadButtonClick(View view) {
        if (Build.VERSION.SDK_INT > 22 && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (Build.VERSION.SDK_INT > 22) {
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Explain to the user why we need to read the contacts
                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        12);
            }
            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }
        else if (Build.VERSION.SDK_INT <= 22 || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            realUploadActivity();
            return ;
        }
        else if (Build.VERSION.SDK_INT > 22 && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "Merci d'accorder les droits de lecture pour vos photos", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void onFlickrSearch(View view) {
        search(_flickr, view);
    }

    public void onPixabaySearch(View view) {
        search(_pixabay, view);
    }

    public void onSettingClick(View view) {
        Intent goSettings = new Intent(this, SettingsActivity.class);
        startActivityForResult(goSettings, 0);
    }

    private void search(IPictureSearchingServices service, final View view) {
        final ProgressBar CircleIt = findViewById(R.id.Loading);
        String Text = ((EditText) findViewById(R.id.search_input)).getText().toString();
        CircleIt.setVisibility(View.VISIBLE);
        service.SearchContentByName(this, Text, 0, new ISearchingPicturesServicesCallback() {

            @Override
            public void onSuccess(ArrayList<String> imageUrlList) {
                Log.i("Image Gallery", imageUrlList.size() + " images have been found");
                imageList.clear();
                for (String url : imageUrlList) {
                    imageList.add(new CardImage(url));
                }
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
        adapter.setOnItemClickListener(new CardImageAdapter.OnItemClickListener() {
            @Override
            public void onDownloadClick(int position) {

            }

            @Override
            public void onFavoriteClick(int position) {
                CardImage current = imageList.get(position);
                Log.i("FavoriteClick", "Click on " + current.getUrl());
                current.switchFavorite();
                adapter.notifyItemChanged(position);
            }
        });
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
