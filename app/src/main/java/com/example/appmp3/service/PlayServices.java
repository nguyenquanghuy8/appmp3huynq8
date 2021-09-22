package com.example.appmp3.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.appmp3.model.entity.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlayServices extends Service {
    private IBinder binder;
    private MediaPlayer mediaPlayer;
    private ArrayList<Song> songs = new ArrayList<>();
    private int songPos = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        intPlayer();
    }

    private void intPlayer() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(mp -> mediaPlayer.start());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        mediaPlayer.release();
        return super.onUnbind(intent);
    }

    public void playMusic(Song song) {
        songPos = songs.indexOf(song);
        try {
            mediaPlayer.setDataSource(this, Uri.parse(song.getMp3Url()));
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playNextMusic() {
        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
            mediaPlayer.stop();
        }
        songPos++;
        if (songPos > songs.size() - 1) {
            songPos = 0;
        }
        try {
            intPlayer();
            mediaPlayer.setDataSource(this, Uri.parse(songs.get(songPos).getMp3Url()));
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playPreviousMusic() {
        if (mediaPlayer.isPlaying() || mediaPlayer != null) {
            mediaPlayer.stop();
        }
        songPos--;
        if (songPos < 0) {
            songPos = songs.size() - 1;
        }
        try {
            intPlayer();
            mediaPlayer.setDataSource(this, Uri.parse(songs.get(songPos).getMp3Url()));
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSongs(List<Song> songs) {
        this.songs.clear();
        this.songs.addAll(songs);
    }

    public boolean isPlaying() {
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return true;
    }

    public void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public class MyBinder extends Binder {
        public PlayServices getService() {
            return PlayServices.this;
        }
    }
}
