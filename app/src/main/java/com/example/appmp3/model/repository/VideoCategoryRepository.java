package com.example.appmp3.model.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.appmp3.model.entity.Category;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class VideoCategoryRepository {

    @Inject
    public VideoCategoryRepository() {

    }

    public void fakeCategoryData(GetVideoCategoryCallback getVideoCategoryCallback) {
        List<Category> categoryVideoList = new ArrayList<>();
        categoryVideoList.add(new Category("", "Top 100 MV", "https://yt3.ggpht.com/wV9dgN_3SN89DkNHRQRZkJHCKXqNeOXRpoy-YQhA2ICMFsIAzT5snzfXad5VNG2HBvEaRxa41Q=s576"));

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                getVideoCategoryCallback.onSuccess(categoryVideoList);
            }
        }, 1500);
    }

    public interface GetVideoCategoryCallback {
        void onSuccess(List<Category> videoList);
        void onFail(String error);
    }
}
