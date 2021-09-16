package com.example.appmp3.view.module.login;

import android.content.Context;
import android.content.Intent;
import android.util.Patterns;

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

    public static void startActivity(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }

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
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
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
        String email = getBinding().edtLoginEmail.getText().toString().trim();
        String password = getBinding().edtLoginPassword.getText().toString().trim();
        if (checkValidate(email, password)) {
            getViewModel().login(email, password);
        }
    }

    private boolean checkValidate(String email, String password) {
        boolean isEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches();
        boolean isPassword = password.length() > 6;

        if (email.isEmpty()) {
            showToast(getString(R.string.check_email_empty));
            return false;
        }
        if (!isEmail) {
            showToast(getString(R.string.check_email_valid));
            return false;
        }
        if (password.isEmpty()) {
            showToast(getString(R.string.check_password_empty));
            return false;
        }
        if (!isPassword) {
            showToast(getString(R.string.check_password_length));
            return false;
        }
       return true;
    }
}
