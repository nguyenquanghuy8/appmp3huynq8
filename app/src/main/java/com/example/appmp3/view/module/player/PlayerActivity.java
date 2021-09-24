package com.example.appmp3.view.module.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityPlayerBinding;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.service.PlayServices;
import com.example.appmp3.ultils.ImageUtils;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.viewmodel.PlayerViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PlayerActivity extends BaseActivity<ActivityPlayerBinding, PlayerViewModel> {

    private static final String EXTRA_SONG = "extra_song";
    private static final String EXTRA_SONGS = "extra_songs";
    private Song song;
    private List<Song> songs = new ArrayList<>();
    private PlayServices playServices;

    public static void startActivity(Context context, Song song, List<Song> songs) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(EXTRA_SONG, song);
        intent.putExtra(EXTRA_SONGS, songs.toArray(new Song[0]));
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        song = (Song) getIntent().getSerializableExtra(EXTRA_SONG);
        Song[] songArray = (Song[]) getIntent().getSerializableExtra(EXTRA_SONGS);
        songs.addAll(Arrays.asList(songArray));
        getBinding().setSong(song);
        Intent intent = new Intent(this, PlayServices.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PlayServices.MyBinder binder = (PlayServices.MyBinder) service;
                playServices = binder.getService();
                playServices.setSongs(songs);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        }, Context.BIND_AUTO_CREATE);
        getBinding().btnPlayPlayer.setImageResource(R.drawable.ic_play);
        ImageUtils.bindImage(this, getBinding().imgAvatarSong, song.getAvatarSong());
    }

    @Override
    protected void addEvent() {
        getBinding().btnPlayPlayer.setOnClickListener(v -> {
            if (playServices.isPlaying()) {
                playServices.pauseMusic();
                getBinding().btnPlayPlayer.setImageResource(R.drawable.ic_play);
            } else {
                playServices.playMusic(song);
                getBinding().btnPlayPlayer.setImageResource(R.drawable.ic_pause);
            }
        });

        getBinding().btnNextPlayer.setOnClickListener(v -> playServices.playNextMusic());
        getBinding().btnPreviousPlayer.setOnClickListener(v -> playServices.playPreviousMusic());
    }

    @Override
    protected void obsViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_player;
    }

    @Override
    protected Class<PlayerViewModel> getViewModelClass() {
        return PlayerViewModel.class;
    }
}
