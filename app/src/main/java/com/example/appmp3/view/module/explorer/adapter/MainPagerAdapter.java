package com.example.appmp3.view.module.explorer.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appmp3.view.module.explorer.fragment.HomeFragment;
import com.example.appmp3.view.module.explorer.fragment.UserFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final int TAB_SIZE = 2;

    public MainPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new UserFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return TAB_SIZE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "HOME";
            case 1:
                return "USER";
            default:
                return "";
        }
    }
}