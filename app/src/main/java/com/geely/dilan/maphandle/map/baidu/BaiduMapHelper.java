package com.geely.dilan.maphandle.map.baidu;

import android.support.annotation.NonNull;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class BaiduMapHelper {

    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;

    public BaiduMapHelper(@NonNull MapView mapView){
        mMapView = mapView;
        mBaiduMap = mMapView.getMap();
    }

    public BaiduMap getBaiduMap(){
        return mBaiduMap;
    }

    public void onResume(){
        mMapView.onResume();
    }

    public void onPause(){
        mMapView.onPause();
    }

    public void onDestroy(){
        mMapView.onDestroy();
    }
}
