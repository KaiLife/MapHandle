package com.geely.dilan.maphandle.map.common;

import android.os.Bundle;

/**
 * Created by XinKai.Tong on 2017/3/10.
 */

public interface IMapService<M, T, L> {

    /**
     * 获取地图
     *
     * @return
     */
    M getMap();

    /**
     * onCreate 生命周期调用
     *
     * @param savedInstanceState
     * @param mapLocationListener
     */
    void onCreate(Bundle savedInstanceState, MapLocationListener<L> mapLocationListener);

    /**
     * onResume 生命周期调用
     */
    void onResume();

    /**
     * onPause 生命周期调用
     */
    void onPause();

    /**
     * onSaveInstanceState 生命周期调用
     *
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * onDestroy 生命周期调用
     */
    void onDestroy();

    /**
     * 地图放大
     */
    void zoomIn();

    /**
     * 地图缩小
     */
    void zoomOut();

    /**
     * 移动地图到某个位置
     *
     * @param target 经纬度
     * @param zoom   缩放级别
     */
    void animateMap(T target, float zoom);
}
