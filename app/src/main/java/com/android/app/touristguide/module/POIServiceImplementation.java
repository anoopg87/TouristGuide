package com.android.app.touristguide.module;
import com.android.app.touristguide.R;
import com.android.app.touristguide.TouristGuideApp;
import com.android.app.touristguide.model.POIResponse;
import com.android.app.touristguide.util.LogUtil;
import com.google.gson.Gson;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class POIServiceImplementation {
    public  void invokePoiService(String location,String radius,String type,POIServiceCallback callback){
        // Creating ApiInterface instance using the Global retrofit object
        IPOIService apiInterface = TouristGuideApp.getRetrofit().create(IPOIService.class);
        Call<POIResponse> call= apiInterface.getPOI(location,radius,type);
        // clone is used for calling the same url with same parameters to the same server multiple time
        call.clone().enqueue(new Callback<POIResponse>() {
            @Override
            public void onResponse(Call<POIResponse> call, Response<POIResponse> POIList) {
                LogUtil.debug("OnResponse",new Gson().toJson(POIList));
                callback.onResponse(POIList.body());
            }
            @Override
            public void onFailure(Call<POIResponse> call, Throwable t) {
                callback.onFailure(TouristGuideApp.getStringRes(R.string.please_check_internet_connection));
            }
        });

    }


}
