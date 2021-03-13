package com.dailyinspirationalquotes.lifequotes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dailyinspirationalquotes.lifequotes.R;
import com.dailyinspirationalquotes.lifequotes.adapters.FavouriteAdapter;
import com.dailyinspirationalquotes.lifequotes.models.FavouriteModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private List<FavouriteModel> models;
    private FavouriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        RecyclerView fav_rv = findViewById(R.id.favourite_rv);
        fav_rv.setHasFixedSize(true);
        fav_rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavouriteAdapter(this);
        fav_rv.setAdapter(adapter);
        setModels();
    }

    private void setModels() {
        models = new ArrayList<>();
        models.add(new FavouriteModel("", "Hello world", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world2", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world3", "tauseef", false));
        models.add(new FavouriteModel("", "Hello world4", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world5", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world6", "tauseef", false));
        models.add(new FavouriteModel("", "Hello world7", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world8", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world9", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world10", "tauseef", false));
        models.add(new FavouriteModel("", "Hello world11", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world12", "tauseef", true));
        models.add(new FavouriteModel("", "Hello world13", "tauseef", true));
        adapter.addFavouriteModel(models);
    }
}