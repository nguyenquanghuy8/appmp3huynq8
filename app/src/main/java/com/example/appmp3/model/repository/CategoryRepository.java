package com.example.appmp3.model.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.appmp3.model.entity.Category;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryRepository {

    @Inject
    public CategoryRepository() {

    }

    public void fakeCategoriesData(GetCategoryCallBack getCategoryCallBack) {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Id1", "Top 100 Nhạc\n" + "Vpop Hay Nhất", "https://avatar-nct.nixcdn.com/playlist/2019/11/15/1/a/e/b/1573810360774_500.jpg"));
        categoryList.add(new Category("Id2", "#ZINGCHART", "https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/9/7/c/9/97c960ac271e94fa47c87a12aa7d3be5.jpg"));


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                getCategoryCallBack.onSuccess(categoryList);
            }
        }, 1500);
    }

    public interface GetCategoryCallBack {
        void onSuccess(List<Category> categories);

        void onFail(String error);
    }
}
