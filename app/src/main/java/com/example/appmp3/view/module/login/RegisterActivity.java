package com.example.appmp3.view.module.login;

import android.content.Intent;
import android.util.Patterns;

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
        getBinding().btnRegister.setOnClickListener(v -> onRegisterClick());
    }

    @Override
    protected void obsViewModel() {
        getViewModel().userLiveData.observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (getViewModel().userRepository.getCurrentUser() != null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
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

    private void onRegisterClick() {
        String email = getBinding().edtRegisterEmail.getText().toString().trim();
        String password = getBinding().edtRegisterEmail.getText().toString().trim();
        if (checkValidate(email, password)) {
            getViewModel().register(email, password);
        }
    }

    private boolean checkValidate(String email, String password) {
        boolean isEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.isEmpty();
        boolean isPassword = password.length() > 6;

        if (!isEmail) {
            showToast(getApplicationContext(), getString(R.string.check_email));
        } else
        if (!isPassword) {
            showToast(getApplicationContext(), getString(R.string.check_password));
        }
        return isEmail && isPassword;
    }
}
