package com.geely.dilan.maphandle.map.gaode;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class GaodeMapHelper {

    //#if MAP_TYPE == 0

    private com.amap.api.maps.MapView mMapView = null;
    private com.amap.api.maps.AMap aMap = null;

    public GaodeMapHelper(@android.support.annotation.NonNull com.amap.api.maps.MapView mapView) {
        mMapView = mapView;
        aMap = mMapView.getMap();

        initMapSet();
    }

    private void initMapSet() {
        com.amap.api.maps.UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setScaleControlsEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setTiltGesturesEnabled(false);

//        aMap.setMaxZoomLevel(19);
//        aMap.setMinZoomLevel(3);
    }

    public com.amap.api.maps.AMap getAMap() {
        return aMap;
    }

    public void onCreate(android.os.Bundle savedInstanceState) {
        mMapView.onCreate(savedInstanceState);
    }

    public void onResume() {
        mMapView.onResume();
    }

    public void onPause() {
        mMapView.onPause();
    }

    public void onSaveInstanceState(android.os.Bundle outState) {
        mMapView.onSaveInstanceState(outState);
    }

    public void onDestroy() {
        mMapView.onDestroy();
    }

    public void animateMap(com.amap.api.maps.model.LatLng target, float zoom) {
        aMap.animateCamera(com.amap.api.maps.CameraUpdateFactory.newCameraPosition(
                new com.amap.api.maps.model.CameraPosition(target, zoom, 0, 0)));
    }

    public void zoomIn() {
        aMap.animateCamera(com.amap.api.maps.CameraUpdateFactory.zoomIn());
    }

    public void zoomOut() {
        aMap.animateCamera(com.amap.api.maps.CameraUpdateFactory.zoomOut());
    }

    public void setMapLoadedListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapLoadedListener listener) {
        aMap.setOnMapLoadedListener(new com.amap.api.maps.AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                listener.onMapLoaded();
            }
        });
    }

    public void setMapClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MapClickListener listener) {
        aMap.setOnMapClickListener(new com.amap.api.maps.AMap.OnMapClickListener() {
            @Override
            public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
                if (latLng != null) {
                    listener.onMapClick(new com.geely.dilan.maphandle.map.bean.LatLng(latLng.latitude, latLng.longitude));
                }
            }
        });
    }

    public void setMarkerClickListener(final com.geely.dilan.maphandle.map.common.MapHelper.MarkerClickListener listener) {
        aMap.setOnMarkerClickListener(new com.amap.api.maps.AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.amap.api.maps.model.Marker marker) {
                if (marker != null) {
                    listener.onMarkerClick(marker);
                }
                return false;
            }
        });
    }

    //#endif
}
