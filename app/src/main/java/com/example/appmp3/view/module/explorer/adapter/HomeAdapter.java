package com.example.appmp3.view.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.databinding.ItemHomeBannerBinding;
import com.example.appmp3.databinding.ItemHomeCategoryBinding;
import com.example.appmp3.databinding.ItemHomeSelectionBinding;
import com.example.appmp3.databinding.ItemHomeVideoBinding;
import com.example.appmp3.model.entity.Banner;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.model.entity.Selection;
import com.example.appmp3.ultils.SnapHelperOneByOne;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Banner> mListBanner = new ArrayList<>();
    private final List<Selection> mListSelection = new ArrayList<>();
    private final List<Category> mListCategory = new ArrayList<>();
    private final List<Category> mListVideo = new ArrayList<>();
    private static final int BANNER = 0;
    private static final int SELECTION = 1;
    private static final int CATEGORY = 2;
    private static final int VIDEO = 3;
    private CategoryAdapter.CategoryClickListener categoryClickListener;
    private CategoryVideoAdapter.CategoryVideoClickListener categoryVideoClickListener;
    private final int ITEM_SIZE = 4;
    private HomeAdapterClickListener homeAdapterClickListener;

    public HomeAdapter(CategoryAdapter.CategoryClickListener categoryClickListener,
                       HomeAdapterClickListener homeAdapterClickListener, CategoryVideoAdapter.CategoryVideoClickListener categoryVideoClickListener) {
        this.categoryClickListener = categoryClickListener;
        this.homeAdapterClickListener = homeAdapterClickListener;
        this.categoryVideoClickListener = categoryVideoClickListener;
    }

    public void updateAdapterCategory(List<Category> mListCategory) {
        this.mListCategory.addAll(mListCategory);
        notifyItemChanged(CATEGORY);
    }

    public void updateAdapterBanner(List<Banner> mListBanner) {
        this.mListBanner.addAll(mListBanner);
        notifyItemChanged(BANNER);
    }

    public void updateAdapterVideo(List<Category> mListVideo) {
        this.mListVideo.addAll(mListVideo);
        notifyItemChanged(VIDEO);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER:
                ItemHomeBannerBinding viewBanner = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new BannerViewHolder(viewBanner, mListBanner);
            case SELECTION:
                ItemHomeSelectionBinding viewSelection = ItemHomeSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new SelectionViewHolder(viewSelection, homeAdapterClickListener);
            case CATEGORY:
                ItemHomeCategoryBinding viewCategory = ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new CategoryViewHolder(viewCategory, mListCategory);
            case VIDEO:
                ItemHomeVideoBinding viewVideo = ItemHomeVideoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new VideoViewHolder(viewVideo, mListVideo);
            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case BANNER:
                if (holder instanceof BannerViewHolder) {
                    ((BannerViewHolder) holder).bind();
                }
                break;
            case SELECTION:
                if (holder instanceof SelectionViewHolder) {
                    ((SelectionViewHolder) holder).bind();
                }
                break;
            case CATEGORY:
                if (holder instanceof CategoryViewHolder) {
                    ((CategoryViewHolder) holder).bind();
                }
                break;
            case VIDEO:
                if (holder instanceof VideoViewHolder) {
                    ((VideoViewHolder) holder).bind();
                }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER) {
            return BANNER;
        }
        if (position == SELECTION) {
            return SELECTION;
        }
        if (position == CATEGORY){
            return CATEGORY;
        }
        return VIDEO;
    }

    @Override
    public int getItemCount() {
        return ITEM_SIZE;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeBannerBinding itemHomeBannerBinding;

        BannerViewHolder(ItemHomeBannerBinding itemView, List<Banner> mBanners) {
            super(itemView.getRoot());
            itemHomeBannerBinding = itemView;
            BannerAdapter bannerAdapter = new BannerAdapter(mBanners);
            itemHomeBannerBinding.recyclerViewHomeBanner.setLayoutManager(new LinearLayoutManager(itemView.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemHomeBannerBinding.recyclerViewHomeBanner.setAdapter(bannerAdapter);

            LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
            itemHomeBannerBinding.recyclerViewHomeBanner.setOnFlingListener(null);
            linearSnapHelper.attachToRecyclerView(itemHomeBannerBinding.recyclerViewHomeBanner);
        }

        public void bind() {
//            BannerAdapter bannerAdapter = new BannerAdapter(banners);
//            itemHomeBannerBinding.recyclerViewHomeBanner.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
//            itemHomeBannerBinding.recyclerViewHomeBanner.setAdapter(bannerAdapter);
//
//            LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
//            itemHomeBannerBinding.recyclerViewHomeBanner.setOnFlingListener(null);
//            linearSnapHelper.attachToRecyclerView(itemHomeBannerBinding.recyclerViewHomeBanner);
        }
    }

    public class SelectionViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeSelectionBinding itemHomeSelectionBinding;
        private HomeAdapterClickListener homeAdapterClickListener;

        SelectionViewHolder(ItemHomeSelectionBinding itemView, HomeAdapterClickListener homeAdapterClickListener) {
            super(itemView.getRoot());
            itemHomeSelectionBinding = itemView;
            this.homeAdapterClickListener = homeAdapterClickListener;
        }

        public void bind() {
            itemHomeSelectionBinding.llCategory.setOnClickListener(v ->
                    homeAdapterClickListener.onAllCategoryClicked()
            );
        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeCategoryBinding itemHomeCategoryBinding;

        public CategoryViewHolder(ItemHomeCategoryBinding itemView, List<Category> mCategories) {
            super(itemView.getRoot());
            itemHomeCategoryBinding = itemView;
            CategoryAdapter categoryAdapter = new CategoryAdapter(mCategories, categoryClickListener);
            itemHomeCategoryBinding.recyclerViewHomeCategory.setLayoutManager(new LinearLayoutManager(itemView.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemHomeCategoryBinding.recyclerViewHomeCategory.setAdapter(categoryAdapter);
        }

        public void bind() {

        }
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeVideoBinding itemHomeVideoBinding;

        public VideoViewHolder(ItemHomeVideoBinding itemView, List<Category> mVideos) {
            super(itemView.getRoot());
            itemHomeVideoBinding = itemView;
            CategoryVideoAdapter categoryVideoAdapter = new CategoryVideoAdapter(mVideos, categoryVideoClickListener);
            itemHomeVideoBinding.recyclerViewHomeVideo.setLayoutManager(new LinearLayoutManager(itemView.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemHomeVideoBinding.recyclerViewHomeVideo.setAdapter(categoryVideoAdapter);
        }

        public void bind() {

        }
    }

    public interface HomeAdapterClickListener {
        void onAllCategoryClicked();
    }
}
