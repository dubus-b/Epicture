package eu.epitech.epicture.favorite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import eu.epitech.epicture.CardImageAdapter;
import eu.epitech.epicture.R;

public class FavoriteActivity extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private CardImageAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        ArrayList<String> favoriteUrlList = null;

        recyclerView = findViewById(R.id.favorite_gallery);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        adapter = new CardImageAdapter(getApplicationContext(), favoriteUrlList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
