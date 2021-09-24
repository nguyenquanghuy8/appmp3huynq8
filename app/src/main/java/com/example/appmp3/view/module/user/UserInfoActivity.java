package com.example.appmp3.view.module.user;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityUserInfoBinding;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.viewmodel.UserInfoViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserInfoActivity extends BaseActivity<ActivityUserInfoBinding, UserInfoViewModel> {

    @Override
    protected void addEvent() {

    }

    @Override
    protected void obsViewModel() {
        getViewModel().userLiveData.observe(this, user -> {
            getBinding().tvUserEmail.setText(user.getUserEmail());
            getBinding().tvUserName.setText(user.getUserName());
            getBinding().tvUserFirstName.setText(user.getUserFirstName());
            getBinding().tvUserLastName.setText(user.getUserLastName());
            getBinding().tvUserAddress.setText(user.getUserAddress());
            getBinding().tvUserPhoneNumber.setText(user.getUserPhoneNumber());
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    protected Class<UserInfoViewModel> getViewModelClass() {
        return UserInfoViewModel.class;
    }

    @Override
    protected void init() {
        getViewModel().loadUserInfo();
    }
}
