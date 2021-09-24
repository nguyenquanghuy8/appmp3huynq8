package com.example.appmp3.viewmodel;

import com.example.appmp3.view.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PlayerViewModel extends BaseViewModel {

    @Inject
    public PlayerViewModel() {

    }
}
