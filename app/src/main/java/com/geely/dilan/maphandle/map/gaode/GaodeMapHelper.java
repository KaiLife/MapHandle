package com.geely.dilan.maphandle.map.gaode;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class GaodeMapHelper {

    //#if MAP_TYPE == 0

    private MapView mMapView = null;
    private AMap aMap = null;

    public GaodeMapHelper(@NonNull MapView mapView) {
        mMapView = mapView;
        aMap = mMapView.getMap();
    }

    public AMap getAMap() {
        return aMap;
    }

    public void onCreate(Bundle savedInstanceState){
        mMapView.onCreate(savedInstanceState);
    }

    public void onResume() {
        mMapView.onResume();
    }

    public void onPause() {
        mMapView.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        mMapView.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        mMapView.onDestroy();
    }

    //#endif
}
