package com.example.appmp3.view.module.explorer.fragment;

import android.content.Intent;

import com.example.appmp3.R;
import com.example.appmp3.databinding.UserFragmentBinding;
import com.example.appmp3.view.base.BaseFragment;
import com.example.appmp3.view.module.login.LoginActivity;
import com.example.appmp3.view.module.upload.UploadActivity;
import com.example.appmp3.view.module.user.UserInfoActivity;
import com.example.appmp3.viewmodel.UserViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserFragment extends BaseFragment<UserFragmentBinding, UserViewModel> {

    @Override
    protected void addEvent() {
        getBinding().btnUserInfo.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            startActivity(intent);
        });

        getBinding().btnSignOut.setOnClickListener(v -> {
            getViewModel().signOutUser();
            LoginActivity.startActivity(getContext());
        });

        getBinding().btnUploadSong.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), UploadActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void obsViewModel() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_fragment;
    }

    @Override
    protected Class<UserViewModel> getViewModelClass() {
        return UserViewModel.class;
    }

    @Override
    protected void init() {
    }
}
