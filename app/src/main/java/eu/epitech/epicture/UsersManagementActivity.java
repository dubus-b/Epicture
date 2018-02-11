package eu.epitech.epicture;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import eu.epitech.epicture.api.User;
import eu.epitech.epicture.database.table.dbAccount;
import eu.epitech.epicture.database.table.dbImgurAccount;

public class UsersManagementActivity extends AppCompatActivity {

    private RecyclerView _usersListView;
    private UsersAdaptater _usersAdapter;
    private RecyclerView.LayoutManager _usersLayout;
    private ArrayList<String> _filepaths;
    private int _nb_checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_management);
        _nb_checked = 0;
        final ArrayList<User> Users = new ArrayList<>();
        SQLiteDatabase db = MainActivity.database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + dbAccount.TABLE + ";", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Users.add(new User(cursor.getColumnIndex("_id"),
                        cursor.getString(cursor.getColumnIndex(dbAccount.COL_ACCOUNT_USTR)),
                        cursor.getString(cursor.getColumnIndex(dbAccount.COL_ACCOUNT_SERVICE))));
                cursor.moveToNext();
            }
        }
        db.close();

        _usersListView = findViewById(R.id.users_list);
        _usersListView.setHasFixedSize(true);
        _usersLayout = new LinearLayoutManager(this);
        _usersAdapter = new UsersAdaptater(Users, UsersAdaptater.ACCOUNTS_MANAGEMENT_ACTIVITY);

        _usersListView.setLayoutManager(_usersLayout);
        _usersListView.setAdapter(_usersAdapter);

        _usersAdapter.set_click_listener(new UsersAdaptater.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {

            }

            @Override
            public void onInfoClick(int position) {

            }

            @Override
            public void onCheckboxClick(int position) {
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Toast.makeText(this, "L'ajout de votre compte est un succès", Toast.LENGTH_LONG).show();
                // Do something with the contact here (bigger example below)
            }
            else {
                Toast.makeText(this, "Désolé mais l'ajout à échoué", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void AddNewUser(View view) {
        Intent add_user = new Intent(this, AddNewUser.class);
        startActivityForResult(add_user, 1);
    }
}
