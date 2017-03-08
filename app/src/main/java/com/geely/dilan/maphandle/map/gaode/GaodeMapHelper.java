package com.geely.dilan.maphandle.map.gaode;



/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class GaodeMapHelper {

    //#if MAP_TYPE == 0
//@    private MyLocationSource myLocationSource = null;
//@    private MyAMapLocationListener myAMapLocationListener = null;
//@    private com.amap.api.location.AMapLocationClient mlocationClient;
//@    private com.amap.api.maps.LocationSource.OnLocationChangedListener mListener;
//@    private com.geely.dilan.maphandle.map.common.MapHelper.MapLocationListenner mapLocationListenner;
//@    private com.amap.api.maps.MapView mMapView = null;
//@    private com.amap.api.maps.AMap aMap = null;
//@    private boolean locOpened;
//@    private boolean isFirstLoc; // 是否首次定位
//@
//@    public GaodeMapHelper(@android.support.annotation.NonNull com.amap.api.maps.MapView mapView, boolean locOpened) {
//@        this.locOpened = locOpened;
//@        mMapView = mapView;
//@        aMap = mMapView.getMap();
//@
//@        initMapSet();
//@    }
//@
//@    private void initMapSet() {
//@        com.amap.api.maps.UiSettings uiSettings = aMap.getUiSettings();
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
//@    public com.amap.api.maps.AMap getAMap() {
//@        return aMap;
//@    }
//@
//@    public void onCreate(android.os.Bundle savedInstanceState, final com.geely.dilan.maphandle.map.common.MapHelper.MapLocationListenner listenner) {
//@        mMapView.onCreate(savedInstanceState);
//@        if (locOpened) {
//@            isFirstLoc = true;
//@            mapLocationListenner = listenner;
//@            myLocationSource = new MyLocationSource();
//@            myAMapLocationListener = new MyAMapLocationListener();
//@            aMap.setLocationSource(myLocationSource);// 设置定位监听
//@            initMapLoc();
//@        }
//@    }
//@
//@    private void initMapLoc() {
//@        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
//@        aMap.setMyLocationEnabled(true);
//@        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
//@        aMap.setMyLocationType(com.amap.api.maps.AMap.LOCATION_TYPE_LOCATE);
//@        com.amap.api.maps.model.MyLocationStyle myLocationStyle = new com.amap.api.maps.model.MyLocationStyle();
//@        // 自定义定位蓝点图标
//@        myLocationStyle.myLocationIcon(com.amap.api.maps.model.BitmapDescriptorFactory.
//@                fromResource(com.geely.dilan.maphandle.R.mipmap.dibiaowo));
//@        // 自定义精度范围的圆形边框颜色
//@        myLocationStyle.strokeColor(0x00000000);
//@        //自定义精度范围的圆形边框宽度
//@        myLocationStyle.strokeWidth(0);
//@        // 设置圆形的填充颜色
//@        myLocationStyle.radiusFillColor(0x00000000);
//@        // 将自定义的 myLocationStyle 对象添加到地图上
//@        aMap.setMyLocationStyle(myLocationStyle);
//@    }
//@
//@    public void onResume() {
//@        mMapView.onResume();
//@    }
//@
//@    public void onPause() {
//@        mMapView.onPause();
//@        stopLocation();
//@    }
//@
//@    public void onSaveInstanceState(android.os.Bundle outState) {
//@        mMapView.onSaveInstanceState(outState);
//@    }
//@
//@    public void onDestroy() {
//@        mMapView.onDestroy();
//@        stopLocation();
//@    }
//@
//@    public void animateMap(com.amap.api.maps.model.LatLng target, float zoom) {
//@        aMap.animateCamera(com.amap.api.maps.CameraUpdateFactory.newCameraPosition(
//@                new com.amap.api.maps.model.CameraPosition(target, zoom, 0, 0)));
//@    }
//@
//@    public void zoomIn() {
//@        aMap.animateCamera(com.amap.api.maps.CameraUpdateFactory.zoomIn());
//@    }
//@
//@    public void zoomOut() {
//@        aMap.animateCamera(com.amap.api.maps.CameraUpdateFactory.zoomOut());
//@    }
//@
//@    public void setMapLoadedListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapLoadedListener listener) {
//@        aMap.setOnMapLoadedListener(new com.amap.api.maps.AMap.OnMapLoadedListener() {
//@            @Override
//@            public void onMapLoaded() {
//@                listener.onMapLoaded();
//@            }
//@        });
//@    }
//@
//@    public void setMapClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapClickListener listener) {
//@        aMap.setOnMapClickListener(new com.amap.api.maps.AMap.OnMapClickListener() {
//@            @Override
//@            public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
//@                if (latLng != null) {
//@                    listener.onMapClick(new com.geely.dilan.maphandle.map.bean.LatLng(latLng.latitude, latLng.longitude));
//@                }
//@            }
//@        });
//@    }
//@
//@    public void setMarkerClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MarkerClickListener listener) {
//@        aMap.setOnMarkerClickListener(new com.amap.api.maps.AMap.OnMarkerClickListener() {
//@            @Override
//@            public boolean onMarkerClick(com.amap.api.maps.model.Marker marker) {
//@                if (marker != null) {
//@                    listener.onMarkerClick(marker);
//@                }
//@                return false;
//@            }
//@        });
//@    }
//@
//@    private class MyLocationSource implements com.amap.api.maps.LocationSource {
//@
//@        /**
//@         * 激活定位
//@         */
//@        @Override
//@        public void activate(com.amap.api.maps.LocationSource.OnLocationChangedListener listener) {
//@            mListener = listener;
//@            if (mlocationClient == null) {
//@                mlocationClient = new com.amap.api.location.AMapLocationClient(mMapView.getContext());
//@                com.amap.api.location.AMapLocationClientOption mLocationOption = new com.amap.api.location.AMapLocationClientOption();
//@                //设置定位监听
//@                mlocationClient.setLocationListener(myAMapLocationListener);
//@                //设置为高精度定位模式
//@                mLocationOption.setLocationMode(com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
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
//@        mListener = null;
//@        if (mlocationClient != null) {
//@            mlocationClient.stopLocation();
//@            mlocationClient.onDestroy();
//@        }
//@        mlocationClient = null;
//@    }
//@
//@    private class MyAMapLocationListener implements com.amap.api.location.AMapLocationListener {
//@
//@        /**
//@         * 定位成功后回调函数
//@         */
//@        @Override
//@        public void onLocationChanged(com.amap.api.location.AMapLocation amapLocation) {
//@            if (mListener != null && amapLocation != null) {
//@                if (amapLocation.getErrorCode() == 0) {
//@                    mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
//@                    if (mapLocationListenner != null) {
//@                        mapLocationListenner.onLocationSuccess(amapLocation);
//@                        if (isFirstLoc) {
//@                            isFirstLoc = false;
//@                            mapLocationListenner.onFirstSuccess(amapLocation);
//@                        }
//@                    }
//@                }
//@            }
//@        }
//@    }
//@
    //#endif
}
