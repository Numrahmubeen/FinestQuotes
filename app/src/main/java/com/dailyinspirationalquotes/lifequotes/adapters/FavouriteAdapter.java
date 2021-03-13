package com.dailyinspirationalquotes.lifequotes.adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyinspirationalquotes.lifequotes.R;
import com.dailyinspirationalquotes.lifequotes.models.FavouriteModel;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FavouriteAdapter extends RecyclerView.Adapter {
    private List<FavouriteModel> favouriteModels = new ArrayList<FavouriteModel>();
    private Context context;
    private int myColor;

    public FavouriteAdapter(Context context) {
        this.context = context;
    }

    public void addFavouriteModel(List<FavouriteModel> favouriteModels) {
        this.favouriteModels = favouriteModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_favourite, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vholder, int position) {
        FavouriteModel result = favouriteModels.get(position);
        QuoteViewHolder holder = (QuoteViewHolder) vholder;

        holder.textQuote_tv.setText(result.getText());
        holder.author_tv.setText(String.valueOf(result.getAuthor()));
        if (result.isLike())
            holder.like_iv.setImageResource(R.drawable.ic_vec_heart);
        if (!result.isLike())
            holder.like_iv.setImageResource(R.drawable.ic_heart_empty);
    }

    private Bitmap getScreenShot(View view, int color) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();

        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else {
            canvas.drawColor(color);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    @Override
    public int getItemCount() {
        return favouriteModels.size();
    }

    public class QuoteViewHolder extends RecyclerView.ViewHolder {

        TextView textQuote_tv, author_tv;
        ImageView like_iv;

        public QuoteViewHolder(View itemView) {
            super(itemView);

            textQuote_tv = (TextView) itemView.findViewById(R.id.favourite_quote_tv);
            author_tv = itemView.findViewById(R.id.favourite_author_tv);
            like_iv = itemView.findViewById(R.id.favourite_like_iv);
        }
    }
}

