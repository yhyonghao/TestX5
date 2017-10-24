package com.example.pc.testx5;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.InputStream;

public class WebActivity extends BaseActivity{

    private EditText etWebsite;
    private TextView tvEnter, tvStatus;
    private ProgressBar progressBar;
    private com.tencent.smtt.sdk.WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        LogUtil.i("WebActivity.onCreate()");
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        loadUrl(url);

    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        url = bundle.getString("url");
    }

    public static void gotoActivity(String url) {
        if (TextUtils.isEmpty(url) || !url.startsWith("http")) {
            ToastUtil.show("网址错误!");
            return;
        }
        Intent intent = new Intent(BaseApplication.getInstance(), WebActivity.class);
        intent.putExtra("url", url);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getInstance().startActivity(intent);
    }



    private void initView() {

        webView = (com.tencent.smtt.sdk.WebView) findViewById(R.id.webView);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//允许js弹出窗口



    }

    private void loadUrl(String url) {

//        String murl="http://192.168.1.130:8009/res/plat/html5/Template/hr/build/pre-entry/index.html?page=yuruzhiyuangong&data={\"baseUrl\":\"http://192.168.1.10:8080\",\"tokenValue\":\"8u8scG8IOQZk_rybbn5rmNpRGXL6LmVO2hzmTLi5Z2w\"}";
//        String murl=" http://192.168.1.130:8009/res/plat/html5/Template/hr/build/pre-entry/index.html?page=yuruzhiyuangong&data={\"baseUrl\":\"http://192.168.1.10:8080\",\"tokenValue\":\"x8gAsm539iHfkpyAQoy_z-OAF7pSqgT7OWqS8INdcF4\"}";
//        String murl="http://192.168.1.38/html5/Template/hr/build/pre-entry/index.html";
        String murl="www.baidu.com";
        webView.loadUrl("http://www.android-studio.org/");
        Log.d("yyyyyyyyyyy","走了111111111111111"+murl);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, String s) {
                try {
                    Log.d("yyyyyyyyyyy","走了111111111111111");
                    if (s.contains("niuablity.com")) {
                        Log.d("yyyyyyyyyyy","走了22222222222");
                        InputStream localCopy = getAssets().open("all.js");
//                        WebResourceResponse response = new WebResourceResponse("text/x-javascript", "UTF-8", localCopy);
                        WebResourceResponse response = new WebResourceResponse("text/plain", "UTF-8", localCopy);
                        return response;
//                        return new WebResourceResponse("application/x-javascript", "utf-8", getAssets().open("all.js"));
                    }
                } catch (Exception e) {

                }
                return super.shouldInterceptRequest(webView, s);
            }

            @Override
            public void onReceivedError(WebView var1, int var2, String var3, String var4) {
                progressBar.setVisibility(View.GONE);
                ToastUtil.show("网页加载失败");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) webView.destroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
