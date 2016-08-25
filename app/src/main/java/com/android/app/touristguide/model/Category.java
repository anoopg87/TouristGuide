package com.android.app.touristguide.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.app.touristguide.BR;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Category extends BaseObservable {

    // Category for listing type of interest

    private String categoryName;
    private String categoryValue;

    public Category(String categoryName, String categoryValue) {
        this.categoryName = categoryName;
        this.categoryValue = categoryValue;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        notifyPropertyChanged(BR.categoryName);
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
        notifyPropertyChanged(BR.categoryValue);
    }

    @Bindable
    public String getCategoryName() {
        return categoryName;
    }

    @Bindable
    public String getCategoryValue() {
        return categoryValue;
    }

    public static List<Category> getCategoryList(){

        List<Category> categoryList=new ArrayList<>();
        categoryList.add(new Category("Accounting","accounting"));
        categoryList.add(new Category("ATM","atm"));
        categoryList.add(new Category("Bank","bank"));
        categoryList.add(new Category("Bus Station","bus_station"));
        categoryList.add(new Category("Cafe","cafe"));
        categoryList.add(new Category("Hospital","hospital"));
        categoryList.add(new Category("Laundry","laundry"));
        categoryList.add(new Category("Library","library"));
        categoryList.add(new Category("Museum","museum"));
        categoryList.add(new Category("Plumber","plumber"));
        categoryList.add(new Category("Pharmacy","pharmacy"));
        categoryList.add(new Category("Restaurant","restaurant"));
        categoryList.add(new Category("Post Office","post_office"));
        categoryList.add(new Category("Train Station","train_station"));
        return categoryList;
    }


}
