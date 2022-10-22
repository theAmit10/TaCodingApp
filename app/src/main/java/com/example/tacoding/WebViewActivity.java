package com.example.tacoding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

//    private static GeckoRuntime sRuntime;

    WebView webView ;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webviewOrg);
        url = getIntent().getStringExtra("url");

//        GeckoView view = findViewById(R.id.webviewOrg);
//        GeckoSession session = new GeckoSession();
//        session.setContentDelegate(new GeckoSession.ContentDelegate() {});
//        if (sRuntime == null) {
//            sRuntime = GeckoRuntime.create(this);
//        }
//        session.open(sRuntime);
//        view.setSession(session);
//        session.loadUri(url);
//        session.loadUri("https://github.com/theAmit10");







        WebViewClient webViewClient  = new WebViewClient();
        webView.setWebViewClient(webViewClient);

        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);

        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(url);

    }

}