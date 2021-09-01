package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmp3.model.entity.Banner;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.repository.BannerRepository;
import com.example.appmp3.model.repository.CategoryRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    public MutableLiveData<List<Category>> categoryLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Banner>> bannerLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private CategoryRepository categoryRepository = new CategoryRepository();
    private BannerRepository bannerRepository = new BannerRepository();

    public HomeViewModel() {
    }

    public void getCategory() {
        loadingLiveData.postValue(true);

        categoryRepository.fakeCategoriesData(new CategoryRepository.GetCategoryCallBack() {
            @Override
            public void onSuccess(List<Category> categories) {
                categoryLiveData.postValue(categories);
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
