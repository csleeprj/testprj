package com.appinsightor.android.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //웹뷰 인스턴스 생성
        //setLayout();
        mWebView = (WebView)findViewById(R.id.webivew);

        //스크롤 없애기 추가
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);

        //웹뷰에서 자바스크립트 실행 가능
        mWebView.getSettings().setJavaScriptEnabled(true);


        final Context myApp = this;
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)
            {
                new AlertDialog.Builder(myApp)
                        .setTitle("AlertDialog")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new AlertDialog.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        result.confirm();
                                    }
                                })
                        .setCancelable(false)
                        .create()
                        .show();
                return true;
            }
        });



        //플러그인 가능하게(플래시 동영상 등) - 자동처리됨
        //mWebView.getSettings().setPluginsEnabled(true);

        //구글 홈페이지 지정
        mWebView.loadUrl("http://192.168.108.131:8080");
        //WebViewClient 지정
        mWebView.setWebViewClient(new WebViewClient());














    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == event.KEYCODE_BACK) && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 종료처리시 종료 할지 물어보기 추가
     */
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("종료")
                .setMessage("종료하시겠습니가?")
                .setNegativeButton("아니오",null)
                .setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }

    private void setLayout() {
        mWebView = (WebView)findViewById(R.id.webivew);
    }

}
