package com.example.appmp3.view.module.categories;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.repository.CategoryRepository;
import com.example.appmp3.view.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class CategoriesViewModel extends BaseViewModel {
    private CategoryRepository categoryRepository;
    public MutableLiveData<List<Category>> getCategoriesObs = new MutableLiveData<>();

    @Inject
    public CategoriesViewModel(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void getCategories() {
        compositeDisposable.add(
                categoryRepository.getCategories()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError -> notifyHideLoading())
                        .doOnSubscribe(onSubscribe -> notifyShowLoading())
                        .doOnComplete(this::notifyHideLoading)
                        .subscribe(response -> getCategoriesObs.postValue(response), this::notifyError)
        );
    }
}
