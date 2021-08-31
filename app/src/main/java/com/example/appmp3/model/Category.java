package com.example.appmp3.model;

import java.io.Serializable;

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
}
