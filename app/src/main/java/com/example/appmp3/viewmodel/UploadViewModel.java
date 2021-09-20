package com.example.appmp3.viewmodel;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.entity.Song;
import com.example.appmp3.model.repository.UploadRepository;
import com.example.appmp3.view.base.BaseViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class UploadViewModel extends BaseViewModel {
    private UploadRepository uploadRepository;
    public MutableLiveData<Boolean> songLiveData = new MutableLiveData<>();

    @Inject
    public UploadViewModel(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public void uploadSongInfo(Uri avatarUri, Uri mp3Uri, Song song) {
        compositeDisposable.add(
                uploadSongImage(avatarUri)
                        .map(url -> {
                            song.setAvatarSong(url);
                            return song;
                        })
                        .flatMap(songObserver -> uploadSongMp3(mp3Uri)
                                .map(url2 -> {
                                    song.setMp3Url(url2);
                                    return song;
                                }))
                        .flatMap(songs -> uploadRepository.storeSongs(song))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError -> notifyHideLoading())
                        .doOnSubscribe(onSubscribe -> notifyShowLoading())
                        .doOnComplete(this::notifyHideLoading)
                        .subscribe(response -> songLiveData.postValue(true), this::notifyError)
        );
    }

    private Observable<String> uploadSongImage(Uri uri) {
        return uploadRepository
                .storeImage(uri)
                .flatMap(ref -> uploadRepository.getDownloadUrl(ref));
    }

    private Observable<String> uploadSongMp3(Uri uri) {
        return uploadRepository
                .storeMp3(uri)
                .flatMap(ref -> uploadRepository.getDownloadUrl(ref));
    }
}
