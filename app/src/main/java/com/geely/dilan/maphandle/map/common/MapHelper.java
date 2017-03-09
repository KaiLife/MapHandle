package com.geely.dilan.maphandle.map.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.geely.dilan.maphandle.map.baidu.BaiduMapHelper;
import com.geely.dilan.maphandle.map.bean.LatLng;
import com.geely.dilan.maphandle.map.gaode.GaodeMapHelper;
import com.geely.dilan.maphandle.map.utils.SensorEventHelper;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */
public class MapHelper {

    private BaiduMapHelper baiduMapHelper;
    private GaodeMapHelper gaodeMapHelper;
    private Object map;
    private SensorEventHelper mSensorHelper;
    private Context context;

    /**
     * 建议该方法放在Application的初始化方法中
     *
     * @param context
     */
    public static void init(Context context) {
        //#if MAP_TYPE == 1
        com.baidu.mapapi.SDKInitializer.initialize(context);
        //#endif
    }

    public MapHelper(@NonNull Context context, @NonNull Object mapView) {
        this.context = context;

        //#if MAP_TYPE == 1
        if (mapView instanceof com.baidu.mapapi.map.MapView) {
            baiduMapHelper = new BaiduMapHelper(context, (com.baidu.mapapi.map.MapView) mapView);
            map = baiduMapHelper.getBaiduMap();
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (mapView instanceof com.amap.api.maps.MapView) {
//@            gaodeMapHelper = new GaodeMapHelper(context, (com.amap.api.maps.MapView) mapView);
//@            map = gaodeMapHelper.getAMap();
//@        }
        //#endif
    }

    public Object getMap() {
        return map;
    }

    public void onCreate(Bundle savedInstanceState, MapLocationListenner locationListenner) {
        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.onCreate(locationListenner);
            mSensorHelper = new SensorEventHelper(context, baiduMapHelper.getMyOrientationListener());
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.onCreate(savedInstanceState, locationListenner);
//@            mSensorHelper = new SensorEventHelper(context, gaodeMapHelper.getMyOrientationListener());
//@        }
        //#endif
    }

    public void onResume() {
        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.onResume();
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.onResume();
//@        }
        //#endif

        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
    }

    public void onPause() {
        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.onPause();
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.onPause();
//@        }
        //#endif

        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.onSaveInstanceState(outState);
//@        }
        //#endif
    }

    public void onDestroy() {
        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.onDestroy();
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.onDestroy();
//@        }
        //#endif

        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
        }
        mSensorHelper = null;
    }

    /**
     * 移动地图
     *
     * @param target 经纬度
     * @param zoom   缩放级别
     */
    public void animateMap(@NonNull LatLng target, float zoom) {
        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.animateMap(new com.baidu.mapapi.model.LatLng(target.getLatitude(), target.getLongitude()), zoom);
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.animateMap(new com.amap.api.maps.model.LatLng(target.getLatitude(), target.getLongitude()), zoom);
//@        }
        //#endif
    }

    /**
     * 地图放大
     */
    public void zoomIn() {
        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.zoomIn();
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.zoomIn();
//@        }
        //#endif
    }

    /**
     * 地图缩小
     */
    public void zoomOut() {
        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.zoomOut();
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.zoomOut();
//@        }
        //#endif
    }

    public void setMapLoadedListener(MapLoadedListener listener) {
        if (listener == null) {
            return;
        }

        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.setMapLoadedListener(listener);
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.setMapLoadedListener(listener);
//@        }
        //#endif
    }

    public void setMapClickListener(MapClickListener listener) {
        if (listener == null) {
            return;
        }

        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.setMapClickListener(listener);
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.setMapClickListener(listener);
//@        }
        //#endif
    }

    public void setMarkerClickListener(MarkerClickListener listener) {
        if (listener == null) {
            return;
        }

        //#if MAP_TYPE == 1
        if (baiduMapHelper != null) {
            baiduMapHelper.setMarkerClickListener(listener);
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.setMarkerClickListener(listener);
//@        }
        //#endif
    }

    public interface MapLoadedListener {
        void onMapLoaded();
    }

    public interface MapClickListener {
        void onMapClick(LatLng point);
    }

    public interface MarkerClickListener {
        boolean onMarkerClick(Object marker);
    }

    public interface MapLocationListenner {
        /**
         * 第一次定位成功
         *
         * @param location 百度-BDLocation，高德-AMapLocation
         */
        void onFirstSuccess(Object location);

        /**
         * 定位成功
         *
         * @param location 百度-BDLocation，高德-AMapLocation
         */
        void onLocationSuccess(Object location);
    }

    /**
     * 我的位置方向朝向监听
     */
    public interface MyOrientationListener {
        void onOrientationChanged(float x);
    }
}
