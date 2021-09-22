package com.example.appmp3.model.repository;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;

import com.example.appmp3.model.entity.Song;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class SongRepository {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private static final String COLLECTION_SONGS = "songs";
    private List<Song> postList;

    @Inject
    public SongRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
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

    public Observable<List<Song>> getSongInfo() {

        return Observable.create(emitter ->
                firebaseFirestore
                        .collection(COLLECTION_SONGS)
//                        .document(getCurrentUserUid())
                        .get()
                        .addOnSuccessListener(documentSnapshot -> {
                            if (documentSnapshot != null && !documentSnapshot.getDocuments().isEmpty()) {
                                List<Song> songs = new ArrayList<>();
                                List<DocumentSnapshot> documentSnapshots = documentSnapshot.getDocuments();
                                for (DocumentSnapshot value: documentSnapshots) {
                                    songs.add(value.toObject(Song.class));
                                }
                                emitter.onNext(songs);
                            }
                        })
                        .addOnFailureListener(emitter::onError));
    }

    public interface GetSongCallback {
        void onSuccess(List<Song> songs);

        void onFail(String error);
    }
}
