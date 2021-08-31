package com.example.appmp3.model;

import com.example.appmp3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemBanner {

    private int itemBanner;

    public int getItemBanner() {
        return itemBanner;
    }

    public void setItemBanner(int itemBanner) {
        this.itemBanner = itemBanner;
    }

    public static List<ItemBanner> createNewBanner() {
        List<ItemBanner> itemBannerList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ItemBanner itemBanner = new ItemBanner();
            List<Integer> listAvatar = new ArrayList<>();
            Random random = new Random();
            int position = random.nextInt(3);
            listAvatar.add(R.drawable.img_banner);
            listAvatar.add(R.drawable.img_banner2);
            listAvatar.add(R.drawable.img_banner3);
            itemBanner.setItemBanner(listAvatar.get(position));
            itemBannerList.add(itemBanner);
        }
        return itemBannerList;
    }
}
