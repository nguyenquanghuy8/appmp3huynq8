package com.example.appmp3.model.repository;

import com.example.appmp3.R;
import com.example.appmp3.model.entity.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BannerRepository {

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
        getBannersCallback.onSuccess(itemBannerList);
    }

    public interface GetBannersCallback {
        void onSuccess(List<Banner> bannerList);
        void onFail(String result);
    }
}
