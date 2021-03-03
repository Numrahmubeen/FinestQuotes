package com.finest.quotes.models;

public class QuotesCategory {
    private String categoryTitle;
    private int categoryIcon;
    private int color_bg;

    public QuotesCategory( int categoryIcon,String categoryTitle) {
        this.categoryTitle = categoryTitle;
        this.categoryIcon = categoryIcon;
        this.color_bg = color_bg;
    }

    public int getColor_bg() {
        return color_bg;
    }

    public void setColor_bg(int color_bg) {
        this.color_bg = color_bg;
    }
    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(int categoryIcon) {
        this.categoryIcon = categoryIcon;
    }
}
