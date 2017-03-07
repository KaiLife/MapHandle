package com.geely.dilan.maphandle.map.baidu;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class BaiduMapHelper {

    //#if MAP_TYPE == 1

    private com.baidu.mapapi.map.MapView mMapView = null;
    private com.baidu.mapapi.map.BaiduMap mBaiduMap = null;

    public BaiduMapHelper(@android.support.annotation.NonNull com.baidu.mapapi.map.MapView mapView){
        mMapView = mapView;
        mBaiduMap = mMapView.getMap();
    }

    public com.baidu.mapapi.map.BaiduMap getBaiduMap(){
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

    //#endif
}
