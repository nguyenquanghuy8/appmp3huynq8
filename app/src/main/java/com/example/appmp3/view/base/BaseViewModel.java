package com.example.appmp3.view.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    protected void notifyShowLoading(){
        loadingLiveData.postValue(true);
    }

    protected void notifyHideLoading(){
        loadingLiveData.postValue(false);
    }

    protected void notifyError(Throwable e){
        errorLiveData.postValue(e.getMessage());
    }
}
