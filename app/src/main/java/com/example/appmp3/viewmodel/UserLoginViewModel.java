package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.repository.UserRepository;
import com.example.appmp3.view.base.BaseViewModel;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserLoginViewModel extends BaseViewModel {
    public UserRepository userRepository;
    public MutableLiveData <FirebaseUser> userLiveData;

    @Inject
    public UserLoginViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
        userLiveData = new MutableLiveData<>();
    }

    public void login(String email, String password) {
        loadingLiveData.postValue(true);
        userRepository.getCurrentUser();

        userRepository.loginUser(email, password, new UserRepository.LoginCallback() {
            @Override
            public void onSuccess() {
                userLiveData.postValue(userRepository.getCurrentUser());
                loadingLiveData.postValue(false);
            }

            @Override
            public void onFail(String error) {
                errorLiveData.postValue(error);
                loadingLiveData.postValue(false);
            }
        });
    }
}
