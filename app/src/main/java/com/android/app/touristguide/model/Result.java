package com.android.app.touristguide.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.app.touristguide.BR;

public class Result extends BaseObservable{

    // POIService response
    private String id;
    private String place_id;
    private String name;
    private String icon;
    private Geometry geometry;
    private String vicinity;

    public Result(String id, String place_id, String name, String icon, Geometry geometry, String vicinity) {
        this.id = id;
        this.place_id = place_id;
        this.name = name;
        this.icon = icon;
        this.geometry = geometry;
        this.vicinity = vicinity;
    }

    @Bindable
    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
        notifyPropertyChanged(BR.vicinity);
    }

    @Bindable
    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
        notifyPropertyChanged(BR.place_id);
    }

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        notifyPropertyChanged(BR.icon);
    }

    @Bindable
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
        notifyPropertyChanged(BR.geometry);
    }
}
