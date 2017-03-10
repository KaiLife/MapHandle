package com.geely.dilan.maphandle.map.common;

/**
 * Created by XinKai.Tong on 2017/3/1.
 */

public interface ILocationService<L, O> {

    /**
     * 注册定位监听
     * @param listener
     */
    void registerListener(L listener);

    /**
     * 注销定位监听
     */
    void unregisterListener();

    /**
     * 开始定位
     */
    void start();

    /**
     * 停止定位
     */
    void stop();

    /**
     * 设置定位设置
     * @param option
     */
    void setLocationOption(O option);

    /**
     * 获取定位设置
     * @return
     */
    O getOption();

    /**
     * 获取默认定位设置
     * @return
     */
    O getDefaultLocationClientOption();

    /**
     * 销毁定位
     */
    void destroyLocation();

}
