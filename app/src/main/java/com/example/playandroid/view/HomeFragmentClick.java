package com.example.playandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.playandroid.R;

import java.net.MalformedURLException;
import java.net.URL;

public class HomeFragmentClick extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment_click);
        Intent intent=getIntent();
        String link=intent.getStringExtra("link");
        WebView webView=(WebView) findViewById(R.id.home_fragment_click_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
    }
}