package eu.epitech.epicture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import eu.epitech.epicture.database.table.*;

public class FavoriteActivity extends AppCompatActivity {

    private ArrayList<CardImage> favoriteList;

    private RecyclerView recyclerView;
    private CardImageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favoriteList = new ArrayList<>();
        dbFavorite.getAllUrl(favoriteList);

        recyclerView = findViewById(R.id.favorite_gallery);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        adapter = new CardImageAdapter(getApplicationContext(), favoriteList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CardImageAdapter.OnItemClickListener() {

            @Override
            public void onDownloadClick(int position) {

            }

            @Override
            public void onFavoriteClick(int position) {
                CardImage current = favoriteList.get(position);
                Log.i("FavoriteClick", "Click on " + current.getUrl());
                current.switchFavorite();
                adapter.notifyItemChanged(position);
            }
        });
    }
}
