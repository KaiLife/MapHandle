package com.geely.dilan.maphandle.map.common;

import android.content.Context;

import com.geely.dilan.maphandle.map.baidu.BaiduLocationService;
import com.geely.dilan.maphandle.map.bean.MapLocation;
import com.geely.dilan.maphandle.map.gaode.GaodeLocationService;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class LocationService {

    private BaiduLocationService baiduLocationService;
    private GaodeLocationService gaodeLocationService;
    private Object option;
    private MapLocationListener locationListener;

    //#if MAP_TYPE == 1
//@    private com.baidu.location.BDLocationListener bdLocationListener;
    //#endif

    //#if MAP_TYPE == 0
    private com.amap.api.location.AMapLocationListener aMapLocationListener;
    //#endif

    public LocationService(Context context) {
        //#if MAP_TYPE == 1
//@        baiduLocationService = new BaiduLocationService(context);
        //#endif

        //#if MAP_TYPE == 0
        gaodeLocationService = new GaodeLocationService(context);
        //#endif
    }

    public void registerListener(MapLocationListener listener) {
        if (listener == null) {
            return;
        }
        this.locationListener = listener;
        final MapLocation location = new MapLocation();

        //#if MAP_TYPE == 1
//@        bdLocationListener = new com.baidu.location.BDLocationListener() {
//@            @Override
//@            public void onReceiveLocation(com.baidu.location.BDLocation bdLocation) {
//@                if (null != bdLocation && bdLocation.getLocType() != com.baidu.location.BDLocation.TypeServerError) {
//@                    location.setLatitude(bdLocation.getLatitude());
//@                    location.setLongitude(bdLocation.getLongitude());
//@                    location.setAddress(bdLocation.getAddrStr());
//@                    locationListener.onLocationChanged(location);
//@                }
//@            }
//@
//@            @Override
//@            public void onConnectHotSpotMessage(String s, int i) {
//@
//@            }
//@        };
//@        baiduLocationService.registerListener(bdLocationListener);
        //#endif

        //#if MAP_TYPE == 0
        aMapLocationListener = new com.amap.api.location.AMapLocationListener() {
            @Override
            public void onLocationChanged(com.amap.api.location.AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    location.setLatitude(aMapLocation.getLatitude());
                    location.setLongitude(aMapLocation.getLongitude());
                    location.setAddress(aMapLocation.getAddress());
                    locationListener.onLocationChanged(location);
                }
            }
        };
        gaodeLocationService.registerListener(aMapLocationListener);
        //#endif
    }

    public void unregisterListener() {
        locationListener = null;

        //#if MAP_TYPE == 1
//@        baiduLocationService.unregisterListener(bdLocationListener);
//@        bdLocationListener = null;
        //#endif

        //#if MAP_TYPE == 0
        gaodeLocationService.unregisterListener();
        aMapLocationListener = null;
        //#endif
    }

    public void start() {
        //#if MAP_TYPE == 1
//@        baiduLocationService.start();
        //#endif

        //#if MAP_TYPE == 0
        gaodeLocationService.start();
        //#endif
    }

    public void stop() {
        //#if MAP_TYPE == 1
//@        baiduLocationService.stop();
        //#endif

        //#if MAP_TYPE == 0
        gaodeLocationService.stop();
        //#endif
    }

    public void setLocationOption(Object option) {
        if (option == null) {
            return;
        }
        this.option = option;

        //#if MAP_TYPE == 1
//@        if (option instanceof com.baidu.location.LocationClientOption) {
//@            baiduLocationService.setLocationOption((com.baidu.location.LocationClientOption) option);
//@        }
        //#endif

        //#if MAP_TYPE == 0
        if (option instanceof com.amap.api.location.AMapLocationClientOption) {
            gaodeLocationService.setLocationOption((com.amap.api.location.AMapLocationClientOption) option);
        }
        //#endif
    }

//    public Object getOption() {
//        return this.option;
//    }

    public Object getDefaultLocationClientOption() {
        //#if MAP_TYPE == 1
//@        return baiduLocationService.getDefaultLocationClientOption();
        //#endif

        //#if MAP_TYPE == 0
        return gaodeLocationService.getDefaultLocationClientOption();
        //#endif
    }

    public void destroyLocation() {
        //#if MAP_TYPE == 1
//@        baiduLocationService.destroyLocation();
        //#endif

        //#if MAP_TYPE == 0
        gaodeLocationService.destroyLocation();
        //#endif
    }

    public interface MapLocationListener {
        void onLocationChanged(MapLocation location);
    }

}
