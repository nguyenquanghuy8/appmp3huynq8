package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.entity.Banner;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.repository.BannerRepository;
import com.example.appmp3.model.repository.CategoryRepository;
import com.example.appmp3.model.repository.VideoCategoryRepository;
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
    public MutableLiveData<List<Category>> videoCategoryLiveData = new MutableLiveData<>();

    private CategoryRepository categoryRepository;
    private BannerRepository bannerRepository;
    private VideoCategoryRepository videoCategoryRepository;

    @Inject
    public HomeViewModel(BannerRepository bannerRepository, CategoryRepository categoryRepository, VideoCategoryRepository videoCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.bannerRepository = bannerRepository;
        this.videoCategoryRepository = videoCategoryRepository;
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

    public void getCategoryVideo() {
        loadingLiveData.postValue(true);

        videoCategoryRepository.fakeCategoryData(new VideoCategoryRepository.GetVideoCategoryCallback() {
            @Override
            public void onSuccess(List<Category> videoList) {
                videoCategoryLiveData.postValue(videoList);
                loadingLiveData.postValue(false);
            }

            @Override
            public void onFail(String error) {
                errorLiveData.postValue(error);
                loadingLiveData.postValue(false);
            }
        });
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
