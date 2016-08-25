package com.android.app.touristguide.module;
import com.android.app.touristguide.model.POIResponse;
public interface POIServiceCallback {

     // callback for POIService
     void onResponse(POIResponse poiList);
     void onFailure(String string);
}
