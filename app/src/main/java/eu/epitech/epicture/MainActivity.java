package eu.epitech.epicture;

import android.support.constraint.ConstraintLayout;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // déclaré interface imgur en private
    // déclaré interfacxe flickr en private
    // déclaré interface pixabay en private

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

    public void onImgurSearch(View view) {
        onSearchClick(view);
    }


    public void onFlickrSearch(View view) {
        onSearchClick(view);
    }


    public void onPixabaySearch(View view) {
        onSearchClick(view);
    }

    public void onSearchClick(View view) {
        FloatingActionButton button = findViewById(R.id.floating_search_button);
        RelativeLayout searchLayout = findViewById(R.id.search_layout);
        if (searchLayout.getVisibility() == View.INVISIBLE) {
            searchLayout.setVisibility(View.VISIBLE);
            button.setImageResource(R.drawable.ic_close);
        } else {
            searchLayout.setVisibility(View.INVISIBLE);
            button.setImageResource(R.drawable.ic_search);
        }
    }
}
