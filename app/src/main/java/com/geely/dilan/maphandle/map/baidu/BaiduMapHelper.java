package com.geely.dilan.maphandle.map.baidu;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class BaiduMapHelper {

    //#if MAP_TYPE == 1
//@
//@    private com.baidu.mapapi.map.MapView mMapView = null;
//@    private com.baidu.mapapi.map.BaiduMap mBaiduMap = null;
//@
//@    public BaiduMapHelper(@android.support.annotation.NonNull com.baidu.mapapi.map.MapView mapView) {
//@        mMapView = mapView;
//@        mBaiduMap = mMapView.getMap();
//@
//@        initMapSet();
//@    }
//@
//@    private void initMapSet() {
//@        com.baidu.mapapi.map.UiSettings uiSettings = mBaiduMap.getUiSettings();
//@        uiSettings.setOverlookingGesturesEnabled(false);
//@        uiSettings.setRotateGesturesEnabled(false);
//@        uiSettings.setScrollGesturesEnabled(true);
//@        uiSettings.setZoomGesturesEnabled(true);
//@
//@//        mBaiduMap.setMaxAndMinZoomLevel(21, 3);
//@    }
//@
//@    public com.baidu.mapapi.map.BaiduMap getBaiduMap() {
//@        return mBaiduMap;
//@    }
//@
//@    public void onResume() {
//@        mMapView.onResume();
//@    }
//@
//@    public void onPause() {
//@        mMapView.onPause();
//@    }
//@
//@    public void onDestroy() {
//@        mMapView.onDestroy();
//@    }
//@
//@    public void animateMap(com.baidu.mapapi.model.LatLng target, float zoom) {
//@        mBaiduMap.animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.newMapStatus(
//@                new com.baidu.mapapi.map.MapStatus.Builder().target(target).zoom(zoom).build()));
//@    }
//@
//@    public void zoomIn() {
//@        mBaiduMap.animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.zoomIn());
//@    }
//@
//@    public void zoomOut() {
//@        mBaiduMap.animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.zoomOut());
//@    }
//@
//@    public void setMapLoadedListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapLoadedListener listener) {
//@        mBaiduMap.setOnMapLoadedCallback(new com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback() {
//@            @Override
//@            public void onMapLoaded() {
//@                listener.onMapLoaded();
//@            }
//@        });
//@    }
//@
//@    public void setMapClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapClickListener listener) {
//@        mBaiduMap.setOnMapClickListener(new com.baidu.mapapi.map.BaiduMap.OnMapClickListener() {
//@            @Override
//@            public void onMapClick(com.baidu.mapapi.model.LatLng latLng) {
//@                if (latLng != null) {
//@                    listener.onMapClick(new com.geely.dilan.maphandle.map.bean.LatLng(latLng.latitude, latLng.longitude));
//@                }
//@            }
//@
//@            @Override
//@            public boolean onMapPoiClick(com.baidu.mapapi.map.MapPoi mapPoi) {
//@                return false;
//@            }
//@        });
//@    }
//@
//@    public void setMarkerClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MarkerClickListener listener) {
//@        mBaiduMap.setOnMarkerClickListener(new com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener() {
//@            @Override
//@            public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
//@                if (marker != null) {
//@                    listener.onMarkerClick(marker);
//@                }
//@                return false;
//@            }
//@        });
//@    }
//@
    //#endif
}
