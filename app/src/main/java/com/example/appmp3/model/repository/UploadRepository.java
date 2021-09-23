package com.example.appmp3.model.repository;

import android.net.Uri;

import com.example.appmp3.model.entity.Song;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class UploadRepository {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private StorageReference storageReference;
    private static final String COLLECTION_SONGS = "songs";
    private static final String COLLECTION_IMAGES = "images";
    private static final String COLLECTION_MP3 = "mp3";
    private static final String COLLECTION_VIDEOS = "videos";

    @Inject
    public UploadRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public String getCurrentUserUid() {
        return firebaseAuth.getCurrentUser().getUid();
    }

    public Observable<Boolean> storeSongs(Song song) {
        return Observable.create(emitter ->
                firebaseFirestore
                        .collection(COLLECTION_SONGS)
                        .document(getCurrentUserUid() + System.currentTimeMillis())
                        .set(song)
                        .addOnSuccessListener(unused -> {
                                    emitter.onNext(true);
                                    emitter.onComplete();
                                }
                        )
                        .addOnFailureListener(emitter::onError)
        );
    }

    public Observable<Song> getSongInfo() {
        return Observable.create(emitter ->
                firebaseFirestore
                        .collection(COLLECTION_SONGS)
                        .document(getCurrentUserUid())
                        .get()
                        .addOnSuccessListener(documentSnapshot -> {
                            Song song = documentSnapshot.toObject(Song.class);
                            emitter.onNext(song);
                            emitter.onComplete();
                        })
                        .addOnFailureListener(emitter::onError));
    }

    public Observable<StorageReference> storeImage(Uri uri) {
        return storageFile(COLLECTION_IMAGES, getCurrentUserUid() + "-images-" + System.currentTimeMillis(), uri);
    }

    public Observable<StorageReference> storeMp3(Uri uri) {
        return storageFile(COLLECTION_MP3, getCurrentUserUid() + "-mp3-" + System.currentTimeMillis(), uri);
    }

    public Observable<StorageReference> storeVideo(Uri uri) {
        return storageFile(COLLECTION_VIDEOS, getCurrentUserUid() + "-videos-" + System.currentTimeMillis(), uri);
    }

    private Observable<StorageReference> storageFile(String collection, String fileName, Uri uri) {
        StorageReference fileRef = storageReference.child(collection).child(fileName);
        storageReference.child(System.currentTimeMillis() + ".");
        return Observable.create(emitter ->
                fileRef
                        .putFile(uri)
                        .addOnSuccessListener(taskSnapshot -> {
                            emitter.onNext(fileRef);
                            emitter.onComplete();
                        })
                        .addOnFailureListener(emitter::onError)
        );
    }

    public Observable<String> getDownloadUrl(StorageReference storageReference) {
        return Observable.create(emitter ->
                storageReference
                        .getDownloadUrl()
                        .addOnSuccessListener(uri -> {
                            emitter.onNext(uri.toString());
                            emitter.onComplete();
                        })
                        .addOnFailureListener(emitter::onError));
    }
}
