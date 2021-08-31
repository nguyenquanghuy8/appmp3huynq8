package com.example.appmp3.module.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
import com.example.appmp3.model.Category;
import com.example.appmp3.model.Song;
import com.example.appmp3.module.explorer.adapter.SongAdapter;

import java.util.Objects;

public class DetailCategoryActivity extends AppCompatActivity {

    private Category category;
    private ImageView imgBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);

//        category = (Category) getIntent().getSerializableExtra("detail1");

        imgBtnBack = findViewById(R.id.imgBtnBack);

        imgBtnBack.setOnClickListener(v -> {
            finish();
        });

        RecyclerView recyclerViewDetailCategory = findViewById(R.id.recyclerDetailCategory);
        SongAdapter songAdapter = new SongAdapter(Song.createNewListSong());
        recyclerViewDetailCategory.setAdapter(songAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDetailCategory.setLayoutManager(layoutManager);
        recyclerViewDetailCategory.setHasFixedSize(true);
    }
}
