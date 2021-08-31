package com.example.appmp3.model;

public class Song {
    private String tvSongName;
    private String tvArtistName;
    private String imgAvatarSong;

    public Song(String tvSongName, String tvArtistName, String imgAvatarSong) {
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

    public String getImgAvatarSong() {
        return imgAvatarSong;
    }

    public void setImgAvatarSong(String imgAvatarSong) {
        this.imgAvatarSong = imgAvatarSong;
    }
}
