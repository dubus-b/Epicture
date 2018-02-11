package eu.epitech.epicture;

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
import java.util.UUID;

import eu.epitech.epicture.api.imgur.Client;

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
            public void onReceiveValue(Boolean value) {
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
