package com.android.app.touristguide.ui.activity;

import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import com.android.app.touristguide.R;
import com.android.app.touristguide.TouristGuideApp;
import com.android.app.touristguide.databinding.ActivityMapsBinding;
import com.android.app.touristguide.ui.view.MapsActivityView;
import com.android.app.touristguide.util.ConnectivityReceiver;
import com.android.app.touristguide.viewmodel.ActivityMapViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback ,MapsActivityView,ConnectivityReceiver.ConnectivityReceiverListener{

    public static final String TYPE = "com.google.map.places.TYPE";
    public static final String LOCATION = "com.google.map.location";
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private ActivityMapViewModel viewModel;
    private Location location;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setSupportActionBar(binding.toolbar);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel=new ActivityMapViewModel(MapsActivity.this,this);
        binding.setHandler(viewModel);
        type=getIntent().getStringExtra(TYPE);
        location=getIntent().getParcelableExtra(LOCATION);
        TouristGuideApp.setConnectivityChangeListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        viewModel.setGoogleMap(mMap);
        viewModel.initPOISearch(type,location);
    }

    @Override
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return binding.swipeRefresh;
    }

    @Override
    public GoogleMap getGoogleMap() {
        return mMap;
    }

    @Override
    public void showSnackBarWithAction(String message) {
        Snackbar snackbar = Snackbar
                .make( binding.mainContainer,message, Snackbar.LENGTH_LONG)
                .setAction(TouristGuideApp.getStringRes(R.string.yes), view -> {
                    viewModel.setAsStartingPoint();
                });
        snackbar.show();
    }

    @Override
    public void setConnectivityError(boolean isConnected) {
        onNetworkConnectionChanged(isConnected);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        if(!isConnected) {
            Snackbar snackbar = Snackbar
                    .make(binding.mainContainer, TouristGuideApp.getStringRes(R.string.please_check_internet_connection), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
