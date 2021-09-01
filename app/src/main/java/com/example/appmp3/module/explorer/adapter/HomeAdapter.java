package com.example.appmp3.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
import com.example.appmp3.databinding.ItemHomeBannerBinding;
import com.example.appmp3.databinding.ItemHomeCategoryBinding;
import com.example.appmp3.databinding.ItemHomeSelectionBinding;
import com.example.appmp3.model.Banner;
import com.example.appmp3.model.Category;
import com.example.appmp3.model.Selection;
import com.example.appmp3.ultils.SnapHelperOneByOne;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Banner> mListBanner;
    private final List<Selection> mListSelection;
    private final List<Category> mCategory;
    private static final int BANNER = 0;
    private static final int SELECTION = 1;
    private static final int CATEGORY = 2;
    private CategoryAdapter.CategoryClickListener categoryClickListener;
    private final int ITEM_SIZE = 3;

    public HomeAdapter(List<Category> categories, List<Banner> listBanner, List<Selection> listSelection, CategoryAdapter.CategoryClickListener categoryClickListener) {
        this.mCategory = categories;
        this.mListBanner = listBanner;
        this.mListSelection = listSelection;
        this.categoryClickListener = categoryClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                ItemHomeBannerBinding viewBanner = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new BannerViewHolder(viewBanner);
            case 1:
                ItemHomeSelectionBinding viewSelection = ItemHomeSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new SelectionViewHolder(viewSelection);
            case 2:
                ItemHomeCategoryBinding viewCategory = ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.getContext()));
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
                }
                break;
            case CATEGORY:
                if (holder instanceof CategoryViewHolder) {
                    ((CategoryViewHolder) holder).bind(mCategory);
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
            linearSnapHelper.attachToRecyclerView( itemHomeBannerBinding.recyclerViewHomeBanner);
        }
    }

    public class SelectionViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeSelectionBinding itemHomeSelectionBinding;

        SelectionViewHolder(ItemHomeSelectionBinding itemView) {
            super(itemView.getRoot());
            itemHomeSelectionBinding = itemView;
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
}
