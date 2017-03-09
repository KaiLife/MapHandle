package com.geely.dilan.maphandle.map.utils;

import com.geely.dilan.maphandle.map.bean.LatLng;

/**
 * Created by XinKai.Tong on 2017/3/9.
 */

public class MapUtils {

    public static double getDistance(LatLng latLng1, LatLng latLng2) {
        if (latLng1 == null || latLng2 == null) {
            return 0;
        }

        //#if MAP_TYPE == 1
        return com.baidu.mapapi.utils.DistanceUtil.getDistance(
                new com.baidu.mapapi.model.LatLng(latLng1.getLatitude(), latLng1.getLongitude()),
                new com.baidu.mapapi.model.LatLng(latLng2.getLatitude(), latLng2.getLongitude()));
        //#endif

        //#if MAP_TYPE == 0
//@        return com.amap.api.maps.AMapUtils.calculateLineDistance(
//@                new com.amap.api.maps.model.LatLng(latLng1.getLatitude(), latLng1.getLongitude()),
//@                new com.amap.api.maps.model.LatLng(latLng2.getLatitude(), latLng2.getLongitude()));
        //#endif
    }

}
