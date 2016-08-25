package com.android.app.touristguide.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.app.touristguide.R;
import com.android.app.touristguide.TouristGuideApp;
import com.android.app.touristguide.databinding.ActivityMainBinding;
import com.android.app.touristguide.ui.view.LocationServiceView;
import com.android.app.touristguide.util.LocationServiceProvider;
import com.android.app.touristguide.util.LogUtil;

public class MainActivity extends AppCompatActivity implements LocationServiceView {

    private static final int PERMISSIONS_REQUEST_READ_LOCATION = 10000;
    private static final String TAG = "MainActivity";
    private LocationServiceProvider locationServiceProvider;
    private Location location;
private  ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        // initializing location service provider
        locationServiceProvider=new LocationServiceProvider(MainActivity.this,this);
        // request permission for using the user location
        requestPermission4UsingUserLocation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(null!=locationServiceProvider)locationServiceProvider.onStart();


    }

    @Override
    protected void onPause() {
        super.onPause();
        if(null!=locationServiceProvider)locationServiceProvider.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if(null!=locationServiceProvider)locationServiceProvider.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(null!=locationServiceProvider)locationServiceProvider.onStop();

    }



    private void requestPermission4UsingUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                 Manifest.permission.ACCESS_COARSE_LOCATION,},
                    PERMISSIONS_REQUEST_READ_LOCATION);
        } else {


            // starting the location update
            locationServiceProvider.startLocationUpdates();
            // permission granted
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Call back for the request permissions
        switch (requestCode) {
            case MainActivity.PERMISSIONS_REQUEST_READ_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    locationServiceProvider.startLocationUpdates();
                } else {
                    // permission dined
                    locationServiceProvider.stopLocationUpdates();
                    finish();
                }

            }
        }

    }

    @Override
    public void setCurrentLocation(Location location) {

        // setting up current location
        this.location=location;
        if(null!=location) {
            LogUtil.debug(TAG, location.getLatitude() + " " + location.getLongitude());
        }else{

            showLocationError();
        }

    }
    public  Location getLocation(){
        return location;
    }

    public void showLocationError(){
        Snackbar snackbar=Snackbar.make(binding.fragmentLoadingSpace, TouristGuideApp.getStringRes(R.string.please_check_gsp_settings),Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
