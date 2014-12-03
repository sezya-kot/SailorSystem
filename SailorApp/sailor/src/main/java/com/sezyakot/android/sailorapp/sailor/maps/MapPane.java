package com.sezyakot.android.sailorapp.sailor.maps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.sezyakot.android.sailorapp.sailor.DefaultActivity;
import com.sezyakot.android.sailorapp.sailor.R;

public class MapPane extends DefaultActivity {
    final int RQS_GooglePlayServices = 1;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();


        }

    private void setUI() {
        setWrapper(true);
        getLayoutInflater().inflate(R.layout.activity_maps, wrapper);
        setContentView(wrapper);
        setTitle(getString(R.string.gps));
        setCategoryTitle(true);

        
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
    }
}

