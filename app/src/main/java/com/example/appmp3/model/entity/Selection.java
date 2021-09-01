package com.example.appmp3.model.entity;

public class Selection {
    private int newSong;
    private int category;
    private int topSong;
    private int topMV;

    public Selection(int newSong, int category, int topSong, int topMV) {
        this.newSong = newSong;
        this.category = category;
        this.topSong = topSong;
        this.topMV = topMV;
    }

    public int getNewSong() {
        return newSong;
    }

    public void setNewSong(int newSong) {
        this.newSong = newSong;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getTopSong() {
        return topSong;
    }

    public void setTopSong(int topSong) {
        this.topSong = topSong;
    }

    public int getTopMV() {
        return topMV;
    }

    public void setTopMV(int topMV) {
        this.topMV = topMV;
    }
}
