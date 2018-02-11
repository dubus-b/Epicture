package eu.epitech.epicture;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Date;

import eu.epitech.epicture.api.User;

public class UploadActivity extends AppCompatActivity {

    private RecyclerView _usersListView;
    private UsersAdaptater _usersAdapter;
    private RecyclerView.LayoutManager _usersLayout;
    private ArrayList<String> _filepaths;
    private int _nb_checked;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _nb_checked = 0;
        setContentView(R.layout.upload_activity);
        final ArrayList<User> Users = new ArrayList<>();
        _filepaths = (ArrayList<String>) getIntent().getSerializableExtra("FilePaths");
        Users.add(new User(1, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(2, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(3, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(4, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(5, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(6, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(7, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(8, new Date(), "jeantoast42@gmail.czm", "imgur"));
        Users.add(new User(9, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(10, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(11, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(12, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(13, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(14, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(14, new Date(), "jeantoast42@gmail.com", "imgur"));
        Users.add(new User(15, new Date(), "jeantoast42@gmail.czm", "imgur"));

        _usersListView = findViewById(R.id.users_recycler_view);
        _usersListView.setHasFixedSize(true);
        _usersLayout = new LinearLayoutManager(this);
        _usersAdapter = new UsersAdaptater(Users, UsersAdaptater.UPLOAD_ACTIVITY);

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
                Boolean new_check_status = ((CheckBox)_usersListView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.AccountSelect)).isChecked();
                if (_nb_checked == 1 && new_check_status == false) {
                    // last one checked removed
                    FloatingActionButton fab = findViewById(R.id.floating_action_upload_activity);
                    fab.setVisibility(View.INVISIBLE);
                }
                else if (_nb_checked == 0 && new_check_status == true) {
                    FloatingActionButton fab = findViewById(R.id.floating_action_upload_activity);
                    fab.setVisibility(View.VISIBLE);
                }
                if (new_check_status)
                    ++_nb_checked;
                else
                    --_nb_checked;
            }
        });
    }

    public void UploadOnSelectedPlatform(View view) {

    }
}
