package com.sezyakot.android.sailorapp.sailor.maps;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.sezyakot.android.sailorapp.sailor.DefaultActivity;
import com.sezyakot.android.sailorapp.sailor.R;

public class GPS extends DefaultActivity implements View.OnClickListener {

    public static final String TAG = GPS.class.getSimpleName();
    final int RQS_GooglePlayServices = 1;

    LinearLayout mMapLL;
    LinearLayout mSearchLL;
    LinearLayout mFavourites;
    LinearLayout mSettings;

    MapFragment myMapFragment;
    private GoogleMap mMap;
    private static final String TAG_MYMAPFRAGMENT = "TAG_MyMapFragment";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();


    }

    private void setUI() {
        setWrapper(true);
        getLayoutInflater().inflate(R.layout.gps, wrapper);
        setContentView(wrapper);
        setTitle(getString(R.string.gps));
        setCategoryTitle(true);

        mMapLL = (LinearLayout) findViewById(R.id.map);
        mSearchLL = (LinearLayout) findViewById(R.id.search);
        mFavourites = (LinearLayout) findViewById(R.id.favourites);
        mSettings = (LinearLayout) findViewById(R.id.settings);

        mMapLL.setOnClickListener(this);
        mSearchLL.setOnClickListener(this);
        mFavourites.setOnClickListener(this);
        mSettings.setOnClickListener(this);

    }

//    @Override
//    protected void onResume() {

//        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
//        if (resultCode == ConnectionResult.SUCCESS){
//            Toast.makeText(getApplicationContext(),
//                    "isGooglePlayServicesAvailable SUCCESS",
//                    Toast.LENGTH_LONG).show();
//
//            if(mMap == null){
//                mMap = myMapFragment.getMap();
//                if(mMap != null){
//                    mMap.setMyLocationEnabled(true);
//                    mMap.getMyLocation();
////                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
////                            new CameraPosition(
////                                    new LatLng(
////                                            mMap.getMyLocation().getLatitude(),
////                                            mMap.getMyLocation().getLongitude()),
////                                    0,
////                                    0,
////                                    0)
////                    ));
//                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//                }else{
//                    Toast.makeText(getApplicationContext(),
//                            "cannot getMap!",
//                            Toast.LENGTH_LONG).show();
//                }
//            }
//
//        }else{
//            GooglePlayServicesUtil.getErrorDialog(resultCode, this, RQS_GooglePlayServices);
//        }
//    }

    @Override
    public void onClick(View v) {
        FragmentManager fm = getFragmentManager();

        switch (v.getId()) {
            case R.id.map: {

                myMapFragment = (MapFragment)fm.findFragmentByTag(TAG_MYMAPFRAGMENT);
                if(myMapFragment == null){
                    myMapFragment = MapFragment.newInstance();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container, myMapFragment, TAG_MYMAPFRAGMENT);
                    fragmentTransaction.commit();
                }

                break;
            }
            case R.id.search: {

                myMapFragment = (MapFragment)fm.findFragmentByTag(TAG_MYMAPFRAGMENT);
                if(myMapFragment == null){
                    myMapFragment = MapFragment.newInstance();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.add(R.id.fragment_container, myMapFragment, TAG_MYMAPFRAGMENT);
                    fragmentTransaction.commit();
                }

                break;
            }
            case R.id.favourites: {
                break;
            }
            case R.id.settings: {
                break;
            }
            default: break;
        }
    }
}
