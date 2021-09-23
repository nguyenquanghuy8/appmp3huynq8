package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.entity.Song;
import com.example.appmp3.model.repository.SongRepository;
import com.example.appmp3.view.base.BaseViewModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class SongViewModel extends BaseViewModel {
    private SongRepository songRepository;

    public MutableLiveData<List<Song>> songLiveData = new MutableLiveData<>();
    public MutableLiveData<Song> songLiveData2 = new MutableLiveData<>();

    @Inject
    public SongViewModel(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void getSong() {
        loadingLiveData.postValue(true);

        songRepository.fakeSongsData(new SongRepository.GetSongCallback() {
            @Override
            public void onSuccess(List<Song> songs) {
                songLiveData.postValue(songs);
                loadingLiveData.postValue(false);
            }

            @Override
            public void onFail(String error) {
                errorLiveData.postValue(error);
                loadingLiveData.postValue(false);
            }
        });
    }

    public void getVideos() {
        compositeDisposable.add(
                songRepository
                        .getSongInfo()
                        .flatMapIterable(songs -> songs)
                        .filter(song -> song.isHaveVideo())
                        .toList()
                        .toObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError -> notifyHideLoading())
                        .doOnSubscribe(onSubscribe -> notifyShowLoading())
                        .doOnComplete(this::notifyHideLoading)
                        .subscribe(result -> songLiveData.postValue(result), this::notifyError)
        );
    }

    public void loadSongInfo() {

        compositeDisposable.add(
                songRepository
                        .getSongInfo()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError -> notifyHideLoading())
                        .doOnSubscribe(onSubscribe -> notifyShowLoading())
                        .doOnComplete(this::notifyHideLoading)
                        .subscribe(result -> songLiveData.postValue(result), this::notifyError)
        );
    }
}