package eu.epitech.epicture;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import eu.epitech.epicture.api.IPictureSearchingServices;
import eu.epitech.epicture.database.table.dbFavorite;

/**
 * Created by oscar on 10/02/2018.
 */

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void ManageUsersAccount(View view) {
        Intent management_users_account = new Intent(this, UsersManagementActivity.class);
        startActivity(management_users_account);
    }

    public void clear_cache(View view) {
        IPictureSearchingServices imgur = MainActivity._imgur;
    }

    public void clear_favorite(View view) {
        ArrayList<CardImage> favorite = new ArrayList<>();
        dbFavorite.getAllUrl(favorite);
        for (CardImage cardImage : favorite) {
            dbFavorite.delUrl(cardImage.getUrl());
        }
    }
}
