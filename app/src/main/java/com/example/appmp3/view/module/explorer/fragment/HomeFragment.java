package com.example.appmp3.view.module.explorer.fragment;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmp3.R;
import com.example.appmp3.databinding.HomeFragmentBinding;
import com.example.appmp3.model.entity.Banner;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.view.base.BaseFragment;
import com.example.appmp3.view.module.categories.CategoriesActivity;
import com.example.appmp3.view.module.categorydetail.DetailCategoryActivity;
import com.example.appmp3.view.module.explorer.adapter.CategoryAdapter;
import com.example.appmp3.view.module.explorer.adapter.CategoryVideoAdapter;
import com.example.appmp3.view.module.explorer.adapter.HomeAdapter;
import com.example.appmp3.view.module.video.DetailVideoCategoryActivity;
import com.example.appmp3.viewmodel.HomeViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<HomeFragmentBinding, HomeViewModel>
        implements CategoryAdapter.CategoryClickListener, HomeAdapter.HomeAdapterClickListener, CategoryVideoAdapter.CategoryVideoClickListener {
    private HomeAdapter homeAdapter;
    private Observer<List<Category>> categoryListUpdateObserver = categories -> homeAdapter.updateAdapterCategory(categories);
    private Observer<List<Banner>> bannerListUpdateObserver = banners -> homeAdapter.updateAdapterBanner(banners);
    private Observer<List<Category>> videoListUpdateObserver = videos -> homeAdapter.updateAdapterVideo(videos);

    @Override
    protected void addEvent() {

    }

    @Override
    protected void obsViewModel() {
        getViewModel().categoryLiveData.observe(this, categoryListUpdateObserver);
        getViewModel().bannerLiveData.observe(this, bannerListUpdateObserver);
        getViewModel().videoCategoryLiveData.observe(this, videoListUpdateObserver);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    protected Class<HomeViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Override
    protected void init() {
        initRV();

        getViewModel().getCategory();
        getViewModel().getBanner();
        getViewModel().getCategoryVideo();
    }

    private void initRV() {
        homeAdapter = new HomeAdapter(this, this, this);
        getBinding().rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().rvHome.setAdapter(homeAdapter);
    }

    @Override
    public void onCategoryClicked(Category category) {
        DetailCategoryActivity.startActivity(getContext(), category);
    }

    @Override
    public void onAllCategoryClicked() {
        CategoriesActivity.startActivity(requireContext());
    }

    @Override
    public void onCategoryVideoClicked(Category category) {
        DetailVideoCategoryActivity.startActivity(getContext(), category);
    }
}
