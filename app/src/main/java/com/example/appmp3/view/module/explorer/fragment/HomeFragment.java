package com.example.appmp3.view.module.explorer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmp3.databinding.HomeFragmentBinding;
import com.example.appmp3.model.entity.Banner;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.view.module.categorydetail.DetailCategoryActivity;
import com.example.appmp3.view.module.dialog.LoadingDialog;
import com.example.appmp3.view.module.explorer.adapter.CategoryAdapter;
import com.example.appmp3.view.module.explorer.adapter.HomeAdapter;
import com.example.appmp3.viewmodel.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment implements CategoryAdapter.CategoryClickListener {
    private HomeAdapter homeAdapter;
    private HomeFragmentBinding homeFragmentBinding;
    private HomeViewModel homeViewModel;
    private LoadingDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(getContext());
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.categoryLiveData.observe(this, categoryListUpdateObserver);

        homeViewModel.bannerLiveData.observe(this, bannerListUpdateObserver);
        homeViewModel.errorLiveData.observe(this, errorObserver);
        homeViewModel.loadingLiveData.observe(this, loadingObs);
        homeViewModel.getCategory();
        homeViewModel.getBanner();
    }

    Observer<List<Category>> categoryListUpdateObserver = categories -> homeAdapter.updateAdapterCategory(categories);
    Observer<List<Banner>> bannerListUpdateObserver = banners -> homeAdapter.updateAdapterBanner(banners);
    Observer<String> errorObserver = error -> Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
    Observer<Boolean> loadingObs = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isShowLoading) {
            if (isShowLoading) {
                loadingDialog.show();
            } else {
                loadingDialog.dismiss();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        HomeFragmentBinding item = HomeFragmentBinding.inflate(inflater);
        homeFragmentBinding = item;
        return item.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRV();
    }

    private void initRV() {
        homeAdapter = new HomeAdapter(this);
        homeFragmentBinding.rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        homeFragmentBinding.rvHome.setAdapter(homeAdapter);
    }

    @Override
    public void onCategoryClicked(Category category) {
        Intent intent = new Intent(getContext(), DetailCategoryActivity.class);
        intent.putExtra(DetailCategoryActivity.EXTRA_CATEGORY, category);
        startActivity(intent);
    }
}
