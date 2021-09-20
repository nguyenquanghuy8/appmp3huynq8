package com.example.appmp3.view.module.login;

import android.util.Patterns;

import androidx.lifecycle.Observer;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityRegisterBinding;
import com.example.appmp3.model.entity.User;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.view.module.home.MainActivity;
import com.example.appmp3.viewmodel.UserRegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, UserRegisterViewModel> {

    @Override
    protected void addEvent() {
        getBinding().btnRegister.setOnClickListener(v -> onRegisterClick());
    }

    @Override
    protected void obsViewModel() {
        getViewModel().registerSuccessObs.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isRegisterSuccess) {
                MainActivity.startActivity(RegisterActivity.this);
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
        String password = getBinding().edtRegisterPassword.getText().toString().trim();
        String username = getBinding().edtRegisterUserName.getText().toString().trim();
        String firstName = getBinding().edtRegisterFirstName.getText().toString().trim();
        String lastName = getBinding().edtRegisterLastName.getText().toString().trim();
        String address = getBinding().edtRegisterAddress.getText().toString().trim();
        String phoneNumber = getBinding().edtRegisterPhoneNumber.getText().toString().trim();

        if (checkValidate(email, password, username, firstName, lastName, address, phoneNumber)) {
            User user = new User(email, username, firstName, lastName, address, phoneNumber);
            getViewModel().register(user, password);
        }
    }

    private boolean checkValidate(String email, String password, String username, String firstName, String lastName, String address, String phoneNumber) {
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
        if (email.isEmpty()) {
            showToast(getString(R.string.check_email_empty));
            return false;
        }
        if (username.isEmpty()) {
            showToast(getString(R.string.check_username_length));
            return false;
        }
        if (firstName.isEmpty()) {
            showToast(getString(R.string.check_firstname_length));
            return false;
        }
        if (lastName.isEmpty()) {
            showToast(getString(R.string.check_lastname_length));
            return false;
        }
        if (address.isEmpty()) {
            showToast(getString(R.string.check_address_length));
            return false;
        }
        if (phoneNumber.isEmpty()) {
            showToast(getString(R.string.check_phonenumber_length));
            return false;
        }
        return true;
    }
}
