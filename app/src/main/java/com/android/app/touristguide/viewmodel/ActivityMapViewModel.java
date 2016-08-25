package com.android.app.touristguide.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.location.Location;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.android.app.touristguide.BR;
import com.android.app.touristguide.R;
import com.android.app.touristguide.TouristGuideApp;
import com.android.app.touristguide.model.POIResponse;
import com.android.app.touristguide.model.Result;
import com.android.app.touristguide.module.POIServiceCallback;
import com.android.app.touristguide.module.POIServiceImplementation;
import com.android.app.touristguide.ui.adapter.RecyclerViewBindingAdapter;
import com.android.app.touristguide.ui.adapter.RecyclerViewConfiguration;
import com.android.app.touristguide.ui.view.MapsActivityView;
import com.android.app.touristguide.util.ConnectivityReceiver;
import com.android.app.touristguide.util.MapUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ActivityMapViewModel extends BaseObservable{

    public final RecyclerViewConfiguration recyclerViewConfiguration = new RecyclerViewConfiguration();
    private GoogleMap googleMap;
    private String type;
    private Location location;
    private List<Result> results=new ArrayList<>();
    private final List<com.android.app.touristguide.model.Location> locationList=new ArrayList<>();
    private final Context context;
    private final MapsActivityView view;
    private RecyclerViewBindingAdapter<Result> adapter;
    private int startPosition=0;

    @Bindable
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
        notifyPropertyChanged(BR.results);
    }

    public final SwipeRefreshLayout.OnRefreshListener onRefreshListener= () -> {
        invokePOIService();
    };

    public ActivityMapViewModel(Context context, MapsActivityView view) {
        this.context = context;
        this.view = view;
        // setting up POIList with null value
        setupPOIList();
    }

    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;

    }


    public void initPOISearch(String type, Location location) {
        this.type = type;
        this.location = location;
        startInitialLoading();

    }

    private void invokePOIService() {

        // invoking the webservice by checking the internet connection


        if(ConnectivityReceiver.isConnected()) {
            String loc = location.getLatitude() + "," + location.getLongitude();

            POIServiceImplementation poiServiceImplementation = new POIServiceImplementation();
            poiServiceImplementation.invokePoiService(loc, "5000", type, new POIServiceCallback() {
                @Override
                public void onResponse(POIResponse poiList) {

                    // filtering the 20 results from the webservice callback

                    if (poiList.getResults().size() > 20) {
                        setResults(poiList.getResults().subList(0, 19));
                    } else {
                        setResults(poiList.getResults());
                    }

                    addMarkerToMap();
                    adapter.addItems(getResults());
                    view.getSwipeRefreshLayout().setRefreshing(false);
                    view.getSwipeRefreshLayout().setEnabled(false);

                }

                @Override
                public void onFailure(String string) {
                    view.setConnectivityError(false);
                    view.getSwipeRefreshLayout().setRefreshing(false);
                }
            });
        }else{
            view.setConnectivityError(ConnectivityReceiver.isConnected());
        }

    }

    private void setupPOIList() {

        recyclerViewConfiguration.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewConfiguration.setItemAnimator(new DefaultItemAnimator());
        adapter=new RecyclerViewBindingAdapter<>(R.layout.poi_detail_layout, BR.poi,results);
        adapter.setItemClickListener((position, item) -> {

            // ask for setting the location as start up location
            view.showSnackBarWithAction(TouristGuideApp.getStringRes(R.string.set_as_startup_location));
            startPosition=position;

        });
        recyclerViewConfiguration.setAdapter(adapter);
    }

    private void addMarkerToMap() {

        // adding markers for all the location listed
        for (Result poi : results) {
            locationList.add(poi.getGeometry().getLocation());
            LatLng latLng = new LatLng(poi.getGeometry().getLocation().getLat(), poi.getGeometry().getLocation().getLng());
            MapUtil.addMarker(googleMap, new MarkerOptions().position(latLng).title(poi.getName()));
        }
    }


    private void startInitialLoading() {
        // start the initial loading with swipe refresh layout
        view.getSwipeRefreshLayout().post(() -> {
            view.getSwipeRefreshLayout().setRefreshing(true);
            invokePOIService();
        });
    }

    public void setAsStartingPoint() {


        googleMap.clear();
        addMarkerToMap();
        // drawing the ploygon on map with given points and start up location by calculating shortest distance
        MapUtil.drawPolygon(googleMap,locationList,startPosition);
    }
}
