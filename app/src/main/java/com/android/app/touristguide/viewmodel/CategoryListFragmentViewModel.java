package com.android.app.touristguide.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import com.android.app.touristguide.BR;
import com.android.app.touristguide.R;
import com.android.app.touristguide.model.Category;
import com.android.app.touristguide.ui.activity.MainActivity;
import com.android.app.touristguide.ui.activity.MapsActivity;
import com.android.app.touristguide.ui.adapter.RecyclerViewBindingAdapter;
import com.android.app.touristguide.ui.adapter.RecyclerViewConfiguration;


@SuppressWarnings("ALL")
public class CategoryListFragmentViewModel {

    public final RecyclerViewConfiguration recyclerViewConfiguration=new RecyclerViewConfiguration();
    private final Context context;

    public CategoryListFragmentViewModel(Context context) {
        this.context = context;
        setupRecyclerView();    }

    private void setupRecyclerView() {
        recyclerViewConfiguration.setItemAnimator(new DefaultItemAnimator());
        recyclerViewConfiguration.setLayoutManager(new GridLayoutManager(context,2));
        RecyclerViewBindingAdapter<Category> adapter=new RecyclerViewBindingAdapter<>
                (R.layout.category_list_layout, BR.category, Category.getCategoryList());
        adapter.setItemClickListener((position, category) -> {

            startMapActivity(category.getCategoryValue());

        });
        recyclerViewConfiguration.setAdapter(adapter);
    }

    private void startMapActivity(String type){
        Location location=((MainActivity)context).getLocation();

        if(null!=location) {
            Intent intent = new Intent(context, MapsActivity.class);
            intent.putExtra(MapsActivity.TYPE, type);
            intent.putExtra(MapsActivity.LOCATION, location);
            context.startActivity(intent);
        }else{
            ((MainActivity)context).showLocationError();
        }


    }
}
