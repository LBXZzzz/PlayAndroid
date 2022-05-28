package com.example.playandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.playandroid.R;

import java.net.MalformedURLException;
import java.net.URL;

public class WebViewClick extends AppCompatActivity {
    //这个是点击跳转文章的WebView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment_click);
        Intent intent=getIntent();
        String link=intent.getStringExtra("link");
        WebView webView=(WebView) findViewById(R.id.home_fragment_click_web_view);
        ProgressBar progressBar=(ProgressBar) findViewById(R.id.Web_view_progressbar);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}