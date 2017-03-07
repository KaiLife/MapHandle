package com.geely.dilan.maphandle.map.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.geely.dilan.maphandle.map.baidu.BaiduMapHelper;
import com.geely.dilan.maphandle.map.bean.LatLng;
import com.geely.dilan.maphandle.map.gaode.GaodeMapHelper;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */
public class MapHelper {

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
        //#if MAP_TYPE == 1
//@        com.baidu.mapapi.SDKInitializer.initialize(context);
        //#endif
    }

    public MapHelper(@NonNull Object mapView) {
        //#if MAP_TYPE == 1
//@        if (mapView instanceof com.baidu.mapapi.map.MapView) {
//@            baiduMapHelper = new BaiduMapHelper((com.baidu.mapapi.map.MapView) mapView);
//@            map = baiduMapHelper.getBaiduMap();
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (mapView instanceof com.amap.api.maps.MapView) {
            gaodeMapHelper = new GaodeMapHelper((com.amap.api.maps.MapView) mapView);
            map = gaodeMapHelper.getAMap();
        }
        //#endif

        initMapSettings();
    }

    private void initMapSettings() {
        //#if MAP_TYPE == 1
//@        if (map instanceof com.baidu.mapapi.map.BaiduMap) {
//@            com.baidu.mapapi.map.BaiduMap baiduMap = (com.baidu.mapapi.map.BaiduMap) map;
//@
//@            com.baidu.mapapi.map.UiSettings uiSettings = baiduMap.getUiSettings();
//@            uiSettings.setOverlookingGesturesEnabled(false);
//@            uiSettings.setRotateGesturesEnabled(false);
//@            uiSettings.setScrollGesturesEnabled(true);
//@            uiSettings.setZoomGesturesEnabled(true);
//@
//@//            baiduMap.setMaxAndMinZoomLevel(21, 3);
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (map instanceof com.amap.api.maps.AMap) {
            com.amap.api.maps.AMap aMap = ((com.amap.api.maps.AMap) map);

            com.amap.api.maps.UiSettings uiSettings = aMap.getUiSettings();
            uiSettings.setMyLocationButtonEnabled(false);
            uiSettings.setRotateGesturesEnabled(false);
            uiSettings.setScrollGesturesEnabled(true);
            uiSettings.setScaleControlsEnabled(true);
            uiSettings.setZoomGesturesEnabled(true);
            uiSettings.setZoomControlsEnabled(false);
            uiSettings.setTiltGesturesEnabled(false);

//            aMap.setMaxZoomLevel(19);
//            aMap.setMinZoomLevel(3);
        }
        //#endif
    }

    public Object getMap() {
        return map;
    }

    public void onCreate(Bundle savedInstanceState) {
        //#if MAP_TYPE == 0
        if (gaodeMapHelper != null) {
            gaodeMapHelper.onCreate(savedInstanceState);
        }
        //#endif
    }

    public void onResume() {
        //#if MAP_TYPE == 1
//@        if (baiduMapHelper != null) {
//@            baiduMapHelper.onResume();
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (gaodeMapHelper != null) {
            gaodeMapHelper.onResume();
        }
        //#endif
    }

    public void onPause() {
        //#if MAP_TYPE == 1
//@        if (baiduMapHelper != null) {
//@            baiduMapHelper.onPause();
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (gaodeMapHelper != null) {
            gaodeMapHelper.onPause();
        }
        //#endif
    }

    public void onSaveInstanceState(Bundle outState) {
        //#if MAP_TYPE == 0
        if (gaodeMapHelper != null) {
            gaodeMapHelper.onSaveInstanceState(outState);
        }
        //#endif
    }

    public void onDestroy() {
        //#if MAP_TYPE == 1
//@        if (baiduMapHelper != null) {
//@            baiduMapHelper.onDestroy();
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (gaodeMapHelper != null) {
            gaodeMapHelper.onDestroy();
        }
        //#endif
    }

    /**
     * 移动地图
     *
     * @param target 经纬度
     * @param zoom   缩放级别
     */
    public void animateMap(@NonNull LatLng target, float zoom) {
        //#if MAP_TYPE == 1
//@        if (map instanceof com.baidu.mapapi.map.BaiduMap) {
//@            ((com.baidu.mapapi.map.BaiduMap) map).animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.newMapStatus(
//@                    new com.baidu.mapapi.map.MapStatus.Builder().target(new com.baidu.mapapi.model.LatLng(target.getLatitude(),
//@                            target.getLongitude())).zoom(zoom).build()));
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (map instanceof com.amap.api.maps.AMap) {
            ((com.amap.api.maps.AMap) map).animateCamera(com.amap.api.maps.CameraUpdateFactory.newCameraPosition(
                    new com.amap.api.maps.model.CameraPosition(new com.amap.api.maps.model.LatLng(target.getLatitude(),
                            target.getLongitude()), zoom, 0, 0)));
        }
        //#endif
    }

    /**
     * 地图放大
     */
    public void zoomIn() {
        //#if MAP_TYPE == 1
//@        if (map instanceof com.baidu.mapapi.map.BaiduMap) {
//@            ((com.baidu.mapapi.map.BaiduMap) map).animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.zoomIn());
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (map instanceof com.amap.api.maps.AMap) {
            ((com.amap.api.maps.AMap) map).animateCamera(com.amap.api.maps.CameraUpdateFactory.zoomIn());
        }
        //#endif
    }

    /**
     * 地图缩小
     */
    public void zoomOut() {
        //#if MAP_TYPE == 1
//@        if (map instanceof com.baidu.mapapi.map.BaiduMap) {
//@            ((com.baidu.mapapi.map.BaiduMap) map).animateMapStatus(com.baidu.mapapi.map.MapStatusUpdateFactory.zoomOut());
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (map instanceof com.amap.api.maps.AMap) {
            ((com.amap.api.maps.AMap) map).animateCamera(com.amap.api.maps.CameraUpdateFactory.zoomOut());
        }
        //#endif
    }

    public void setMapLoadedListener(MapLoadedListener listener) {
        if (listener == null) {
            return;
        }
        this.mapLoadedListener = listener;

        //#if MAP_TYPE == 1
//@        ((com.baidu.mapapi.map.BaiduMap) map).setOnMapLoadedCallback(new com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback() {
//@            @Override
//@            public void onMapLoaded() {
//@                mapLoadedListener.onMapLoaded();
//@            }
//@        });
        //#endif

        //#if MAP_TYPE == 0
        ((com.amap.api.maps.AMap) map).setOnMapLoadedListener(new com.amap.api.maps.AMap.OnMapLoadedListener() {
            @Override
            public void onMapLoaded() {
                mapLoadedListener.onMapLoaded();
            }
        });
        //#endif
    }

    public void setMapClickListener(MapClickListener listener) {
        if (listener == null) {
            return;
        }
        this.mapClickListener = listener;
        final LatLng point = new LatLng();

        //#if MAP_TYPE == 1
//@        ((com.baidu.mapapi.map.BaiduMap) map).setOnMapClickListener(new com.baidu.mapapi.map.BaiduMap.OnMapClickListener() {
//@            @Override
//@            public void onMapClick(com.baidu.mapapi.model.LatLng latLng) {
//@                if (latLng != null) {
//@                    point.setLatitude(latLng.latitude);
//@                    point.setLongitude(latLng.longitude);
//@                    mapClickListener.onMapClick(point);
//@                }
//@            }
//@
//@            @Override
//@            public boolean onMapPoiClick(com.baidu.mapapi.map.MapPoi mapPoi) {
//@                return false;
//@            }
//@        });
        //#endif

        //#if MAP_TYPE == 0
        ((com.amap.api.maps.AMap) map).setOnMapClickListener(new com.amap.api.maps.AMap.OnMapClickListener() {
            @Override
            public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
                if (latLng != null) {
                    point.setLatitude(latLng.latitude);
                    point.setLongitude(latLng.longitude);
                    mapClickListener.onMapClick(point);
                }
            }
        });
        //#endif
    }

    public void setMarkerClickListener(MarkerClickListener listener) {
        if (listener == null) {
            return;
        }
        this.markerClickListener = listener;

        //#if MAP_TYPE == 1
//@        ((com.baidu.mapapi.map.BaiduMap) map).setOnMarkerClickListener(new com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener() {
//@            @Override
//@            public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
//@                if (marker != null) {
//@                    markerClickListener.onMarkerClick(marker);
//@                }
//@                return false;
//@            }
//@        });
        //#endif

        //#if MAP_TYPE == 0
        ((com.amap.api.maps.AMap) map).setOnMarkerClickListener(new com.amap.api.maps.AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.amap.api.maps.model.Marker marker) {
                if (marker != null) {
                    markerClickListener.onMarkerClick(marker);
                }
                return false;
            }
        });
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
}
