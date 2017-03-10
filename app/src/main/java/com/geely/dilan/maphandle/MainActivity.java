package com.geely.dilan.maphandle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.geely.dilan.maphandle.map.baidu.BaiduMapHelper;
import com.geely.dilan.maphandle.map.baidu.BaiduMapUtils;
import com.geely.dilan.maphandle.map.common.MapLocationListener;
import com.geely.dilan.maphandle.map.common.MapView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MapLocationListener<BDLocation> {

    //#if MAP_TYPE == 1
    private BaiduMapHelper mapHelper;
    //#endif

    //#if MAP_TYPE == 0
//@    private GaodeMapHelper mapHelper;
    //#endif

    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mMapView = (MapView) findViewById(R.id.map);

        //#if MAP_TYPE == 1
        mapHelper = new BaiduMapHelper(this, (com.baidu.mapapi.map.MapView) mMapView.getMap());
        //#endif

        //#if MAP_TYPE == 0
//@        mapHelper = new GaodeMapHelper(this, (com.amap.api.maps.MapView) mMapView.getMap());
        //#endif

        mapHelper.onCreate(savedInstanceState, this);

        tv_content = (TextView) findViewById(R.id.tv_content);
        findViewById(R.id.btn_test).setOnClickListener(this);
        findViewById(R.id.btn_zoomin).setOnClickListener(this);
        findViewById(R.id.btn_zoomout).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapHelper.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapHelper.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapHelper.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test:
                mapHelper.animateMap(new LatLng(39.983456, 116.3154950), 18);
                tv_content.setText("距离：" + BaiduMapUtils.getDistance(new LatLng(39.926516, 116.389366),
                        new LatLng(39.924870, 116.403270)) + "m");
                break;

            case R.id.btn_zoomin:
                mapHelper.zoomIn();
                break;

            case R.id.btn_zoomout:
                mapHelper.zoomOut();
                break;

            default:
                break;
        }
    }

    @Override
    public void onFirstSuccess(BDLocation location) {
        if (location == null) {
            return;
        }

        mapHelper.animateMap(new LatLng(location.getLatitude(), location.getLongitude()), 18);
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }
}
