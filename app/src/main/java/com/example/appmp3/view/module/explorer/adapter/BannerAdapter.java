package com.example.appmp3.view.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.databinding.ItemRecyclerViewHomeBannerBinding;
import com.example.appmp3.model.entity.Banner;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    private final List<Banner> mListItemBanner;

    public BannerAdapter(List<Banner> mListItemBanner) {
        this.mListItemBanner = mListItemBanner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecyclerViewHomeBannerBinding itemView = ItemRecyclerViewHomeBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BannerAdapter.ViewHolder holder, int position) {
        Banner itemBanner = mListItemBanner.get(position);
        holder.bind(itemBanner);
    }

    @Override
    public int getItemCount() {
        return mListItemBanner.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRecyclerViewHomeBannerBinding itemRecyclerViewHomeBannerBinding;

        public ViewHolder(ItemRecyclerViewHomeBannerBinding itemView) {
            super(itemView.getRoot());
            itemRecyclerViewHomeBannerBinding = itemView;
        }

        public void bind(Banner banner) {
            itemRecyclerViewHomeBannerBinding.imgBanner.setImageResource(banner.getBanner());
        }
    }
}