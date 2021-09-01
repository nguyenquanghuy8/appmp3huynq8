package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmp3.model.entity.Banner;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.repository.BannerRepository;
import com.example.appmp3.model.repository.CategoryRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    public MutableLiveData<List<Category>> categoryLiveData;

    public MutableLiveData<List<Banner>> bannerLiveData;
    public MutableLiveData<String> errorLiveData;
    private CategoryRepository categoryRepository;
    private BannerRepository bannerRepository;

    public HomeViewModel() {
        categoryRepository = new CategoryRepository();
        categoryLiveData = new MutableLiveData<>();

        bannerRepository = new BannerRepository();
        bannerLiveData = new MutableLiveData<>();

        errorLiveData = new MutableLiveData<>();
    }

    public void getCategory() {
        List<Category> arrayList = categoryRepository.fakeCategoriesData();
        categoryLiveData.postValue(arrayList);

    }

    public void getBanner() {
        bannerRepository.fakeBannersData(new BannerRepository.GetBannersCallback() {
            @Override
            public void onSuccess(List<Banner> bannerList) {
                bannerLiveData.postValue(bannerList);
            }

            @Override
            public void onFail(String result) {
                errorLiveData.postValue(result);
            }
        });

    }
}
