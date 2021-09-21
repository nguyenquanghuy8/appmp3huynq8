package com.example.appmp3.model.entity;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String name;
    private String banner;

    public Category(String id, String name, String banner) {
        this.id = id;
        this.name = name;
        this.banner = banner;
    }

    public Category() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override
    public String toString() {
        return name;
    }
}
