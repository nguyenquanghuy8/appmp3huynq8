package com.example.appmp3.module.explorer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmp3.R;
import com.example.appmp3.model.Song;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private final List<Song> mListSong;

    public SongAdapter(List<Song> mListSong) {
        this.mListSong =  mListSong;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_home_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder holder, int position) {
        Song song = mListSong.get(position);
        holder.tvSongName.setText(song.getTvSongName());
        holder.tvArtistName.setText(song.getTvArtistName());
        holder.imgAvatarSong.setImageResource(song.getImgAvatarSong());
    }

    @Override
    public int getItemCount() {
        return mListSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvSongName;
        private final TextView tvArtistName;
        private final ImageView imgAvatarSong;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSongName = itemView.findViewById(R.id.tvSongName);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);
            imgAvatarSong = itemView.findViewById(R.id.imgAvatarSong);
        }
    }
}