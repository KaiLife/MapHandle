package com.geely.dilan.maphandle.map.baidu;



/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class BaiduMapHelper {

    //#if MAP_TYPE == 1
    // 定位相关
    private com.baidu.location.LocationClient mLocClient = null;
    private com.baidu.mapapi.map.MapView mMapView = null;
    private com.baidu.mapapi.map.BaiduMap mBaiduMap = null;
    private boolean locOpened;
    private boolean isFirstLoc; // 是否首次定位

    public BaiduMapHelper(@android.support.annotation.NonNull com.baidu.mapapi.map.MapView mapView, boolean locOpened) {
        this.locOpened = locOpened;
        mMapView = mapView;
        mBaiduMap = mMapView.getMap();

        initMapSet();
    }

    private void initMapSet() {
        com.baidu.mapapi.map.UiSettings uiSettings = mBaiduMap.getUiSettings();
        uiSettings.setOverlookingGesturesEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);

//        mBaiduMap.setMaxAndMinZoomLevel(21, 3);
    }

    public com.baidu.mapapi.map.BaiduMap getBaiduMap() {
        return mBaiduMap;
    }

    public void onCreate(final com.geely.dilan.maphandle.map.common.MapHelper.MapLocationListenner listenner) {
        if (locOpened) {
            isFirstLoc = true;
            // 定位初始化
            mLocClient = new com.baidu.location.LocationClient(mMapView.getContext());
            mLocClient.registerLocationListener(new com.baidu.location.BDLocationListener() {
                @Override
                public void onReceiveLocation(com.baidu.location.BDLocation bdLocation) {
                    // mMapView 销毁后不在处理新接收的位置
                    if (bdLocation == null || mMapView == null) {
                        return;
                    }
                    mBaiduMap.setMyLocationData(new com.baidu.mapapi.map.MyLocationData.Builder()
                            .latitude(bdLocation.getLatitude())
                            .longitude(bdLocation.getLongitude()).build());
                    listenner.onLocationSuccess(bdLocation);
                    if (isFirstLoc) {
                        isFirstLoc = false;
                        listenner.onFirstSuccess(bdLocation);
                    }
                }

                @Override
                public void onConnectHotSpotMessage(String s, int i) {

                }
            });

            // 开启定位图层
            mBaiduMap.setMyLocationEnabled(true);

            mBaiduMap.setMyLocationConfigeration(new com.baidu.mapapi.map.MyLocationConfiguration(
                    com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL, true,
                    com.baidu.mapapi.map.BitmapDescriptorFactory.fromResource(com.geely.dilan.maphandle.R.mipmap.dibiaowo)));

            com.baidu.location.LocationClientOption option = new com.baidu.location.LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            mLocClient.setLocOption(option);
        }
    }

    public void onResume() {
        mMapView.onResume();
        if (mLocClient != null) {
            mLocClient.start();
        }
    }

    public void onPause() {
        mMapView.onPause();
        if (mLocClient != null) {
            mLocClient.stop();
        }
    }

    public void onDestroy() {
        if (mLocClient != null) {
            // 退出时销毁定位
            mLocClient.stop();
            mLocClient = null;
        }
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
    }

    public void animateMap(com.baidu.mapapi.model.LatLng target, float zoom) {
        mBaiduMap.animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.newMapStatus(
                new com.baidu.mapapi.map.MapStatus.Builder().target(target).zoom(zoom).build()));
    }

    public void zoomIn() {
        mBaiduMap.animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.zoomIn());
    }

    public void zoomOut() {
        mBaiduMap.animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.zoomOut());
    }

    public void setMapLoadedListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapLoadedListener listener) {
        mBaiduMap.setOnMapLoadedCallback(new com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                listener.onMapLoaded();
            }
        });
    }

    public void setMapClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapClickListener listener) {
        mBaiduMap.setOnMapClickListener(new com.baidu.mapapi.map.BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(com.baidu.mapapi.model.LatLng latLng) {
                if (latLng != null) {
                    listener.onMapClick(new com.geely.dilan.maphandle.map.bean.LatLng(latLng.latitude, latLng.longitude));
                }
            }

            @Override
            public boolean onMapPoiClick(com.baidu.mapapi.map.MapPoi mapPoi) {
                return false;
            }
        });
    }

    public void setMarkerClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MarkerClickListener listener) {
        mBaiduMap.setOnMarkerClickListener(new com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
                if (marker != null) {
                    listener.onMarkerClick(marker);
                }
                return false;
            }
        });
    }

    //#endif
}
