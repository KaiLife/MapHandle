package com.geely.dilan.maphandle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.geely.dilan.maphandle.map.baidu.BaiduLocationService;

public class LocationActivity extends AppCompatActivity implements BDLocationListener, AMapLocationListener {

    //#if MAP_TYPE == 0
//@    private GaodeLocationService locationService;
    //#endif

    //#if MAP_TYPE == 1
    private BaiduLocationService locationService;
    //#endif

    private Button btn_loc;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());

        btn_loc = (Button) findViewById(R.id.btn_loc);
        btn_loc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (btn_loc.getText().toString().equals(getString(R.string.startlocation))) {
                    locationService.start();// 定位SDK
                    // start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
                    btn_loc.setText(getString(R.string.stoplocation));
                } else {
                    locationService.stop();
                    btn_loc.setText(getString(R.string.startlocation));
                }
            }
        });

        //#if MAP_TYPE == 0
//@        locationService = new GaodeLocationService(this);
        //#endif

        //#if MAP_TYPE == 1
        locationService = new BaiduLocationService(this);
        //#endif

        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationService.registerListener(this);
    }

    @Override
    protected void onStop() {
        locationService.unregisterListener(); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationService.destroyLocation();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation != null) {
            tv_content.setText("经    度    : " + bdLocation.getLongitude() + "\n"
                    + "纬    度    : " + bdLocation.getLatitude());
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            tv_content.setText("经    度    : " + aMapLocation.getLongitude() + "\n"
                    + "纬    度    : " + aMapLocation.getLatitude());
        }
    }
}
