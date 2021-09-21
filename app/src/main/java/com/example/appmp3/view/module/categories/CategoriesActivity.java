package com.example.appmp3.view.module.categories;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityCategoriesBinding;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.view.module.categorydetail.DetailCategoryActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CategoriesActivity extends BaseActivity<ActivityCategoriesBinding, CategoriesViewModel> implements CategoriesAdapter.ItemCategoryClickListener {
    private CategoriesAdapter categoriesAdapter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, CategoriesActivity.class));
    }

    @Override
    protected void init() {
        initRv();
        getViewModel().getCategories();
    }

    @Override
    protected void addEvent() {
        getBinding().ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void obsViewModel() {
        getViewModel().getCategoriesObs.observe(this, categories -> {
            categoriesAdapter.updateAdapter(categories);
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_categories;
    }

    @Override
    protected Class<CategoriesViewModel> getViewModelClass() {
        return CategoriesViewModel.class;
    }

    private void initRv() {
        categoriesAdapter = new CategoriesAdapter(this);
        getBinding().rvCategories.setAdapter(categoriesAdapter);
        getBinding().rvCategories.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onCategoryClicked(Category category) {
        DetailCategoryActivity.startActivity(this, category);
    }
}
