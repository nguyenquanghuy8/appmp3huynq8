package com.example.appmp3.network;

import com.example.appmp3.model.entity.Song;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("zGnnSoHcBueKQPpPCjB4GxzGc5P2-mp3-1631879659715?alt=media&token=d9067a01-2464-4cf3-83a3-552b77e6bd78")
    Call<Song> getInfoSong();
}
