package com.geely.dilan.maphandle.map.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.amap.api.maps.AMap;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.geely.dilan.maphandle.BuildConfig;
import com.geely.dilan.maphandle.map.baidu.BaiduMapHelper;
import com.geely.dilan.maphandle.map.bean.LatLng;
import com.geely.dilan.maphandle.map.gaode.GaodeMapHelper;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */
public class MapHelper {

    private MapType mapType = BuildConfig.MAP_TYPE;
    private BaiduMapHelper baiduMapHelper;
    private GaodeMapHelper gaodeMapHelper;
    private Object map;

    private MapLoadedListener mapLoadedListener;
    private MapClickListener mapClickListener;
    private MarkerClickListener markerClickListener;

    /**
     * 建议该方法放在Application的初始化方法中
     *
     * @param context
     */
    public static void init(Context context) {
        switch (BuildConfig.MAP_TYPE) {
            case Baidu:
                SDKInitializer.initialize(context);
                break;

            default:
                break;
        }
    }

    public MapHelper(@NonNull Object mapView) {
        switch (mapType) {
            case Baidu:
                if (mapView instanceof com.baidu.mapapi.map.MapView) {
                    baiduMapHelper = new BaiduMapHelper((com.baidu.mapapi.map.MapView) mapView);
                    map = baiduMapHelper.getBaiduMap();
                }
                break;

            default:
                if (mapView instanceof com.amap.api.maps.MapView) {
                    gaodeMapHelper = new GaodeMapHelper((com.amap.api.maps.MapView) mapView);
                    map = gaodeMapHelper.getAMap();
                }
                break;
        }
    }

    public Object getMap() {
        return map;
    }

    public void onCreate(Bundle savedInstanceState) {
        switch (mapType) {
            case Baidu:
                break;

            default:
                if (gaodeMapHelper != null) {
                    gaodeMapHelper.onCreate(savedInstanceState);
                }
                break;
        }
    }

    public void onResume() {
        switch (mapType) {
            case Baidu:
                if (baiduMapHelper != null) {
                    baiduMapHelper.onResume();
                }
                break;

            default:
                if (gaodeMapHelper != null) {
                    gaodeMapHelper.onResume();
                }
                break;
        }
    }

    public void onPause() {
        switch (mapType) {
            case Baidu:
                if (baiduMapHelper != null) {
                    baiduMapHelper.onPause();
                }
                break;

            default:
                if (gaodeMapHelper != null) {
                    gaodeMapHelper.onPause();
                }
                break;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        switch (mapType) {
            case Baidu:
                break;

            default:
                if (gaodeMapHelper != null) {
                    gaodeMapHelper.onSaveInstanceState(outState);
                }
                break;
        }
    }

    public void onDestroy() {
        switch (mapType) {
            case Baidu:
                if (baiduMapHelper != null) {
                    baiduMapHelper.onDestroy();
                }
                break;

            default:
                if (gaodeMapHelper != null) {
                    gaodeMapHelper.onDestroy();
                }
                break;
        }
    }

    public void setMapLoadedListener(MapLoadedListener listener) {
        if (listener == null) {
            return;
        }
        this.mapLoadedListener = listener;
        switch (mapType) {
            case Baidu:
                ((BaiduMap) map).setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        mapLoadedListener.onMapLoaded();
                    }
                });
                break;

            default:
                ((AMap) map).setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
                    @Override
                    public void onMapLoaded() {
                        mapLoadedListener.onMapLoaded();
                    }
                });
                break;
        }
    }

    public void setMapClickListener(MapClickListener listener) {
        if (listener == null) {
            return;
        }
        this.mapClickListener = listener;
        final LatLng point = new LatLng();
        switch (mapType) {
            case Baidu:
                ((BaiduMap) map).setOnMapClickListener(new BaiduMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(com.baidu.mapapi.model.LatLng latLng) {
                        if (latLng != null) {
                            point.setLatitude(latLng.latitude);
                            point.setLongitude(latLng.longitude);
                            mapClickListener.onMapClick(point);
                        }
                    }

                    @Override
                    public boolean onMapPoiClick(MapPoi mapPoi) {
                        return false;
                    }
                });
                break;

            default:
                ((AMap) map).setOnMapClickListener(new AMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
                        if (latLng != null) {
                            point.setLatitude(latLng.latitude);
                            point.setLongitude(latLng.longitude);
                            mapClickListener.onMapClick(point);
                        }
                    }
                });
                break;
        }
    }

    public void setMarkerClickListener(MarkerClickListener listener) {
        if (listener == null) {
            return;
        }
        this.markerClickListener = listener;
        switch (mapType) {
            case Baidu:
                ((BaiduMap) map).setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
                        if (marker != null) {
                            markerClickListener.onMarkerClick(marker);
                        }
                        return false;
                    }
                });
                break;

            default:
                ((AMap) map).setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(com.amap.api.maps.model.Marker marker) {
                        if (marker != null) {
                            markerClickListener.onMarkerClick(marker);
                        }
                        return false;
                    }
                });
                break;
        }
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
}
