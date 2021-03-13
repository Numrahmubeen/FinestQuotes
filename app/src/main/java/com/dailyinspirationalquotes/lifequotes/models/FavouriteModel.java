package com.dailyinspirationalquotes.lifequotes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavouriteModel {

    private String id;
    private String text;
    private String author;
    private boolean isLike;

    public FavouriteModel(String id, String text, String author, boolean isLike) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.isLike = isLike;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isLike() {
        return isLike;
    }
}
