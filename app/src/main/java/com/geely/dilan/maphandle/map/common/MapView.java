package com.geely.dilan.maphandle.map.common;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by XinKai.Tong on 2017/3/7.
 */

public class MapView extends FrameLayout {

    //#if MAP_TYPE == 1
    private com.baidu.mapapi.map.MapView baiduMap;
    //#endif

    //#if MAP_TYPE == 0
//@    private com.amap.api.maps.MapView gaodeMap;
    //#endif

    public MapView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(@NonNull Context context) {
        //#if MAP_TYPE == 1
        baiduMap = new com.baidu.mapapi.map.MapView(context,
                new com.baidu.mapapi.map.BaiduMapOptions().compassEnabled(false).zoomControlsEnabled(false));
        baiduMap.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(baiduMap);
        //#endif

        //#if MAP_TYPE == 0
//@        gaodeMap = new com.amap.api.maps.MapView(context);
//@        gaodeMap.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//@        addView(gaodeMap);
        //#endif

        Log.d("MapView: ", String.valueOf(getChildCount()));
    }

    public Object getMap() {
        //#if MAP_TYPE == 1
        return baiduMap;
        //#endif

        //#if MAP_TYPE == 0
//@        return gaodeMap;
        //#endif
    }
}
