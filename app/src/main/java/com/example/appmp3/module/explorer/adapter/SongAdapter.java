package com.example.appmp3.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmp3.R;
import com.example.appmp3.databinding.ItemRecyclerViewHomeSongBinding;
import com.example.appmp3.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<Song> mListSong = new ArrayList<>();

    public SongAdapter() {
    }

    public void updateAdapter(List<Song> mListSong) {
        this.mListSong.addAll(mListSong);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecyclerViewHomeSongBinding itemView = ItemRecyclerViewHomeSongBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder holder, int position) {
        Song song = mListSong.get(position);
        holder.bind(song);
    }

    @Override
    public int getItemCount() {
        return mListSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemRecyclerViewHomeSongBinding itemRecyclerViewHomeSongBinding;

        public ViewHolder(ItemRecyclerViewHomeSongBinding itemView) {
            super(itemView.getRoot());
            itemRecyclerViewHomeSongBinding = itemView;
        }

        public void bind(Song song) {
            itemRecyclerViewHomeSongBinding.setSong(song);

            Glide.with(itemView.getContext())
                    .load(song.getImgAvatarSong())
                    .into(itemRecyclerViewHomeSongBinding.imgAvatarSong);
        }
    }
}
