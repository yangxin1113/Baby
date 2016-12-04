package com.zyx.baby.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/12/4 0004.
 */

public class WebActivity extends BaseActivity {
    public final static String URL = "url";
    public final static String TITLE = "title";
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.webView)
    WebView webView;

    public static void runActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_web);
    }

    @Override
    protected void setInitData() {

        String url = getIntent().getStringExtra(URL);
        String title = getIntent().getStringExtra(TITLE);

        pb.setMax(100);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb.setProgress(newProgress);
                if (newProgress >= 100) {
                    pb.setVisibility(View.GONE);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);

    }

    @Override
    protected void initEvent() {

    }

}
