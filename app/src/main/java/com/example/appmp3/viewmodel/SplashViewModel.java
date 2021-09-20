package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.repository.UserRepository;
import com.example.appmp3.view.base.BaseViewModel;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SplashViewModel extends BaseViewModel {
    private UserRepository userRepository;
    public MutableLiveData<NavigateScreen> navigateScreenObs = new MutableLiveData<>();

    public enum NavigateScreen {
        OPEN_HOME, OPEN_LOGIN
    }

    @Inject
    public SplashViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void checkLogin() {
        FirebaseUser user = userRepository.getCurrentUser();
        if (user == null) {
            navigateScreenObs.postValue(NavigateScreen.OPEN_LOGIN);
        } else {
            navigateScreenObs.postValue(NavigateScreen.OPEN_HOME);
        }
    }
}
