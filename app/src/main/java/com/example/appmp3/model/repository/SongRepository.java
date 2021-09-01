package com.example.appmp3.model.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.appmp3.model.entity.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongRepository {

    public void fakeSongsData(GetSongCallback getSongCallback) {
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

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                getSongCallback.onSuccess(listSong);
            }
        }, 1500);
    }

    public interface GetSongCallback {
        void onSuccess(List<Song> songs);

        void onFail(String error);
    }
}
