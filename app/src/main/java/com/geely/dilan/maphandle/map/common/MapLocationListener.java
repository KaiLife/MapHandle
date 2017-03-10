package com.geely.dilan.maphandle.map.common;

/**
 * Created by XinKai.Tong on 2017/3/10.
 */

public interface MapLocationListener<L> {
    /**
     * 第一次定位成功
     *
     * @param location 百度-BDLocation，高德-AMapLocation
     */
    void onFirstSuccess(L location);

    /**
     * 定位成功
     *
     * @param location 百度-BDLocation，高德-AMapLocation
     */
    void onLocationSuccess(L location);
}
