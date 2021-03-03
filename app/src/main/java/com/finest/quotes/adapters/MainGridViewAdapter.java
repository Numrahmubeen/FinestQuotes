package com.finest.quotes.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;


import com.finest.quotes.R;
import com.finest.quotes.models.QuotesCategory;

import java.util.ArrayList;
import java.util.Random;


public class MainGridViewAdapter extends ArrayAdapter {

    private ArrayList<QuotesCategory> categoryList = new ArrayList<>();

    public MainGridViewAdapter(Context context, int textViewResourceId, ArrayList objects) {
        super(context, textViewResourceId, objects);
        categoryList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.main_category_item_gv, null);
        CardView cardView = v.findViewById(R.id.grid_view_items);

        int left = dpToPx(12);
        int top = dpToPx(12);
        int right = dpToPx(12);
        int bottom = dpToPx(12);

        int spanCount = 2;

        boolean isFirstTwoItems = position < spanCount;
        boolean isLasTwoItems = position > getCount()-spanCount;

        if(isFirstTwoItems)
        {
            top = dpToPx(24);
        }
        if(isLasTwoItems)
        {
            bottom = dpToPx(24);
        }
        boolean isLeftSide = position + 1 % spanCount != 0;
        boolean isRightSide = !isLeftSide;
         if(isLeftSide)
         {
             right = dpToPx(12);
         }
        if(isRightSide)
        {
            left = dpToPx(12);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) cardView.getLayoutParams();
        layoutParams.setMargins(left,top,right,bottom);
        cardView.setLayoutParams(layoutParams);

        TextView textView = (TextView) v.findViewById(R.id.categoryTitleItemGv_tv);
        RelativeLayout relativeLayout = v.findViewById(R.id.category_rl);
        textView.setText(categoryList.get(position).getCategoryTitle());
        ImageView imageView = v.findViewById(R.id.categoryIconItemGv_iv);
        imageView.setImageResource(categoryList.get(position).getCategoryIcon());

        Random r = new Random();
        int color = Color.argb(255, r.nextInt(200), r.nextInt(200), r.nextInt(200));
        relativeLayout.setBackgroundColor(color);
        return v;
    }
    private int dpToPx(int dp)
    {
        float px = dp * getContext().getResources().getDisplayMetrics().density;
        return (int) px;
    }

}
