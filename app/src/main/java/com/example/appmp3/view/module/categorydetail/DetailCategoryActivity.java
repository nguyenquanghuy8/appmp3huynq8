package com.example.appmp3.view.module.categorydetail;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityDetailCategoryBinding;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.view.module.explorer.adapter.SongAdapter;
import com.example.appmp3.viewmodel.SongViewModel;

import java.util.List;

public class DetailCategoryActivity extends AppCompatActivity {
    private SongAdapter songAdapter = new SongAdapter();
    private Category category;
    private SongViewModel songViewModel;

    private ActivityDetailCategoryBinding activityDetailCategoryBinding;

    public static final String EXTRA_CATEGORY = "extra_category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_category);

        initRV();
        category = (Category) getIntent().getSerializableExtra(EXTRA_CATEGORY);
        activityDetailCategoryBinding.setCategory(category);
        activityDetailCategoryBinding.setDetailCategoryActivity(this);

        //khoi tao viewmodel
        songViewModel = new ViewModelProvider(this).get(SongViewModel.class);
        //dang ky lang nghe su kien khi data thay doi
        songViewModel.songLiveData.observe(this, songListUpdateObserver);
        songViewModel.getSong();
    }

    Observer<List<Song>> songListUpdateObserver = songs -> songAdapter.updateAdapter(songs);

    public void onClickBtnBack(View view) {
        finish();
    }

    private void initRV() {
        activityDetailCategoryBinding.recyclerDetailCategory.setAdapter(songAdapter);
        activityDetailCategoryBinding.recyclerDetailCategory.setLayoutManager(new LinearLayoutManager(this));
    }
}
