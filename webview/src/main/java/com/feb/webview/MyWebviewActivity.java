package com.feb.webview;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyWebviewActivity extends AppCompatActivity {

    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_webview);
        ButterKnife.bind(this);
        initParams();
        initView();
        //initListener();
    }

    private void initParams() {
    }

    private void initView() {
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webView.loadUrl("http://fanyi.baidu.com/?aldtype=16047#en/zh/NARROW");
        //webView.loadUrl("http://m.hexun.com/insurance/2017-12-11/191937627.html?from=timeline&isappinstalled=0%22");
        //webView.loadUrl("http://www.mobius-china.com/taibaovideo//");
        webView.loadUrl("http://wxtest.cpic.com.cn/mobwebchatsy/?ht=95280000&openid=");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(url);

                } else {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Log.e("ll", e.toString());
                        }
                    }
                }
                return true;
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setTextZoom(100);  //设置字体不随系统设置变化
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setDatabaseEnabled(true);
        //设置定位的数据库路径
        String dir = this.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setGeolocationDatabasePath(dir);
        //启用地理定位
        webSettings.setGeolocationEnabled(true);
        //开启DomStorage缓存
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setUseWideViewPort(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        //wv_webView.addJavascriptInterface(new JsFun(SimpleWebActivity.this), "android");

        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }else{
            cookieManager.setAcceptCookie(true);
        }

    }

    private void initListener() {
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.ACTION_DOWN) {
        }
        return super.onKeyDown(keyCode, event);
    }
}
