package com.example.appmp3.view.module.categorydetail;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityDetailCategoryBinding;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.view.module.dialog.LoadingDialog;
import com.example.appmp3.view.module.explorer.adapter.SongAdapter;
import com.example.appmp3.viewmodel.SongViewModel;

import java.util.List;

public class DetailCategoryActivity extends AppCompatActivity {
    private SongAdapter songAdapter = new SongAdapter();
    private Category category;
    private SongViewModel songViewModel;
    private LoadingDialog loadingDialog;

    private ActivityDetailCategoryBinding activityDetailCategoryBinding;

    public static final String EXTRA_CATEGORY = "extra_category";
    private Observer<List<Song>> songListUpdateObserver = songs -> songAdapter.updateAdapter(songs);
    private Observer<String> errorObserver = error -> Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    private Observer<Boolean> loadingObs = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isShowLoading) {
            if (isShowLoading) {
                loadingDialog.show();
            } else {
                loadingDialog.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailCategoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_category);
        loadingDialog = new LoadingDialog(this);

        initRV();
        category = (Category) getIntent().getSerializableExtra(EXTRA_CATEGORY);
        activityDetailCategoryBinding.setCategory(category);
        activityDetailCategoryBinding.setDetailCategoryActivity(this);

        //khoi tao viewmodel
        songViewModel = new ViewModelProvider(this).get(SongViewModel.class);
        //dang ky lang nghe su kien khi data thay doi

        songViewModel.songLiveData.observe(this, songListUpdateObserver);
        songViewModel.errorLiveData.observe(this, errorObserver);
        songViewModel.loadingLiveData.observe(this, loadingObs);

        songViewModel.getSong();
    }

    public void onClickBtnBack(View view) {
        finish();
    }

    private void initRV() {
        activityDetailCategoryBinding.recyclerDetailCategory.setAdapter(songAdapter);
        activityDetailCategoryBinding.recyclerDetailCategory.setLayoutManager(new LinearLayoutManager(this));
    }
}
