package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.appmp3.model.entity.Song;
import com.example.appmp3.model.repository.SongRepository;
import com.example.appmp3.view.base.BaseViewModel;

import java.util.List;

public class SongViewModel extends BaseViewModel {
    private SongRepository songRepository;

    public MutableLiveData<List<Song>> songLiveData = new MutableLiveData<>();

    public SongViewModel() {
        songRepository = new SongRepository();
        songLiveData = new MutableLiveData<>();
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
}