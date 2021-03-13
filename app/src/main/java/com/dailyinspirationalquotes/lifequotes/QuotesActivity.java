package com.dailyinspirationalquotes.lifequotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.dailyinspirationalquotes.lifequotes.adapters.QuotesAdapter;
import com.dailyinspirationalquotes.lifequotes.api.QuotesApi;
import com.dailyinspirationalquotes.lifequotes.api.QuotesService;
import com.dailyinspirationalquotes.lifequotes.models.Quotes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends AppCompatActivity {

    private static final String TAG = "QuotesActivity";

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private QuotesService quoteService;
    private QuotesAdapter quotesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        Intent intent = getIntent();
        String quoteCategory = intent.getStringExtra("category");

        recyclerView = findViewById(R.id.quotes_rv);
        progressBar = findViewById(R.id.main_progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
        recyclerView.setHasFixedSize(true);

        quotesAdapter = new QuotesAdapter(this);
        recyclerView.setAdapter(quotesAdapter);

        getQuotes(quoteCategory);
    }

    private void getQuotes(String category) {
        progressBar.setVisibility(View.VISIBLE);

        Call<List<Quotes>> call = QuotesApi.getInstance().getApi().getQuoteByCategory(category, "1");

        call.enqueue(new Callback<List<Quotes>>() {
            @Override
            public void onResponse(Call<List<Quotes>> call, Response<List<Quotes>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Quotes> quotes = response.body();
                    quotesAdapter.addQuotes(quotes);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Quotes>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}