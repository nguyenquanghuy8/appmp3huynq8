package com.example.appmp3.view.module.splash;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.Observer;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivitySplashBinding;
import com.example.appmp3.view.base.TransparentStatusBar;
import com.example.appmp3.view.module.home.MainActivity;
import com.example.appmp3.view.module.login.LoginActivity;
import com.example.appmp3.viewmodel.SplashViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends TransparentStatusBar<ActivitySplashBinding, SplashViewModel> {

    @Override
    protected void addEvent() {
    }

    @Override
    protected void obsViewModel() {
        getViewModel().navigateScreenObs.observe(this, new Observer<SplashViewModel.NavigateScreen>() {
            @Override
            public void onChanged(SplashViewModel.NavigateScreen navigateScreen) {
                switch (navigateScreen) {
                    case OPEN_HOME:
                        MainActivity.startActivity(SplashActivity.this);
                        break;
                    case OPEN_LOGIN:
                        LoginActivity.startActivity(SplashActivity.this);
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected Class<SplashViewModel> getViewModelClass() {
        return SplashViewModel.class;
    }

    @Override
    protected void init() {
        delaySplashActivity();
    }

    private void delaySplashActivity() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            getViewModel().checkLogin();
        }, 1000);
    }
}
