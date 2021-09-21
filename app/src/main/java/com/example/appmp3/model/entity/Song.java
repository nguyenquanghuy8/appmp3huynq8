package com.example.appmp3.model.entity;

import java.io.Serializable;

public class Song implements Serializable {
    private String songName;
    private String singerName;
    private String artistName;
    private String postName;
    private String avatarSong;
    private String mp3Url;

    public Song(String songName, String singerName, String artistName, String postName, String avatarSong, String mp3Url) {
        this.songName = songName;
        this.singerName = singerName;
        this.artistName = artistName;
        this.postName = postName;
        this.avatarSong = avatarSong;
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

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAvatarSong() {
        return avatarSong;
    }

    public void setAvatarSong(String avatarSong) {
        this.avatarSong = avatarSong;
    }
}
