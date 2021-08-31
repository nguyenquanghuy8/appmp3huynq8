package com.example.appmp3.module.categorydetail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
import com.example.appmp3.model.Category;
import com.example.appmp3.model.Song;
import com.example.appmp3.module.explorer.adapter.SongAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailCategoryActivity extends AppCompatActivity {
    private SongAdapter songAdapter = new SongAdapter();
    private Category category;
    private ImageView imgBtnBack;
    private TextView tvTitle;

    public static final String EXTRA_CATEGORY = "extra_category";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_category);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        tvTitle = findViewById(R.id.tvTitle);

        initRV();
        category = (Category) getIntent().getSerializableExtra(EXTRA_CATEGORY);
        tvTitle.setText(category.getCategoryName());

        imgBtnBack.setOnClickListener(v -> {
            finish();
        });

        songAdapter.updateAdapter(fakeSongs());
    }

    private List<Song> fakeSongs() {
        List<Song> listSong = new ArrayList<>();
        listSong.add(new Song("Drake", "Laugh Now Cry Later", "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
        listSong.add(new Song("Drake", "Nice For What", "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
        listSong.add(new Song("Drake", "Toosie Slide", "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
        listSong.add(new Song("Drake", "Hello", "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
        listSong.add(new Song("Justin", "What Do You Mean?", "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
        listSong.add(new Song("Justin", "Baby?", "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
        listSong.add(new Song("Justin", "How are you", "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
        listSong.add(new Song("Justin", "Sorry", "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
        listSong.add(new Song("Taylor", "Look What You Made Me Do", "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
        listSong.add(new Song("Taylor", "The Best Day", "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
        listSong.add(new Song("Taylor", "You Belong With Me", "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
        listSong.add(new Song("Taylor", "Willow", "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
        listSong.add(new Song("Taylor", "Jump Then Fall", "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
        listSong.add(new Song("Taylor", "I Heart", "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));

        Collections.shuffle(listSong);

        return listSong;
    }

    private void initRV() {
        RecyclerView recyclerViewDetailCategory = findViewById(R.id.recyclerDetailCategory);
        recyclerViewDetailCategory.setAdapter(songAdapter);
        recyclerViewDetailCategory.setLayoutManager(new LinearLayoutManager(this));
    }
}
