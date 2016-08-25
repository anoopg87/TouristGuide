package com.android.app.touristguide.ui.view;

import android.support.v4.widget.SwipeRefreshLayout;

import com.google.android.gms.maps.GoogleMap;

public interface MapsActivityView {

    SwipeRefreshLayout getSwipeRefreshLayout();
    GoogleMap getGoogleMap();

    void showSnackBarWithAction(String string);

    void setConnectivityError(boolean isConnected);
}
