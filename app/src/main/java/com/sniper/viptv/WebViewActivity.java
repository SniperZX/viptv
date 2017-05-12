package com.sniper.viptv;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;

import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebViewActivity extends Activity {

    String urltxt;
    private WebView webView;
    boolean hasSet = false;
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.webView);
        initWebView(webView);
        urltxt = getIntent().getStringExtra("url");
        webView.loadUrl("http://www.zhaohongru.top:8080/vip/index.html");
     //   webView.loadUrl("javascript:setUrl('" + urltxt + "')");
     //   webView.addJavascriptInterface(new Contact(), "contact");
    }

    public void initWebView( WebView webView) {
        //设置支持JavaScript脚本
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
                //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //去掉放缩的图标
        webSettings.setDisplayZoomControls(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheMaxSize(1024*1024*8);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);


        //设置WebViewClient
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
               // view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(!hasSet) {
                    view.loadUrl("javascript:setUrl('" + urltxt + "')");
                    hasSet = true;
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {

                }
//                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                // TODO Auto-generated method stub
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                openFileChooserImplForAndroid5(filePathCallback);
                return true;
            }
        });


    }


    private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg) {
        mUploadMessageForAndroid5 = uploadMsg;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("file/*");

        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "文件管理器");

        startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
    }
    public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;


    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5){
            if (null == mUploadMessageForAndroid5)
                return;
            Uri result = (intent == null || resultCode != RESULT_OK) ? null: intent.getData();
            if (result != null) {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
            } else {
                mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
            }
            mUploadMessageForAndroid5 = null;
        }
    }



}
