package com.sniper.viptv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnClickListener{

    protected Button youku;
    protected Button tencent;
    protected Button iqiyi;
    protected Button mango;
    protected Button souhu;
    protected RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        youku = (Button) findViewById(R.id.youku);
        tencent = (Button) findViewById(R.id.tencent);
        iqiyi = (Button) findViewById(R.id.iqiyi);
        mango = (Button) findViewById(R.id.mango);
        souhu = (Button) findViewById(R.id.souhu);
        activityMain = (RelativeLayout) findViewById(R.id.activity_main);
        youku.setOnClickListener(this);
        tencent.setOnClickListener(this);
        iqiyi.setOnClickListener(this);
        mango.setOnClickListener(this);
        souhu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String url = "";
        switch (v.getId()){
            case R.id.youku:
                url = "http://www.youku.com";
                break;
            case R.id.tencent:
                url="http://v.qq.com";
                break;
            case R.id.iqiyi:
                url="http://www.iqiyi.com";
                break;
            case R.id.mango:
                url="www.mgtv.com";
                break;
            case R.id.souhu:
                url="http://tv.sohu.com/";
                break;
        }
        Intent intent = new Intent(this,VipVoidActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);

    }
}
