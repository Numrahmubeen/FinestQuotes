package com.dailyinspirationalquotes.lifequotes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyinspirationalquotes.lifequotes.adapters.CategoryAdapter;
import com.dailyinspirationalquotes.lifequotes.api.QuotesApi;
import com.dailyinspirationalquotes.lifequotes.models.Quotes;
import com.dailyinspirationalquotes.lifequotes.models.QuotesCategory;
import com.dailyinspirationalquotes.lifequotes.models.RecyclerViewItemDecorator;

import java.lang.Void;

import com.dailyinspirationalquotes.lifequotes.models.TotalQuotesModel;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kotlin.jvm.internal.Ref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private List<QuotesCategory> categoryModels;
    private CategoryAdapter adapter;
    private DrawerLayout drawer;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setNavigationDrawer(savedInstanceState);
        getQuotesCount();


        categoryModels.add(new QuotesCategory(R.drawable.ic_general_quote, "General", 95));
        categoryModels.add(new QuotesCategory(R.drawable.ic_attitude, "Attitude", 493));
        categoryModels.add(new QuotesCategory(R.drawable.ic_beauty, "Beauty", 972));
        categoryModels.add(new QuotesCategory(R.drawable.ic_best, "Best", 611));
        categoryModels.add(new QuotesCategory(R.drawable.ic_couple, "Marriage", 486));
        categoryModels.add(new QuotesCategory(R.drawable.ic_medical, "Medical", 299));
        categoryModels.add(new QuotesCategory(R.drawable.ic_men, "Men", 869));
        categoryModels.add(new QuotesCategory(R.drawable.ic_mom, "Mom", 998));
        categoryModels.add(new QuotesCategory(R.drawable.ic_money, "Money", 503));
        categoryModels.add(new QuotesCategory(R.drawable.ic_morning, "Morning", 495));
        categoryModels.add(new QuotesCategory(R.drawable.ic_motivational, "Motivational", 111));
        categoryModels.add(new QuotesCategory(R.drawable.ic_movie, "Movies", 340));
        categoryModels.add(new QuotesCategory(R.drawable.ic_music, "Music", 205));
        categoryModels.add(new QuotesCategory(R.drawable.ic_nature, "Nature", 416));
        categoryModels.add(new QuotesCategory(R.drawable.ic_parenting, "Parenting", 146));
        categoryModels.add(new QuotesCategory(R.drawable.ic_patience, "Patience", 231));
        categoryModels.add(new QuotesCategory(R.drawable.ic_patriotism, "Patriotism", 154));
        categoryModels.add(new QuotesCategory(R.drawable.ic_peace, "Peace", 83));


        adapter.addModel(categoryModels);
        adapter.setOnItemClickListener(model -> {
            Intent intent = new Intent(MainActivity.this, QuotesActivity.class);
            intent.putExtra("category", model.getCategoryTitle().toLowerCase());
            startActivity(intent);
        });
    }

    private void setNavigationDrawer(Bundle savedInstanceState) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new MessageFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_message);
        }
    }

    private void init() {
        RecyclerView category_rv = findViewById(R.id.main_category_rv);
        category_rv.setHasFixedSize(true);
        category_rv.setLayoutManager(new GridLayoutManager(this, 2));
        category_rv.addItemDecoration(new RecyclerViewItemDecorator(5, 5, 5, 5));
        adapter = new CategoryAdapter();
        category_rv.setAdapter(adapter);

        categoryModels = new ArrayList<>();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_message:
                Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new MessageFragment()).commit();
                break;
            case R.id.nav_chat:
                Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new ChatFragment()).commit();
                break;
            case R.id.nav_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new ProfileFragment()).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getQuotesCount() {

//        progressBar.setVisibility(View.VISIBLE);

        Call<TotalQuotesModel> call = QuotesApi.getInstance().getApi().getTotalQuotes("gene", "1");

        call.enqueue(new Callback<TotalQuotesModel>() {
            @Override
            public void onResponse(Call<TotalQuotesModel> call, Response<TotalQuotesModel> response) {

                Log.d(TAG, "onResponse: " + response.body());
                Log.d(TAG, "onResponse: " + response.errorBody());
                if (response.isSuccessful() && response.body() != null) {

//                    progressBar.setVisibility(View.GONE);
                } else {
//                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TotalQuotesModel> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private int getRandomNumber() {
        Random random = new Random();
        int number = random.nextInt(1000);
        if (number < 300) return number + 250;
        else return number;
    }
}