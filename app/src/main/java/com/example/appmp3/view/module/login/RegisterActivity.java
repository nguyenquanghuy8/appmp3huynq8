package com.example.appmp3.view.module.login;

import android.content.Intent;
import android.util.Patterns;
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
        getBinding().btnRegister.setOnClickListener(v -> onRegisterClick());
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

    private void onRegisterClick() {
        String edtRegisterEmail = getBinding().edtRegisterEmail.getText().toString().trim();
        String edtRegisterPassword = getBinding().edtRegisterEmail.getText().toString().trim();
        if (edtRegisterEmail.length() > 0 && edtRegisterPassword.length() > 0 && isEmailValid(edtRegisterEmail)) {
            getViewModel().register(edtRegisterEmail, edtRegisterPassword);
        }
        if (edtRegisterPassword.length() < 8){
            Toast.makeText(getApplicationContext(), "Password must be more than 8 characters", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Register Fail", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
