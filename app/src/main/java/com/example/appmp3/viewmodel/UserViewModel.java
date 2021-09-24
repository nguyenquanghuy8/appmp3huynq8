package com.example.appmp3.viewmodel;

import com.example.appmp3.model.repository.UserRepository;
import com.example.appmp3.view.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UserViewModel extends BaseViewModel {
    private UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signOutUser() {
        userRepository.signOut();
    }
}
