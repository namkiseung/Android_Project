package com.namkit.namki.bookmarkapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowseActivity extends AppCompatActivity {
private String URL = "url";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        Intent intent = getIntent();
        String url = intent.getStringExtra(URL);

        WebView webView = (WebView)findViewById(R.id.webView);
        //새 창 안뜨게 하려고
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
