package com.android.app.touristguide.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.app.touristguide.R;
import com.android.app.touristguide.databinding.CategoryListFragmentLayoutBinding;
import com.android.app.touristguide.viewmodel.CategoryListFragmentViewModel;

public class CategoryListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CategoryListFragmentLayoutBinding binding= DataBindingUtil.inflate(inflater, R.layout.category_list_fragment_layout,container,false);
        binding.setHandler(new CategoryListFragmentViewModel(getActivity()));
        return binding.getRoot();
    }
}
