package com.example.appmp3.view.module.login;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityRegisterBinding;

import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.view.module.home.MainActivity;
import com.example.appmp3.viewmodel.UserRegisterViewModel;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, UserRegisterViewModel> {

    @Override
    protected void addEvent() {
        getBinding().btnRegister.setOnClickListener(v -> onClickRegister());
    }

    @Override
    protected void obsViewModel() {
        getViewModel().userLiveData.observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected Class<UserRegisterViewModel> getViewModelClass() {
        return UserRegisterViewModel.class;
    }

    @Override
    protected void init() {

    }

    private void onClickRegister() {
        String edtRegisterEmail = getBinding().edtRegisterEmail.getText().toString().trim();
        String edtRegisterPassword = getBinding().edtRegisterEmail.getText().toString().trim();
        if (edtRegisterEmail.length() > 0 && edtRegisterPassword.length() > 0) {
            getViewModel().register(edtRegisterEmail, edtRegisterPassword);
        } else {
            Toast.makeText(getApplicationContext(), "Register Fail", Toast.LENGTH_SHORT).show();
        }
    }
}
