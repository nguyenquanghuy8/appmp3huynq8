package com.example.appmp3.view.module.login;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityLoginBinding;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.view.module.home.MainActivity;
import com.example.appmp3.viewmodel.UserLoginViewModel;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserLoginViewModel> {

    @Override
    protected void addEvent() {
        getBinding().llRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        getBinding().btnLogin.setOnClickListener(v -> onClickLogin());
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
        return R.layout.activity_login;
    }

    @Override
    protected Class<UserLoginViewModel> getViewModelClass() {
        return UserLoginViewModel.class;
    }

    @Override
    protected void init() {

    }

    private void onClickLogin() {
        String edtRegisterEmail = getBinding().edtLoginEmail.getText().toString().trim();
        String edtRegisterPassword = getBinding().edtLoginPassword.getText().toString().trim();
        if (edtRegisterEmail.length() > 0 && edtRegisterPassword.length() > 0) {
            getViewModel().login(edtRegisterEmail, edtRegisterPassword);
        } else {
            Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
        }
    }
}
