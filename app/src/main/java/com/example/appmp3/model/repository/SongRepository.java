package com.example.appmp3.model.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.appmp3.model.entity.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SongRepository {

    @Inject
    public SongRepository() {

    }

    public void fakeSongsData(GetSongCallback getSongCallback) {
        List<Song> listSong = new ArrayList<>();
//        listSong.add(new Song("Drake", null, "Laugh Now Cry Later", null, "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
//        listSong.add(new Song("Drake", null, "Nice For What", null, "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
//        listSong.add(new Song("Drake", null, "Toosie Slide", null, "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
//        listSong.add(new Song("Drake", null, "Hello", null, "https://hiphop-n-more.com/wp-content/uploads/2019/09/drake-1.jpg"));
//        listSong.add(new Song("Justin", null, "What Do You Mean?", null, "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
//        listSong.add(new Song("Justin", null, "Baby?", null, "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
//        listSong.add(new Song("Justin", null, "How are you", null, "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
//        listSong.add(new Song("Justin", null, "Sorry", null, "https://i.ytimg.com/vi/qdDVtFvJwUc/maxresdefault.jpg"));
//        listSong.add(new Song("Taylor", null, "Look What You Made Me Do", null, "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
//        listSong.add(new Song("Taylor", null, "The Best Day", null, "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
//        listSong.add(new Song("Taylor", null, "You Belong With Me", null, "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
//        listSong.add(new Song("Taylor", null, "Willow", null, "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
//        listSong.add(new Song("Taylor", null, "Jump Then Fall", null, "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));
//        listSong.add(new Song("Taylor", null, "I Heart", null, "https://media.pitchfork.com/photos/5d55ace3652fbc0009b08083/2:1/w_1200,h_600,c_limit/Taylor%20Swift.png"));

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
