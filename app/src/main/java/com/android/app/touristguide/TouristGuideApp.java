package com.android.app.touristguide;

import android.app.Application;
import android.content.Context;

import com.android.app.touristguide.constant.APIConstants;
import com.android.app.touristguide.util.ConnectivityReceiver;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TouristGuideApp extends Application {

    private static Context appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance=this;
    }
    public synchronized static Context getAppInstance() {
        return appInstance;
    }


    // Returning Retrofit with the Base Url
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Returning general string variable for the Resource Id
    public static String getStringRes(int id) {
        return getAppInstance().getResources().getString(id);
    }


    // Setter to ConnectivityChangeListener
    public static void setConnectivityChangeListener(ConnectivityReceiver.ConnectivityReceiverListener connectionChangeListener) {
        ConnectivityReceiver.setConnectivityReceiverListener(connectionChangeListener);
    }


}
