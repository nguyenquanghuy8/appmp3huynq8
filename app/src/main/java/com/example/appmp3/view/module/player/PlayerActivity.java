package com.example.appmp3.view.module.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityPlayerBinding;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.service.PlayServices;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.viewmodel.PlayerViewModel;


public class PlayerActivity extends BaseActivity<ActivityPlayerBinding, PlayerViewModel> {

    private static final String EXTRA_SONG = "extra_song";
    private Song song;
    private PlayServices playServices;
    private boolean isBound = false;

    public static void startActivity(Context context, Song song) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(EXTRA_SONG, song);
        context.startActivity(intent);
    }

    @Override
    protected void init() {
        song = (Song) getIntent().getSerializableExtra(EXTRA_SONG);
        getBinding().setSong(song);
        Intent intent = new Intent(this, PlayServices.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                PlayServices.MyBinder binder = (PlayServices.MyBinder) service;
                playServices = binder.getService();
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound = false;
            }
        }, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void addEvent() {
        getBinding().btnPlayPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playServices.playMusic(song);
            }
        });
//        Glide.with(this)
//                .load(song.getAvatarSong())
//                .into(getBinding().imgAvatarSong);
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
