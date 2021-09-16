package com.example.appmp3.view.module.explorer.fragment;

import android.content.Intent;

import androidx.lifecycle.Observer;

import com.example.appmp3.R;
import com.example.appmp3.databinding.UserFragmentBinding;
import com.example.appmp3.model.entity.User;
import com.example.appmp3.view.base.BaseFragment;
import com.example.appmp3.view.module.login.LoginActivity;
import com.example.appmp3.viewmodel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UserFragment extends BaseFragment<UserFragmentBinding, UserViewModel> {

    @Override
    protected void addEvent() {
        getBinding().btnSignOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });
    }

    @Override
    protected void obsViewModel() {
        getViewModel().userLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User users) {
                getBinding().tvUserEmail.setText(users.getUserEmail());
                getBinding().tvUserName.setText(users.getUserName());
                getBinding().tvUserFirstName.setText(users.getUserFirstName());
                getBinding().tvUserLastName.setText(users.getUserLastName());
                getBinding().tvUserAddress.setText(users.getUserAddress());
                getBinding().tvUserPhoneNumber.setText(users.getUserPhoneNumber());
            }
        });
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
        getViewModel().loadUserInfo();
    }
}
