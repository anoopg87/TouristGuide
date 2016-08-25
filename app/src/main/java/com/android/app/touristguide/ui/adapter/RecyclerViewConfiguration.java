package com.android.app.touristguide.ui.adapter;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.android.app.touristguide.BR;


@SuppressWarnings("ALL")
public class RecyclerViewConfiguration extends BaseObservable {

    /*
    Generic class for implementing recyclerView it set all the parameters needed for displaying a recyclerView
     */

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.Adapter adapter;

    // returing the LayoutManager
    @Bindable
    private RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }
    // returning the ItemAnimator
    @Bindable
    private RecyclerView.ItemAnimator getItemAnimator() {
        return itemAnimator;
    }
    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
        notifyPropertyChanged(BR.itemAnimator);
    }
    // returning the Adapter
    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }
    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }


    /*
    Binding adapter for the recyclerView using this we can set the recyclerView adapter from the layout itself
     */
    @BindingAdapter("app:bindAdapter")
    public static void configureRecyclerView(RecyclerView recyclerView, RecyclerViewConfiguration recyclerViewConfiguration){
        recyclerView.setLayoutManager(recyclerViewConfiguration.getLayoutManager());
        recyclerView.setItemAnimator(recyclerViewConfiguration.getItemAnimator());
        recyclerView.setAdapter(recyclerViewConfiguration.getAdapter());
    }
}



