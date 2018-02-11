package eu.epitech.epicture;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.UrlQuerySanitizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;
import java.sql.Date;
import java.util.UUID;

import eu.epitech.epicture.api.imgur.Client;
import eu.epitech.epicture.database.table.*;

public class AddNewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        final WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.clearHistory();
        myWebView.clearCache(true);
        myWebView.clearFormData();
        myWebView.clearSslPreferences();
        WebStorage.getInstance().deleteAllData();
        myWebView.getSettings().setAppCacheEnabled(false);
        CookieManager.getInstance().removeAllCookies(new ValueCallback<Boolean>() {

            @Override
            public void onReceiveValue(final Boolean value) {
                myWebView.getSettings().setAllowContentAccess(false);
                myWebView.getSettings().setUserAgentString(UUID.randomUUID().toString());
                myWebView.getSettings().setUserAgentString("My user agent string, here");
                myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                CookieManager.getInstance().setAcceptThirdPartyCookies(myWebView, false);
                CookieManager.getInstance().flush();
                Boolean cookies = CookieManager.getInstance().hasCookies();
                myWebView.loadUrl("https://api.imgur.com/oauth2/authorize?client_id=" + Client._ID + "&response_type=token");
                myWebView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url.startsWith("https://epcture.com/callback")) {
                            String new_url = "https://epcture.com/callback?" + url.substring("https://epcture.com/callback#".length(), url.length());
                            UrlQuerySanitizer ressource = new UrlQuerySanitizer(new_url);

                            String access_token = ressource.getValue("access_token");
                            String refresh_token = ressource.getValue("refresh_token");
                            String user_name = ressource.getValue("account_username");
                            String account = ressource.getValue("account_id");
                            if (access_token == null || refresh_token == null || user_name == null || account == null) {
                                setResult(RESULT_CANCELED);
                                finish();
                                return super.shouldOverrideUrlLoading(view, url); // return false;
                            }

                            SQLiteDatabase db1 = MainActivity.database.getReadableDatabase();

                            Cursor cursor = db1.rawQuery("SELECT * FROM " + dbImgurAccount.TABLE + " WHERE account_id = ?;", new String[]{account});
                            if (cursor.moveToFirst()) {
                                setResult(RESULT_CANCELED);
                                finish();
                            }

                            db1.close();

                            ContentValues values = new ContentValues();
                            values.put(dbImgurAccount.COL_REFRESH_TOKEN, refresh_token);
                            values.put(dbImgurAccount.COL_ACCESS_TOKEN, access_token);
                            values.put(dbImgurAccount.COL_ACCOUNT_ID, account);
                            values.put(dbImgurAccount.COL_ACCOUNT_USERNAME, user_name);
                            SQLiteDatabase db2 = MainActivity.database.getWritableDatabase();

                            db2.insertWithOnConflict(dbImgurAccount.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                            db2.close();

                            ContentValues value = new ContentValues();
                            value.put(dbAccount.COL_ACCOUNT_SERVICE, "imgur");
                            value.put(dbAccount.COL_ACCOUNT_USTR, user_name);
                            SQLiteDatabase db3 = MainActivity.database.getWritableDatabase();

                            db3.insertWithOnConflict(dbAccount.TABLE, null, value, SQLiteDatabase.CONFLICT_IGNORE);
                            db3.close();
                            setResult(RESULT_OK);
                            finish();
                        }

                        // load the webpage from url: login and grant access

                        return super.shouldOverrideUrlLoading(view, url); // return false;
                    }
                });
            }
        });

    }
}
