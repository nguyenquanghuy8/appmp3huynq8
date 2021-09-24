package com.example.appmp3.model.repository;

import com.example.appmp3.model.entity.Category;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class VideoCategoryRepository {
    List<Category> categoryVideoList = new ArrayList<>();

    @Inject
    public VideoCategoryRepository() {

    }

    public Observable<List<Category>> fakeCategoryData() {
        categoryVideoList.add(new Category("", "Top 100 MV", "https://yt3.ggpht.com/wV9dgN_3SN89DkNHRQRZkJHCKXqNeOXRpoy-YQhA2ICMFsIAzT5snzfXad5VNG2HBvEaRxa41Q=s576"));
        return Observable.just(categoryVideoList);
    }
}
