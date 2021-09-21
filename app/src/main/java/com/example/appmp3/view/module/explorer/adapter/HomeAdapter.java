package com.example.appmp3.view.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.databinding.ItemHomeBannerBinding;
import com.example.appmp3.databinding.ItemHomeCategoryBinding;
import com.example.appmp3.databinding.ItemHomeSelectionBinding;
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
    private static final int BANNER = 0;
    private static final int SELECTION = 1;
    private static final int CATEGORY = 2;
    private CategoryAdapter.CategoryClickListener categoryClickListener;
    private final int ITEM_SIZE = 3;
    private HomeAdapterClickListener homeAdapterClickListener;

    public HomeAdapter(CategoryAdapter.CategoryClickListener categoryClickListener,
                       HomeAdapterClickListener homeAdapterClickListener) {
        this.categoryClickListener = categoryClickListener;
        this.homeAdapterClickListener = homeAdapterClickListener;
    }

    public void updateAdapterCategory(List<Category> mListCategory) {
        this.mListCategory.addAll(mListCategory);
        notifyItemChanged(2);
    }

    public void updateAdapterBanner(List<Banner> mListBanner) {
        this.mListBanner.addAll(mListBanner);
        notifyItemChanged(0);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                ItemHomeBannerBinding viewBanner = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new BannerViewHolder(viewBanner);
            case 1:
                ItemHomeSelectionBinding viewSelection = ItemHomeSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new SelectionViewHolder(viewSelection, homeAdapterClickListener);
            case 2:
                ItemHomeCategoryBinding viewCategory = ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new CategoryViewHolder(viewCategory);
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
                    ((BannerViewHolder) holder).bind(mListBanner);
                }
                break;
            case SELECTION:
                if (holder instanceof SelectionViewHolder) {
                    ((SelectionViewHolder) holder).bind();
                }
                break;
            case CATEGORY:
                if (holder instanceof CategoryViewHolder) {
                    ((CategoryViewHolder) holder).bind(mListCategory);
                }
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        }
        if (position == 1) {
            return SELECTION;
        }
        return CATEGORY;
    }

    @Override
    public int getItemCount() {
        return ITEM_SIZE;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeBannerBinding itemHomeBannerBinding;

        BannerViewHolder(ItemHomeBannerBinding itemView) {
            super(itemView.getRoot());
            itemHomeBannerBinding = itemView;
        }

        public void bind(List<Banner> banners) {
            BannerAdapter bannerAdapter = new BannerAdapter(banners);
            itemHomeBannerBinding.recyclerViewHomeBanner.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemHomeBannerBinding.recyclerViewHomeBanner.setAdapter(bannerAdapter);

            LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
            itemHomeBannerBinding.recyclerViewHomeBanner.setOnFlingListener(null);
            linearSnapHelper.attachToRecyclerView(itemHomeBannerBinding.recyclerViewHomeBanner);
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

        public CategoryViewHolder(ItemHomeCategoryBinding itemView) {
            super(itemView.getRoot());
            itemHomeCategoryBinding = itemView;
        }

        public void bind(List<Category> mCategories) {
            CategoryAdapter categoryAdapter = new CategoryAdapter(mCategories, categoryClickListener);
            itemHomeCategoryBinding.recyclerViewHomeCategory.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            itemHomeCategoryBinding.recyclerViewHomeCategory.setAdapter(categoryAdapter);
        }
    }

    public interface HomeAdapterClickListener {
        void onAllCategoryClicked();
    }
}
