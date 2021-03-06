package com.example.appmp3.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.entity.User;
import com.example.appmp3.model.repository.UserRepository;
import com.example.appmp3.view.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class UserRegisterViewModel extends BaseViewModel {
    private UserRepository userRepository;
    public MutableLiveData<Boolean> registerSuccessObs = new MutableLiveData<>();

    @Inject
    public UserRegisterViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user, String password) {
        compositeDisposable.add(
                userRepository
                        .registerUser(user.getUserEmail(), password)
                        .flatMap(result -> userRepository.storeUser(user))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError -> notifyHideLoading())
                        .doOnSubscribe(onSubscribe -> notifyShowLoading())
                        .doOnComplete(this::notifyHideLoading)
                        .subscribe(response -> registerSuccessObs.postValue(true), this::notifyError)
        );
    }
}