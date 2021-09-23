package com.example.appmp3.view.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmp3.databinding.ItemVideoBinding;
import com.example.appmp3.model.entity.Category;

import java.util.List;


public class CategoryVideoAdapter extends RecyclerView.Adapter<CategoryVideoAdapter.ViewHolder> {
    private List<Category> videos;
    private CategoryVideoClickListener categoryVideoClickListener;

    public CategoryVideoAdapter(List<Category> videos, CategoryVideoClickListener categoryVideoClickListener) {
        this.categoryVideoClickListener = categoryVideoClickListener;
        this.videos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemVideoBinding view = ItemVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(view, categoryVideoClickListener);
    }

    @Override
    public void onBindViewHolder(CategoryVideoAdapter.ViewHolder holder, int position) {
        Category video = videos.get(position);
        holder.bind(video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CategoryVideoClickListener categoryVideoClickListener;
        private ItemVideoBinding itemVideoBinding;

        public ViewHolder(ItemVideoBinding itemView, CategoryVideoClickListener categoryVideoClickListener) {
            super(itemView.getRoot());
            itemVideoBinding = itemView;
            this.categoryVideoClickListener = categoryVideoClickListener;
        }

        public void bind(Category video) {
            itemVideoBinding.setVideo(video);
            String imgAva = video.getBanner();
            Glide.with(itemView.getContext())
                    .load(imgAva)
                    .into(itemVideoBinding.ivAvatarVideo);
            itemView.setOnClickListener(v -> categoryVideoClickListener.onCategoryVideoClicked(video));
        }
    }

    public interface CategoryVideoClickListener {
        void onCategoryVideoClicked(Category video);
    }
}
