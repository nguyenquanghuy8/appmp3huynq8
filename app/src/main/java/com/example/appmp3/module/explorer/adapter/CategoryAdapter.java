package com.example.appmp3.module.explorer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmp3.R;
import com.example.appmp3.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<Category> categories;
    private CategoryClickListener categoryClickListener;
    private Context context;

    public CategoryAdapter(List<Category> categories, CategoryClickListener categoryClickListener) {
        this.categories = categories;
        this.categoryClickListener = categoryClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        context = parent.getContext();
        return new ViewHolder(view, categoryClickListener);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);

        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private ImageView ivAvatar;
        private CategoryClickListener categoryClickListener;

        public ViewHolder(View itemView, CategoryClickListener categoryClickListener) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            this.categoryClickListener = categoryClickListener;
        }

        public void bind(Category category) {
            tvName.setText(category.getCategoryName());
            String imgAva = category.getImageUrl();
            Glide.with(context)
                    .load(imgAva)
                    .into(ivAvatar);
            itemView.setOnClickListener(v -> categoryClickListener.onCategoryClicked(category));
        }
    }

    public interface CategoryClickListener {
        void onCategoryClicked(Category category);
    }
}
