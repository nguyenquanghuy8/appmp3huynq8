package com.example.appmp3.module.explorer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.R;
import com.example.appmp3.event.IEventClick;
import com.example.appmp3.model.Banner;
import com.example.appmp3.model.Category;
import com.example.appmp3.model.ItemBanner;
import com.example.appmp3.model.Selection;
import com.example.appmp3.model.Song;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Banner> mListBanner;
    private final List<Selection> mListSelection;
    private final List<Category> mCategory;
    private static final int BANNER = 0;
    private static final int SELECTION = 1;
    private static final int CATEGORY = 2;
    private final Context context;
    private IEventClick iEventClick;

    public HomeAdapter(Context context, List<Category> categories, List<Banner> listBanner, List<Selection> listSelection, IEventClick iEventClick) {
        this.context = context;
        this.mCategory = categories;
        this.mListBanner = listBanner;
        this.mListSelection = listSelection;
        this.iEventClick = iEventClick;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_banner, parent, false);
                return new BannerViewholder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_selection, parent, false);
                return new SelectionViewholder(view);
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
                if (holder instanceof BannerViewholder) {
                    BannerAdapter bannerAdapter = new BannerAdapter(ItemBanner.createNewBanner());
                    ((BannerViewholder) holder).recyclerViewHomeBanner.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    ((BannerViewholder) holder).recyclerViewHomeBanner.setAdapter(bannerAdapter);

                    LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
                    linearSnapHelper.attachToRecyclerView(((BannerViewholder) holder).recyclerViewHomeBanner);
                }
                break;
            case SELECTION:
                if (holder instanceof SelectionViewholder) {
                }
                break;
            case CATEGORY:
                if (holder instanceof CategoryViewHolder) {
                    CategoryAdapter categoryAdapter = new CategoryAdapter(Category.createNewListCategory(), iEventClick);
                    ((CategoryViewHolder) holder).recyclerViewHomeCategory.setHasFixedSize(true);
                    ((CategoryViewHolder) holder).recyclerViewHomeCategory.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    ((CategoryViewHolder) holder).recyclerViewHomeCategory.setAdapter(categoryAdapter);
                    ((CategoryViewHolder) holder).recyclerViewHomeCategory.setNestedScrollingEnabled(false);
                }
                break;
        }
    }

    public static class SnapHelperOneByOne extends LinearSnapHelper {

        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {

            if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
                return RecyclerView.NO_POSITION;
            }

            final View currentView = findSnapView(layoutManager);

            if (currentView == null) {
                return RecyclerView.NO_POSITION;
            }

            LinearLayoutManager myLayoutManager = (LinearLayoutManager) layoutManager;

            int position1 = myLayoutManager.findFirstVisibleItemPosition();
            int position2 = myLayoutManager.findLastVisibleItemPosition();

            int currentPosition = layoutManager.getPosition(currentView);

            if (velocityX > 400) {
                currentPosition = position2;
            } else if (velocityX < 400) {
                currentPosition = position1;
            }

            if (currentPosition == RecyclerView.NO_POSITION) {
                return RecyclerView.NO_POSITION;
            }

            return currentPosition;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else if (position == 1) {
            return SELECTION;
        } else {
            return CATEGORY;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class BannerViewholder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerViewHomeBanner;

        @SuppressLint("ClickableViewAccessibility")
        BannerViewholder(View view) {
            super(view);
            recyclerViewHomeBanner = view.findViewById(R.id.recyclerViewHomeBanner);
        }
    }

    public class SelectionViewholder extends RecyclerView.ViewHolder {
        private ImageButton btnNewSong;
        private ImageButton btnCategory;
        private ImageButton btnTopSong;
        private ImageButton btnTopMV;

        SelectionViewholder(View view) {
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
    }
}
