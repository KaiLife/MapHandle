package com.geely.dilan.maphandle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.geely.dilan.maphandle.map.common.MapHelper;
import com.geely.dilan.maphandle.map.common.MapView;


public class MainActivity extends AppCompatActivity {

    private MapHelper mapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView mMapView = (MapView) findViewById(R.id.map);
        mapHelper = new MapHelper(mMapView.getMap());
        mapHelper.onCreate(savedInstanceState);
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

}
