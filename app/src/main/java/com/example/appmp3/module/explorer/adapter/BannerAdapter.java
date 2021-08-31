package com.example.appmp3.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
import com.example.appmp3.model.Banner;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {
    private final List<Banner> mListItemBanner;

    public BannerAdapter(List<Banner> mListItemBanner) {
        this.mListItemBanner = mListItemBanner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_home_banner, parent, false);
        return new ViewHolder(view);
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
        private final ImageView imgBanner;

        public ViewHolder(View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.imgBanner);
        }

        public void bind(Banner banner) {
            imgBanner.setImageResource(banner.getBanner());
        }
    }
}