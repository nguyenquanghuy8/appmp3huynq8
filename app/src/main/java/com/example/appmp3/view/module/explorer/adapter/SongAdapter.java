package com.example.appmp3.view.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmp3.databinding.ItemRecyclerViewHomeSongBinding;
import com.example.appmp3.model.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<Song> mListSong = new ArrayList<>();
    private OnItemSongClickListener onItemSongClickListener;

    public SongAdapter(OnItemSongClickListener onItemSongClickListener) {
        this.onItemSongClickListener = onItemSongClickListener;
    }

    public void updateAdapter(List<Song> mListSong) {
        this.mListSong.addAll(mListSong);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecyclerViewHomeSongBinding itemView = ItemRecyclerViewHomeSongBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView, onItemSongClickListener);
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
        private OnItemSongClickListener listener;

        public ViewHolder(ItemRecyclerViewHomeSongBinding itemView, OnItemSongClickListener listener) {
            super(itemView.getRoot());
            itemRecyclerViewHomeSongBinding = itemView;
            this.listener = listener;
        }

        public void bind(Song song) {
            itemRecyclerViewHomeSongBinding.setSong(song);

            Glide.with(itemView.getContext())
                    .load(song.getAvatarSong())
                    .into(itemRecyclerViewHomeSongBinding.imgAvatarSong);
            itemRecyclerViewHomeSongBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemSongClick(song);
                }
            });
        }
    }

    public interface OnItemSongClickListener {
        void onItemSongClick(Song song);
    }

    public List<Song> getItemsAdapter(){
        return mListSong;
    }
}
