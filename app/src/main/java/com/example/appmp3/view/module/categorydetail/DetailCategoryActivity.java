package com.example.appmp3.view.module.categorydetail;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityDetailCategoryBinding;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.view.module.explorer.adapter.SongAdapter;
import com.example.appmp3.viewmodel.SongViewModel;

import java.util.List;

public class DetailCategoryActivity extends BaseActivity<ActivityDetailCategoryBinding, SongViewModel> {
    private SongAdapter songAdapter = new SongAdapter();
    private Category category;

    public static final String EXTRA_CATEGORY = "extra_category";
    private Observer<List<Song>> songListUpdateObserver = songs -> songAdapter.updateAdapter(songs);

    @Override
    protected void addEvent() {

    }

    @Override
    protected void obsViewModel() {
        getViewModel().songLiveData.observe(this, songListUpdateObserver);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_category;
    }

    @Override
    protected Class<SongViewModel> getViewModelClass() {
        return SongViewModel.class;
    }

    @Override
    protected void init() {
        initRV();
        category = (Category) getIntent().getSerializableExtra(EXTRA_CATEGORY);
        getBinding().setCategory(category);
        getBinding().setDetailCategoryActivity(this);

        //dang ky lang nghe su kien khi data thay doi

        getViewModel().getSong();
    }

    public void onClickBtnBack(View view) {
        finish();
    }

    private void initRV() {
        getBinding().recyclerDetailCategory.setAdapter(songAdapter);
        getBinding().recyclerDetailCategory.setLayoutManager(new LinearLayoutManager(this));
    }
}
