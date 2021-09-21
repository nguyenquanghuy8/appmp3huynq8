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

public class PlayServices extends Service {
    private IBinder binder;
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        mediaPlayer = new MediaPlayer();
        binder = new MyBinder();
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        myPlayer.playMediaPlayer();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
//        myPlayer.stopMediaPlayer();
        mediaPlayer.release();
        return super.onUnbind(intent);
    }

    public void playMusic(Song song) {
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(this, Uri.parse(song.getMp3Url()));
            mediaPlayer.prepareAsync();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyBinder extends Binder {
        public PlayServices getService() {
            return PlayServices.this;
        }
    }
}
