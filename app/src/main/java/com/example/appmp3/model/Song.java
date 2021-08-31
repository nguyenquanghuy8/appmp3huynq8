package com.example.appmp3.model;

import com.example.appmp3.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Song {
    private String tvSongName;
    private String tvArtistName;
    private int imgAvatarSong;

    public Song(String tvSongName, String tvArtistName, int imgAvatarSong) {
        this.tvSongName = tvSongName;
        this.tvArtistName = tvArtistName;
        this.imgAvatarSong = imgAvatarSong;
    }

    public Song() {

    }

    public String getTvSongName() {
        return tvSongName;
    }

    public void setTvSongName(String tvSongName) {
        this.tvSongName = tvSongName;
    }

    public String getTvArtistName() {
        return tvArtistName;
    }

    public void setTvArtistName(String tvArtistName) {
        this.tvArtistName = tvArtistName;
    }

    public int getImgAvatarSong() {
        return imgAvatarSong;
    }

    public void setImgAvatarSong(int imgAvatarSong) {
        this.imgAvatarSong = imgAvatarSong;
    }

    public static List<Song> createNewListSong() {
        List<Song> listSong = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Song song = new Song();
            Random random = new Random();
            int position = random.nextInt(3);
            List<Integer> listAvatar = new ArrayList<>();
            listAvatar.add(R.drawable.img_drake);
            listAvatar.add(R.drawable.img_justin);
            listAvatar.add(R.drawable.img_taylor);
            List<String> listName = new ArrayList<>();
            listName.add("Drake");
            listName.add("Justin");
            listName.add("Taylor");
            song.setTvSongName(listName.get(position) + " " + i);
            song.setTvArtistName(listName.get(position) + " " + i);
            song.setImgAvatarSong(listAvatar.get(position));
            listSong.add(song);
        }
        return listSong;
    }
}
