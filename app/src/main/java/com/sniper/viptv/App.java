package com.sniper.viptv;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * Created by 76411 on 2016-09-30.
 */

public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {

            }
        };
        QbSdk.initX5Environment(this,cb );
        QbSdk.setDownloadWithoutWifi(false);
    }



    public static synchronized App getInstance() {
        return app;
    }

}
