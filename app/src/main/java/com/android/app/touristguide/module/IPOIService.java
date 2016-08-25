package com.android.app.touristguide.module;

import com.android.app.touristguide.model.POIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
@SuppressWarnings("ALL")
public interface IPOIService {

    // Retrofit interface for the POIService
    @GET("nearbysearch/json?&key=AIzaSyDtUi03B1YIIe5hFcLRthxsxnUfS3iAB9E")
    Call<POIResponse> getPOI(@Query("location") String location, @Query("radius") String radius, @Query("type") String type);
}
