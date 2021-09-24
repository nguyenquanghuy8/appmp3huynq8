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
public class UserInfoViewModel extends BaseViewModel {
    private UserRepository userRepository;
    public MutableLiveData<User> userLiveData = new MutableLiveData<>();

    @Inject
    public UserInfoViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void loadUserInfo() {

        compositeDisposable.add(
                userRepository
                        .getUserInfo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError -> notifyHideLoading())
                        .doOnSubscribe(onSubscribe -> notifyShowLoading())
                        .doOnComplete(this::notifyHideLoading)
                        .subscribe(result -> userLiveData.postValue(result), this::notifyError)
        );
    }
}
