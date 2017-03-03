package com.geely.dilan.maphandle.map.common;

import android.content.Context;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClientOption;
import com.geely.dilan.maphandle.BuildConfig;
import com.geely.dilan.maphandle.map.baidu.BaiduLocationService;
import com.geely.dilan.maphandle.map.bean.MapLocation;
import com.geely.dilan.maphandle.map.gaode.GaodeLocationService;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public class LocationService {

    private MapType mapType = BuildConfig.MAP_TYPE;
    private BaiduLocationService baiduLocationService;
    private GaodeLocationService gaodeLocationService;
    private Object option;
    private MapLocationListener locationListener;
    private BDLocationListener bdLocationListener;
    private AMapLocationListener aMapLocationListener;

    public LocationService(Context context) {
        switch (mapType) {
            case Baidu:
                baiduLocationService = new BaiduLocationService(context);
                break;

            default:
                gaodeLocationService = new GaodeLocationService(context);
                break;
        }
    }

    public void registerListener(MapLocationListener listener) {
        if (listener == null) {
            return;
        }
        this.locationListener = listener;
        final MapLocation location = new MapLocation();
        switch (mapType) {
            case Baidu:
                bdLocationListener = new BDLocationListener() {
                    @Override
                    public void onReceiveLocation(BDLocation bdLocation) {
                        if (null != bdLocation && bdLocation.getLocType() != BDLocation.TypeServerError) {
                            location.setLatitude(bdLocation.getLatitude());
                            location.setLongitude(bdLocation.getLongitude());
                            location.setAddress(bdLocation.getAddrStr());
                            locationListener.onLocationChanged(location);
                        }
                    }

                    @Override
                    public void onConnectHotSpotMessage(String s, int i) {

                    }
                };
                baiduLocationService.registerListener(bdLocationListener);
                break;

            default:
                aMapLocationListener = new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation aMapLocation) {
                        if (aMapLocation != null) {
                            location.setLatitude(aMapLocation.getLatitude());
                            location.setLongitude(aMapLocation.getLongitude());
                            location.setAddress(aMapLocation.getAddress());
                            locationListener.onLocationChanged(location);
                        }
                    }
                };
                gaodeLocationService.registerListener(aMapLocationListener);
                break;
        }
    }

    public void unregisterListener() {
        locationListener = null;
        switch (mapType) {
            case Baidu:
                baiduLocationService.unregisterListener(bdLocationListener);
                bdLocationListener = null;
                break;

            default:
                gaodeLocationService.unregisterListener();
                aMapLocationListener = null;
                break;
        }
    }

    public void start() {
        switch (mapType) {
            case Baidu:
                baiduLocationService.start();
                break;

            default:
                gaodeLocationService.start();
                break;
        }
    }

    public void stop() {
        switch (mapType) {
            case Baidu:
                baiduLocationService.stop();
                break;

            default:
                gaodeLocationService.stop();
                break;
        }
    }

    public void setLocationOption(Object option) {
        if (option == null) {
            return;
        }
        this.option = option;
        switch (mapType) {
            case Baidu:
                if (option instanceof LocationClientOption) {
                    baiduLocationService.setLocationOption((LocationClientOption) option);
                }
                break;

            default:
                if (option instanceof AMapLocationClientOption) {
                    gaodeLocationService.setLocationOption((AMapLocationClientOption) option);
                }
                break;
        }
    }

//    public Object getOption() {
//        return this.option;
//    }

    public Object getDefaultLocationClientOption() {
        switch (mapType) {
            case Baidu:
                return baiduLocationService.getDefaultLocationClientOption();

            default:
                return gaodeLocationService.getDefaultLocationClientOption();
        }
    }

    public void destroyLocation() {
        switch (mapType) {
            case Baidu:
                baiduLocationService.destroyLocation();
                break;

            default:
                gaodeLocationService.destroyLocation();
                break;
        }
    }

    public interface MapLocationListener {
        void onLocationChanged(MapLocation location);
    }

}
