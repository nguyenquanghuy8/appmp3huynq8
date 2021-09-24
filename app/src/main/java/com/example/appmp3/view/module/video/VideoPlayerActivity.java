package com.example.appmp3.view.module.video;

import android.content.Context;
import android.content.Intent;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ActivityVideoPlayerBinding;
import com.example.appmp3.model.entity.Song;
import com.example.appmp3.view.base.BaseActivity;
import com.example.appmp3.viewmodel.VideoPlayerViewModel;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class VideoPlayerActivity extends BaseActivity<ActivityVideoPlayerBinding, VideoPlayerViewModel> {
    private static final String EXTRA_VIDEO = "extra_video";

    private SimpleExoPlayer simpleExoPlayer;
    private Song song;

    public static void startActivity(Context context, Song song) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra(EXTRA_VIDEO, song);
        context.startActivity(intent);
    }

    @Override
    protected void addEvent() {

    }

    @Override
    protected void obsViewModel() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_player;
    }

    @Override
    protected Class<VideoPlayerViewModel> getViewModelClass() {
        return VideoPlayerViewModel.class;
    }

    @Override
    protected void init() {
        song = (Song) getIntent().getSerializableExtra(EXTRA_VIDEO);
        getBinding().setSong(song);
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();

        getBinding().playerViewVideo.setPlayer(simpleExoPlayer);
        MediaItem mediaItem = MediaItem.fromUri(song.getVideoUrl());
        simpleExoPlayer.setMediaItem(mediaItem);
        simpleExoPlayer.prepare();
        simpleExoPlayer.play();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}
