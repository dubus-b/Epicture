package eu.epitech.epicture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    public void SearchContent(View vue) {
        String Text = ((EditText)findViewById(R.id.ContentText)).getText().toString();
        eu.epitech.epicture.api.imgur.Client ImgurClient = new eu.epitech.epicture.api.imgur.Client();
        ImgurClient.SearchContentByName(this, Text, 0);
    }
}
