package com.dailyinspirationalquotes.lifequotes.models;

public class QuotesCategory {
    private int categoryIcon;
    private String categoryTitle;
    private int color_bg;
    private int quantity;

    public QuotesCategory(int categoryIcon, String categoryTitle, int quantity) {
        this.categoryIcon = categoryIcon;
        this.categoryTitle = categoryTitle;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }
}
