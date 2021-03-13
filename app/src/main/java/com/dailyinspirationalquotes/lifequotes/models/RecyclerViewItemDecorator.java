package com.dailyinspirationalquotes.lifequotes.models;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemDecorator extends RecyclerView.ItemDecoration {
    private final int topHeight;
    private final int bottomHeight;
    private final int leftHeight;
    private final int rightHeight;

    public RecyclerViewItemDecorator(int topHeight, int bottomHeight, int leftHeight, int rightHeight) {
        this.topHeight = topHeight;
        this.bottomHeight = bottomHeight;
        this.leftHeight = leftHeight;
        this.rightHeight = rightHeight;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = bottomHeight;
        outRect.top = topHeight;
        outRect.left = leftHeight;
        outRect.right = rightHeight;
    }
}
