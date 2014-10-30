package com.sezyakot.sailor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.sezyakot.sailor.system.Debug;

public class MapPane extends DefaultActivity {
    public static final String LOG_TAG = "MapPane";

    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_map);
//        setWrapper();
        if (Debug.MODE) {
            Log.d(LOG_TAG, "GooglePlayService: " +
                    GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext()));
        }
//        getLayoutInflater().inflate(R.layout.fragment_map, wrapper);
//        setContentView(wrapper);


//        mGoogleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
//        if (mGoogleMap == null) {
//            if (Debug.MODE) {
//                Log.e(LOG_TAG, "mGoogleMap not found!");
//            }
//        }
//
//        if (Debug.MODE) {
//            Log.d(LOG_TAG, "mGoogleMap is existed!");
//        }

    }

}
