package com.android.app.touristguide.ui.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


@SuppressWarnings("WeakerAccess")
public class BindingAdapters {
    // BindingAdapter used for load image from the given url
    @SuppressWarnings("unused")
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .resize(400,400)
                .onlyScaleDown()
        .into(view);
    }

}
