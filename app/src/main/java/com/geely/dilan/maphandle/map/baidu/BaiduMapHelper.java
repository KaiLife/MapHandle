package com.geely.dilan.maphandle.map.baidu;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.geely.dilan.maphandle.R;
import com.geely.dilan.maphandle.map.common.IMapService;
import com.geely.dilan.maphandle.map.common.MapLocationListener;
import com.geely.dilan.maphandle.map.common.MyOrientationListener;
import com.geely.dilan.maphandle.map.utils.SensorEventHelper;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */
//#if MAP_TYPE == 1
public class BaiduMapHelper implements IMapService<BaiduMap, LatLng, BDLocation> {

    // 定位相关
    private LocationClient mLocClient = null;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap = null;
    private boolean isFirstLoc; // 是否首次定位
    private Context context;
    private SensorEventHelper mSensorHelper;

    /**
     * 建议该方法放在Application的初始化方法中
     *
     * @param context
     */
    public static void init(Context context) {
        SDKInitializer.initialize(context);
    }

    public BaiduMapHelper(@NonNull Context context, @NonNull MapView mapView) {
        this.context = context;
        mMapView = mapView;
        mBaiduMap = mMapView.getMap();
        initMapSet();
    }

    private void initMapSet() {
        UiSettings uiSettings = mBaiduMap.getUiSettings();
        uiSettings.setOverlookingGesturesEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);

//        mBaiduMap.setMaxAndMinZoomLevel(21, 3);
    }

    @Override
    public BaiduMap getMap() {
        return mBaiduMap;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, final MapLocationListener<BDLocation> mapLocationListener) {
        if (mapLocationListener != null) {
            isFirstLoc = true;
            // 定位初始化
            mLocClient = new LocationClient(context);
            mLocClient.registerLocationListener(new BDLocationListener() {
                @Override
                public void onReceiveLocation(BDLocation bdLocation) {
                    // mMapView 销毁后不在处理新接收的位置
                    if (bdLocation == null || mMapView == null) {
                        return;
                    }
                    mBaiduMap.setMyLocationData(new MyLocationData.Builder()
                            .latitude(bdLocation.getLatitude())
                            .longitude(bdLocation.getLongitude()).build());
                    mapLocationListener.onLocationSuccess(bdLocation);
                    if (isFirstLoc) {
                        isFirstLoc = false;
                        mapLocationListener.onFirstSuccess(bdLocation);
                    }
                }

                @Override
                public void onConnectHotSpotMessage(String s, int i) {

                }
            });

            // 开启定位图层
            mBaiduMap.setMyLocationEnabled(true);

            mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.NORMAL, true,
                    BitmapDescriptorFactory.fromResource(R.mipmap.navi_map_gps_locked)));

            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            mLocClient.setLocOption(option);

            mSensorHelper = new SensorEventHelper(context, new MyOrientationListener() {
                @Override
                public void onOrientationChanged(float x) {
                    MyLocationData locData = mBaiduMap.getLocationData();
                    if (locData != null) {
                        mBaiduMap.setMyLocationData(new MyLocationData.Builder()
                                .direction(x)
                                .latitude(locData.latitude)
                                .longitude(locData.longitude).build());
                    }
                }
            });
        }
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        if (mLocClient != null) {
            mLocClient.start();
        }

        if (mSensorHelper != null) {
            mSensorHelper.registerSensorListener();
        }
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        if (mLocClient != null) {
            mLocClient.stop();
        }

        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onDestroy() {
        if (mLocClient != null) {
            // 退出时销毁定位
            mLocClient.stop();
        }
        mLocClient = null;

        if (mSensorHelper != null) {
            mSensorHelper.unRegisterSensorListener();
        }
        mSensorHelper = null;

        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

    @Override
    public void animateMap(LatLng target, float zoom) {
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(
                new MapStatus.Builder().target(target).zoom(zoom).build()));
    }

    @Override
    public void zoomIn() {
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.zoomIn());
    }

    @Override
    public void zoomOut() {
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.zoomOut());
    }

}
//#endif
