package com.geely.dilan.maphandle.map.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
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
        SDKInitializer.initialize(context);
        //#endif
    }

    public MapHelper(@NonNull Object mapView) {
        //#if MAP_TYPE == 1
        if (mapView instanceof com.baidu.mapapi.map.MapView) {
            baiduMapHelper = new BaiduMapHelper((com.baidu.mapapi.map.MapView) mapView);
            map = baiduMapHelper.getBaiduMap();
        }
        //#endif

        //#if MAP_TYPE == 0
//@        if (mapView instanceof com.amap.api.maps.MapView) {
//@            gaodeMapHelper = new GaodeMapHelper((com.amap.api.maps.MapView) mapView);
//@            map = gaodeMapHelper.getAMap();
//@        }
        //#endif
    }

    public Object getMap() {
        return map;
    }

    public void onCreate(Bundle savedInstanceState) {
        //#if MAP_TYPE == 0
//@        if (gaodeMapHelper != null) {
//@            gaodeMapHelper.onCreate(savedInstanceState);
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
    }

    public void setMapLoadedListener(MapLoadedListener listener) {
        if (listener == null) {
            return;
        }
        this.mapLoadedListener = listener;

        //#if MAP_TYPE == 1
        ((BaiduMap) map).setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mapLoadedListener.onMapLoaded();
            }
        });
        //#endif

        //#if MAP_TYPE == 0
//@        ((AMap) map).setOnMapLoadedListener(new AMap.OnMapLoadedListener() {
//@            @Override
//@            public void onMapLoaded() {
//@                mapLoadedListener.onMapLoaded();
//@            }
//@        });
        //#endif
    }

    public void setMapClickListener(MapClickListener listener) {
        if (listener == null) {
            return;
        }
        this.mapClickListener = listener;
        final LatLng point = new LatLng();

        //#if MAP_TYPE == 1
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
        //#endif

        //#if MAP_TYPE == 0
//@        ((AMap) map).setOnMapClickListener(new AMap.OnMapClickListener() {
//@            @Override
//@            public void onMapClick(com.amap.api.maps.model.LatLng latLng) {
//@                if (latLng != null) {
//@                    point.setLatitude(latLng.latitude);
//@                    point.setLongitude(latLng.longitude);
//@                    mapClickListener.onMapClick(point);
//@                }
//@            }
//@        });
        //#endif
    }

    public void setMarkerClickListener(MarkerClickListener listener) {
        if (listener == null) {
            return;
        }
        this.markerClickListener = listener;

        //#if MAP_TYPE == 1
        ((BaiduMap) map).setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
                if (marker != null) {
                    markerClickListener.onMarkerClick(marker);
                }
                return false;
            }
        });
        //#endif

        //#if MAP_TYPE == 0
//@        ((AMap) map).setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
//@            @Override
//@            public boolean onMarkerClick(com.amap.api.maps.model.Marker marker) {
//@                if (marker != null) {
//@                    markerClickListener.onMarkerClick(marker);
//@                }
//@                return false;
//@            }
//@        });
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
