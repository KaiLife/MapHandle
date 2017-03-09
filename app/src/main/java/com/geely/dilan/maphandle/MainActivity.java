package com.geely.dilan.maphandle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.geely.dilan.maphandle.map.bean.LatLng;
import com.geely.dilan.maphandle.map.common.MapHelper;
import com.geely.dilan.maphandle.map.common.MapView;
import com.geely.dilan.maphandle.map.utils.MapUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MapHelper.MapLocationListenner {

    private MapHelper mapHelper;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mMapView = (MapView) findViewById(R.id.map);
        mapHelper = new MapHelper(this, mMapView.getMap());
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
                tv_content.setText("距离：" + MapUtils.getDistance(new LatLng(39.926516, 116.389366),
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
    public void onFirstSuccess(Object location) {
        if (location == null) {
            return;
        }

        if (location instanceof com.baidu.location.BDLocation) {
            com.baidu.location.BDLocation bdLocation = (com.baidu.location.BDLocation) location;
            mapHelper.animateMap(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()), 18);
        }

        if (location instanceof com.amap.api.location.AMapLocation) {
            com.amap.api.location.AMapLocation aMapLocation = (com.amap.api.location.AMapLocation) location;
            mapHelper.animateMap(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude()), 18);
        }
    }

    @Override
    public void onLocationSuccess(Object location) {

    }
}
