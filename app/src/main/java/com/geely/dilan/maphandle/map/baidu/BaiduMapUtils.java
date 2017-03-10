package com.geely.dilan.maphandle.map.baidu;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

/**
 * Created by XinKai.Tong on 2017/3/10.
 */
//#if MAP_TYPE == 1
public class BaiduMapUtils {

    public static double getDistance(LatLng latLng1, LatLng latLng2) {
        if (latLng1 == null || latLng2 == null) {
            return 0;
        }

        return DistanceUtil.getDistance(latLng1, latLng2);
    }
}
//#endif
