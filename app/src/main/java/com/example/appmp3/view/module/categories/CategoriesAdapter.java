package com.example.appmp3.view.module.categories;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmp3.databinding.ItemCategoriesBinding;
import com.example.appmp3.model.entity.Category;
import com.example.appmp3.ultils.ImageUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryVH> {
    private List<Category> categories = new ArrayList<>();
    private ItemCategoryClickListener listener;

    public CategoriesAdapter(ItemCategoryClickListener listener) {
        this.listener = listener;
    }

    public void updateAdapter(List<Category> categories) {
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public CategoryVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        ItemCategoriesBinding binding = ItemCategoriesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CategoryVH(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CategoryVH holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryVH extends RecyclerView.ViewHolder {
        private ItemCategoriesBinding binding;
        private ItemCategoryClickListener listener;

        public CategoryVH(ItemCategoriesBinding binding, ItemCategoryClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        public void bind(Category category) {
            ImageUtils.bindImage(binding.getRoot().getContext(), binding.ivBanner, category.getBanner());
            binding.tvCategoryName.setText(category.getName());
            binding.tvCategoryName.setShadowLayer(30, 0, 0, Color.RED);
            binding.getRoot().setOnClickListener(v -> listener.onCategoryClicked(category));
        }
    }

    public interface ItemCategoryClickListener {
        void onCategoryClicked(Category category);
    }
}
