package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.entity.Banner;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.repository.BannerRepository;
import com.example.appmp3.model.repository.CategoryRepository;
import com.example.appmp3.view.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {
    public MutableLiveData<List<Category>> categoryLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Banner>> bannerLiveData = new MutableLiveData<>();

    private CategoryRepository categoryRepository;
    private BannerRepository bannerRepository;

    @Inject
    public HomeViewModel(BannerRepository bannerRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.bannerRepository = bannerRepository;
    }

    public void getCategory() {
        loadingLiveData.postValue(true);

        compositeDisposable.add(
                categoryRepository.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError -> notifyHideLoading())
                        .doOnSubscribe(onSubscribe -> notifyShowLoading())
                        .doOnComplete(this::notifyHideLoading)
                        .subscribe(response -> categoryLiveData.postValue(response), this::notifyError)
        );
    }

    public void getBanner() {
        loadingLiveData.postValue(true);

        bannerRepository.fakeBannersData(new BannerRepository.GetBannersCallback() {
            @Override
            public void onSuccess(List<Banner> bannerList) {
                bannerLiveData.postValue(bannerList);
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
