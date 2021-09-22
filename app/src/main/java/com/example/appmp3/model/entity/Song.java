package com.example.appmp3.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Song implements Serializable {
    private String songName;
    private String singerName;
    private String artistName;
    private String postName;
    private String avatarSong;
    private String mp3Url;
    private String collectionId;

    public Song(String songName, String singerName, String artistName, String postName, String avatarSong, String mp3Url, String collectionId) {
        this.songName = songName;
        this.singerName = singerName;
        this.artistName = artistName;
        this.postName = postName;
        this.avatarSong = avatarSong;
        this.mp3Url = mp3Url;
        this.collectionId = collectionId;
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

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(songName, song.songName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songName);
    }
}
