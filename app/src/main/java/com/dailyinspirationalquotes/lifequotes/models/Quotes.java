package com.dailyinspirationalquotes.lifequotes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quotes {

    @SerializedName("n")
    @Expose
    private String id;

    @SerializedName("text")
    @Expose
    private String text;

    @SerializedName("author")
    @Expose
    private String author;

    public Quotes() {
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }
}
