package com.example.appmp3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    private String categoryId;
    private String categoryName;
    private String imageUrl;

    public Category(String categoryId, String categoryName, String imageUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
    }

    public Category() {

    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static List<Category> createNewListCategory() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("Id1", "Top 100 Nhạc\n" + "Vpop Hay Nhất", "https://avatar-nct.nixcdn.com/playlist/2019/11/15/1/a/e/b/1573810360774_500.jpg"));
        categoryList.add(new Category("Id2", "#ZINGCHART","https://photo-resize-zmp3.zadn.vn/w240_r1x1_jpeg/cover/9/7/c/9/97c960ac271e94fa47c87a12aa7d3be5.jpg"));
        return categoryList;
    }
}
