package com.geely.dilan.maphandle;

import android.app.Application;

import com.geely.dilan.maphandle.map.common.MapHelper;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MapHelper.init(this);
    }
}
