package com.example.appmp3.module.explorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
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
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner, parent, false);
                return new BannerViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_selection, parent, false);
                return new SelectionViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_category, parent, false);
                return new CategoryViewHolder(view);
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
        private RecyclerView recyclerViewHomeBanner;

        BannerViewHolder(View view) {
            super(view);
            recyclerViewHomeBanner = view.findViewById(R.id.recyclerViewHomeBanner);
        }

        public void bind(List<Banner> banners) {
            BannerAdapter bannerAdapter = new BannerAdapter(banners);
            recyclerViewHomeBanner.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerViewHomeBanner.setAdapter(bannerAdapter);

            LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
            linearSnapHelper.attachToRecyclerView(recyclerViewHomeBanner);
        }
    }

    public class SelectionViewHolder extends RecyclerView.ViewHolder {
        private ImageButton btnNewSong;
        private ImageButton btnCategory;
        private ImageButton btnTopSong;
        private ImageButton btnTopMV;

        SelectionViewHolder(View view) {
            super(view);
            btnNewSong = view.findViewById(R.id.btnNewSong);
            btnCategory = view.findViewById(R.id.btnCategory);
            btnTopSong = view.findViewById(R.id.btnTopSong);
            btnTopMV = view.findViewById(R.id.btnTopMV);
        }
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerViewHomeCategory;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            recyclerViewHomeCategory = itemView.findViewById(R.id.recyclerViewHomeCategory);
        }

        public void bind(List<Category> mCategories) {
            CategoryAdapter categoryAdapter = new CategoryAdapter(mCategories, categoryClickListener);
            recyclerViewHomeCategory.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerViewHomeCategory.setAdapter(categoryAdapter);
        }
    }
}
