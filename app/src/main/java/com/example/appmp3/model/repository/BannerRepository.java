package com.example.appmp3.model.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.appmp3.R;
import com.example.appmp3.model.entity.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BannerRepository {

    @Inject
    public BannerRepository() {

    }

    public void fakeBannersData(GetBannersCallback getBannersCallback) {
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

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                getBannersCallback.onSuccess(itemBannerList);
            }
        }, 1500);
    }

    public interface GetBannersCallback {
        void onSuccess(List<Banner> bannerList);

        void onFail(String error);
    }
}
