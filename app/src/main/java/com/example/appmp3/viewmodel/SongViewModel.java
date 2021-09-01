package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmp3.model.entity.Song;
import com.example.appmp3.model.repository.SongRepository;

import java.util.List;

public class SongViewModel extends ViewModel {
    private SongRepository songRepository;

    public MutableLiveData<List<Song>> songLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

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
