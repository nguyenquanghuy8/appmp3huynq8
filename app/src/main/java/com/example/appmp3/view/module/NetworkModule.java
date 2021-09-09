package com.example.appmp3.view.module;

import com.example.appmp3.model.repository.BannerRepository;
import com.example.appmp3.model.repository.CategoryRepository;
import com.example.appmp3.model.repository.SongRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public SongRepository provideSong() {
        return new SongRepository();
    }

    @Provides
    @Singleton
    public CategoryRepository provideCategory() {
        return new CategoryRepository();
    }

    @Provides
    @Singleton
    public BannerRepository provideBanner() {
        return new BannerRepository();
    }
}
