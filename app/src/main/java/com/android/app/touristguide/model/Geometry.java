package com.android.app.touristguide.model;


public class Geometry {

    // store the Geometry info of the POI
    private Location location;

    public Geometry(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
