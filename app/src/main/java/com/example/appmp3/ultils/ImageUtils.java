package com.example.appmp3.ultils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {
    public static void bindImage(Context context, ImageView view, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .into(view);
    }
}
