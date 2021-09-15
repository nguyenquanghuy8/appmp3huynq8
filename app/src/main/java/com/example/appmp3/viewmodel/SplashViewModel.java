package com.example.appmp3.viewmodel;

import com.example.appmp3.model.repository.SplashRepository;
import com.example.appmp3.view.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SplashViewModel extends BaseViewModel {
    private SplashRepository splashRepository;

    @Inject
    public SplashViewModel(SplashRepository splashRepository) {
        this.splashRepository = splashRepository;

    }

    public Boolean getCurrentUser(boolean login) {
        if (login){
            splashRepository.getCurrentUser();
        }
        return login;
    }
}
