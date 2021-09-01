package com.example.appmp3.module.home;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityMainBinding;
import com.example.appmp3.module.explorer.adapter.MainPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private MainPagerAdapter mainViewPagerAdapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);
        setContentView(activityMainBinding.getRoot());

        mainViewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), 1);

        initVP();

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initVP() {

        activityMainBinding.myViewPager.setAdapter(mainViewPagerAdapter);
        activityMainBinding.myTabLayout.setupWithViewPager(activityMainBinding.myViewPager);
        activityMainBinding.myTabLayout.getTabAt(0).setIcon(R.drawable.ic_tab_account);
    }

    public void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}