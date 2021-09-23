package com.example.appmp3.view.module.video;

import android.content.Context;
import android.content.Intent;

import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.view.module.categorydetail.DetailCategoryActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailVideoCategoryActivity extends DetailCategoryActivity {
    public static void startActivity(Context context, Category category) {
        Intent intent = new Intent(context, DetailVideoCategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY, category);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        super.init();
        getViewModel().getVideos();
    }

    @Override
    public void onItemSongClick(Song song) {
        VideoPlayerActivity.startActivity(this, song);
    }
}