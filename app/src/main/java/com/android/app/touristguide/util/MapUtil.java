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

        // storing the start up location and adding it to the polygon options
        Location startingLoc=locationList.get(startPosition);
        rectOptions.add(getLatLng(startingLoc));
        // remove the current item from the list
        locationList.remove(startPosition);

        do{
            float shortDistance=0;
            Location tempLoc=null;
            int locPosition=0;
           for (int i=0;i<locationList.size();i++){

               if(shortDistance==0){
                   // checking for the shortest distance if it is 0 assign the first value to short distance and tempLoc and position
                   shortDistance=getDistanceBetweenLocations(startingLoc,locationList.get(i));
                   tempLoc=locationList.get(i);
                   locPosition=i;
               }else{
                  float temDistance=getDistanceBetweenLocations(startingLoc,locationList.get(i));
                   if(temDistance<shortDistance){
                       // checking whether the distance between the starting point and other points are lesser than the previous if yes store that values
                       // to  shortDistance ,tempLoc,locPosition
                       shortDistance=temDistance;
                       tempLoc=locationList.get(i);
                       locPosition=i;
                   }
               }

           }
            if(null!=tempLoc) {
                // after loop the location is stored in the temLoc will be the shortest distance point from the starting location
                rectOptions.add(getLatLng(tempLoc));
                startingLoc=tempLoc;
                locationList.remove(locPosition);
            }

            // while loop will continue till the locationList's size become zero

        }while (locationList.size()>0);
        rectOptions.add(getLatLng(locations.get(startPosition)));


        mapView.addPolygon(rectOptions);

    }

    public static void addMarker(GoogleMap map,MarkerOptions markerOptions){
        // adding marker to the google map
        map.addMarker(markerOptions);

    }

    private static LatLng getLatLng(Location loc){
        return new LatLng(loc.getLat(), loc.getLng());
    }

    private static float getDistanceBetweenLocations(Location location1, Location location2) {
        // calculating the distance between two locations
        float[] result = new float[5];
        android.location.Location.distanceBetween(location1.getLat(), location1.getLng(), location2.getLat(), location2.getLng(), result);
        return result[0];
    }

}
