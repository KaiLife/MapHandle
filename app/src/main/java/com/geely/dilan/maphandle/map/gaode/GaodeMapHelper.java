package com.geely.dilan.maphandle.map.gaode;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.geely.dilan.maphandle.R;
import com.geely.dilan.maphandle.map.common.IMapService;
import com.geely.dilan.maphandle.map.common.MapLocationListener;
import com.geely.dilan.maphandle.map.common.MyOrientationListener;
import com.geely.dilan.maphandle.map.utils.SensorEventHelper;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */
//#if MAP_TYPE == 0
//@public class GaodeMapHelper implements IMapService<AMap, LatLng, AMapLocation> {
//@
//@    private MyLocationSource myLocationSource = null;
//@    private MyAMapLocationListener myAMapLocationListener = null;
//@    private AMapLocationClient mlocationClient;
//@    private MapLocationListener<AMapLocation> mapLocationListenner;
//@    private MapView mMapView = null;
//@    private AMap aMap = null;
//@    private boolean isFirstLoc; // 是否首次定位
//@    private Context context;
//@    private Marker mLocMarker;
//@    private SensorEventHelper mSensorHelper;
//@
//@    public GaodeMapHelper(@NonNull Context context, @NonNull MapView mapView) {
//@        this.context = context;
//@        mMapView = mapView;
//@        aMap = mMapView.getMap();
//@
//@        initMapSet();
//@    }
//@
//@    private void initMapSet() {
//@        UiSettings uiSettings = aMap.getUiSettings();
//@        uiSettings.setMyLocationButtonEnabled(false);
//@        uiSettings.setRotateGesturesEnabled(false);
//@        uiSettings.setScrollGesturesEnabled(true);
//@        uiSettings.setScaleControlsEnabled(true);
//@        uiSettings.setZoomGesturesEnabled(true);
//@        uiSettings.setZoomControlsEnabled(false);
//@        uiSettings.setTiltGesturesEnabled(false);
//@
//@//        aMap.setMaxZoomLevel(19);
//@//        aMap.setMinZoomLevel(3);
//@    }
//@
//@    @Override
//@    public AMap getMap() {
//@        return aMap;
//@    }
//@
//@    @Override
//@    public void onCreate(Bundle savedInstanceState, MapLocationListener<AMapLocation> mapLocationListener) {
//@        mMapView.onCreate(savedInstanceState);
//@        if (mapLocationListener != null) {
//@            isFirstLoc = true;
//@            mapLocationListenner = mapLocationListener;
//@            myLocationSource = new MyLocationSource();
//@            myAMapLocationListener = new MyAMapLocationListener();
//@            aMap.setLocationSource(myLocationSource);// 设置定位监听
//@            initMapLoc();
//@            mSensorHelper = new SensorEventHelper(context, new MyOrientationListener() {
//@                @Override
//@                public void onOrientationChanged(float x) {
//@                    if (mLocMarker != null) {
//@                        mLocMarker.setRotateAngle(x);
//@                    }
//@                }
//@            });
//@        }
//@    }
//@
//@    private void initMapLoc() {
//@        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//@        aMap.setMyLocationEnabled(true);
//@        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
//@        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
//@    }
//@
//@    @Override
//@    public void onResume() {
//@        mMapView.onResume();
//@        if (mSensorHelper != null) {
//@            mSensorHelper.registerSensorListener();
//@        }
//@    }
//@
//@    @Override
//@    public void onPause() {
//@        mMapView.onPause();
//@        stopLocation();
//@        if (mSensorHelper != null) {
//@            mSensorHelper.unRegisterSensorListener();
//@        }
//@    }
//@
//@    @Override
//@    public void onSaveInstanceState(Bundle outState) {
//@        mMapView.onSaveInstanceState(outState);
//@    }
//@
//@    @Override
//@    public void onDestroy() {
//@        mMapView.onDestroy();
//@        stopLocation();
//@        if (mSensorHelper != null) {
//@            mSensorHelper.unRegisterSensorListener();
//@        }
//@        mSensorHelper = null;
//@    }
//@
//@    @Override
//@    public void animateMap(LatLng target, float zoom) {
//@        aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(target, zoom, 0, 0)));
//@    }
//@
//@    @Override
//@    public void zoomIn() {
//@        aMap.animateCamera(CameraUpdateFactory.zoomIn());
//@    }
//@
//@    @Override
//@    public void zoomOut() {
//@        aMap.animateCamera(CameraUpdateFactory.zoomOut());
//@    }
//@
//@    private class MyLocationSource implements LocationSource {
//@
//@        /**
//@         * 激活定位
//@         */
//@        @Override
//@        public void activate(LocationSource.OnLocationChangedListener listener) {
//@            if (mlocationClient == null) {
//@                mlocationClient = new AMapLocationClient(context);
//@                AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
//@                //设置定位监听
//@                mlocationClient.setLocationListener(myAMapLocationListener);
//@                //设置为高精度定位模式
//@                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//@                //设置定位参数
//@                mlocationClient.setLocationOption(mLocationOption);
//@                // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//@                // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
//@                // 在定位结束后，在合适的生命周期调用onDestroy()方法
//@                // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//@                mlocationClient.startLocation();
//@            }
//@        }
//@
//@        /**
//@         * 停止定位
//@         */
//@        @Override
//@        public void deactivate() {
//@            stopLocation();
//@        }
//@    }
//@
//@    private void stopLocation() {
//@        if (mlocationClient != null) {
//@            mlocationClient.stopLocation();
//@            mlocationClient.onDestroy();
//@        }
//@        mlocationClient = null;
//@    }
//@
//@    private class MyAMapLocationListener implements AMapLocationListener {
//@
//@        /**
//@         * 定位成功后回调函数
//@         */
//@        @Override
//@        public void onLocationChanged(AMapLocation amapLocation) {
//@            if (amapLocation != null) {
//@                if (amapLocation.getErrorCode() == 0) {
//@                    if (mapLocationListenner != null) {
//@                        mapLocationListenner.onLocationSuccess(amapLocation);
//@                        if (isFirstLoc) {
//@                            isFirstLoc = false;
//@                            addLocMarker(new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude()));
//@                            mapLocationListenner.onFirstSuccess(amapLocation);
//@                        }
//@                    }
//@                }
//@            }
//@        }
//@    }
//@
//@    /**
//@     * 添加定位Marker
//@     */
//@    private void addLocMarker(LatLng latlng) {
//@        if (mLocMarker != null) {
//@            return;
//@        }
//@        BitmapDescriptor des = BitmapDescriptorFactory.fromResource(R.mipmap.navi_map_gps_locked);
//@        MarkerOptions options = new MarkerOptions();
//@        options.icon(des);
//@        options.anchor(0.5f, 0.5f);
//@        options.position(latlng);
//@        mLocMarker = aMap.addMarker(options);
//@    }
//@}
//#endif
