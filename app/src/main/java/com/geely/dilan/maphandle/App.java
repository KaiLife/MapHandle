package com.geely.dilan.maphandle;

import android.app.Application;

import com.geely.dilan.maphandle.map.baidu.BaiduMapHelper;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //#if MAP_TYPE == 1
        BaiduMapHelper.init(this);
        //#endif
    }
}
