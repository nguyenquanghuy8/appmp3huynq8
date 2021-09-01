package com.example.appmp3.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmp3.model.entity.Song;
import com.example.appmp3.model.repository.SongRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongViewModel extends ViewModel {
    public MutableLiveData<List<Song>> songLiveData;
    private SongRepository songRepository;

   public SongViewModel() {
       songRepository = new SongRepository();
       songLiveData = new MutableLiveData<>();
   }

   public void getSong() {
       List<Song> arrayList = songRepository.fakeSongsData();
       songLiveData.postValue(arrayList);
   }
}
