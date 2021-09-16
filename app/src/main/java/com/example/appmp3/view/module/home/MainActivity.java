package com.example.appmp3.view.module.home;

import android.content.Context;
import android.content.Intent;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityMainBinding;
import com.example.appmp3.view.base.TransparentStatusBar;
import com.example.appmp3.view.module.explorer.adapter.MainPagerAdapter;
import com.example.appmp3.viewmodel.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends TransparentStatusBar<ActivityMainBinding, MainViewModel> {
    private MainPagerAdapter mainViewPagerAdapter;

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void addEvent() {

    }

    @Override
    protected void obsViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected Class<MainViewModel> getViewModelClass() {
        return MainViewModel.class;
    }

    @Override
    protected void init() {
        super.init();
        initVP();
    }

    private void initVP() {
        mainViewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), 1);
        getBinding().myViewPager.setAdapter(mainViewPagerAdapter);
        getBinding().myTabLayout.setupWithViewPager(getBinding().myViewPager);
        getBinding().myTabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_account);
    }
}