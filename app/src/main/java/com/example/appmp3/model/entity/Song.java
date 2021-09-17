package com.example.appmp3.model.entity;

public class Song {
    private String tvSongName;
    private String tvSingerName;
    private String tvArtistName;
    private String tvPostName;
    private String imgAvatarSong;
    private String mp3Url;

    public Song(String tvSongName, String tvSingerName, String tvArtistName, String tvPostName,String imgAvatarSong, String mp3Url) {
        this.tvSongName = tvSongName;
        this.tvSingerName = tvSingerName;
        this.tvArtistName = tvArtistName;
        this.tvPostName = tvPostName;
        this.imgAvatarSong = imgAvatarSong;
        this.mp3Url = mp3Url;
    }

    public Song() {

    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public String getTvSingerName() {
        return tvSingerName;
    }

    public void setTvSingerName(String tvSingerName) {
        this.tvSingerName = tvSingerName;
    }

    public String getTvPostName() {
        return tvPostName;
    }

    public void setTvPostName(String tvPostName) {
        this.tvPostName = tvPostName;
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
