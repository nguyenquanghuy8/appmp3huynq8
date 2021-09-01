package com.example.appmp3.module.explorer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
import com.example.appmp3.databinding.HomeFragmentBinding;
import com.example.appmp3.model.Banner;
import com.example.appmp3.model.Category;
import com.example.appmp3.model.Selection;
import com.example.appmp3.module.categorydetail.DetailCategoryActivity;
import com.example.appmp3.module.explorer.adapter.CategoryAdapter;
import com.example.appmp3.module.explorer.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomeFragment extends Fragment implements CategoryAdapter.CategoryClickListener {
    private final List<Selection> selectionList = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private HomeFragmentBinding homeFragmentBinding;

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

    private List<Category> fakeCategoriesData() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Id1", "Top 100 Nhạc\n" + "Vpop Hay Nhất", "https://avatar-nct.nixcdn.com/playlist/2019/11/15/1/a/e/b/1573810360774_500.jpg"));
        categoryList.add(new Category("Id2", "#ZINGCHART", "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/9/7/c/9/97c960ac271e94fa47c87a12aa7d3be5.jpg"));
        return categoryList;
    }

    public List<Banner> fakeBanners() {
        List<Banner> itemBannerList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            List<Integer> listAvatar = new ArrayList<>();
            Random random = new Random();
            int position = random.nextInt(3);

            listAvatar.add(R.drawable.img_banner);
            listAvatar.add(R.drawable.img_banner2);
            listAvatar.add(R.drawable.img_banner3);

            Banner itemBanner = new Banner(listAvatar.get(position));
            itemBannerList.add(itemBanner);
        }
        return itemBannerList;
    }

    private void initRV() {
        homeAdapter = new HomeAdapter(fakeCategoriesData(), fakeBanners(), selectionList, this);
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
