package com.android.app.touristguide.util;
import com.android.app.touristguide.model.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.List;

public class MapUtil {

    public static  void drawPolygon(GoogleMap mapView,List<Location> locations,int startPosition){

        List<Location> locationList=new ArrayList<>();
        locationList.addAll(locations);
        PolygonOptions rectOptions = new PolygonOptions();
        Location startingLoc=locationList.get(startPosition);
        rectOptions.add(getLatLng(startingLoc));
        locationList.remove(startPosition);

        do{
            float shortDistance=0;
            Location tempLoc=null;
            int locPosition=0;
           for (int i=0;i<locationList.size();i++){

               if(shortDistance==0){
                   shortDistance=getDistanceBetweenLocations(startingLoc,locationList.get(i));
                   tempLoc=locationList.get(i);
                   locPosition=i;
               }else{
                  float temDistance=getDistanceBetweenLocations(startingLoc,locationList.get(i));
                   if(temDistance<shortDistance){
                       shortDistance=temDistance;
                       tempLoc=locationList.get(i);
                       locPosition=i;
                   }
               }

           }
            if(null!=tempLoc) {
                rectOptions.add(getLatLng(tempLoc));
                startingLoc=tempLoc;
                locationList.remove(locPosition);
            }

        }while (locationList.size()>0);
        rectOptions.add(getLatLng(locations.get(startPosition)));


        mapView.addPolygon(rectOptions);

    }

    public static void addMarker(GoogleMap map,MarkerOptions markerOptions){
        map.addMarker(markerOptions);

    }

    private static LatLng getLatLng(Location loc){
        return new LatLng(loc.getLat(), loc.getLng());
    }

    private static float getDistanceBetweenLocations(Location location1, Location location2) {
        float[] result = new float[5];
        android.location.Location.distanceBetween(location1.getLat(), location1.getLng(), location2.getLat(), location2.getLng(), result);
        return result[0];
    }

}
