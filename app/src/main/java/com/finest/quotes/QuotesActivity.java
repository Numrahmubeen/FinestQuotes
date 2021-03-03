package com.finest.quotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.finest.quotes.adapters.QuotesAdapter;
import com.finest.quotes.api.QuotesApi;
import com.finest.quotes.api.QuotesService;
import com.finest.quotes.models.Quotes;
import com.finest.quotes.models.ResultsApiQuotes;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuotesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;

    private QuotesService quoteService;
    private static String CATEGORY ;
    private QuotesAdapter quotesAdapter;
    private SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        Intent intent = getIntent();
        CATEGORY = intent.getStringExtra("category");

        recyclerView = findViewById(R.id.quotes_rv);
        progressBar = findViewById(R.id.main_progress);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);


        quoteService = QuotesApi.getClient().create(QuotesService.class);
        quoteService = QuotesApi.getClient().create(QuotesService.class);
        Call<ResultsApiQuotes> call = quoteService.getQuotes("tag",CATEGORY);
        call.enqueue(new Callback<ResultsApiQuotes>() {
            @Override
            public void onResponse(Call<ResultsApiQuotes> call, Response<ResultsApiQuotes> response) {

                if(response!=null){
                    List<Quotes> results = response.body().getQuotes();
                    quotesAdapter = new QuotesAdapter( results,getApplicationContext(), QuotesActivity.this);
                    recyclerView.setAdapter(quotesAdapter);
                }
                else {
                    Toast.makeText(QuotesActivity.this, "Error Loading data", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResultsApiQuotes> call, Throwable t) {
                Toast.makeText(QuotesActivity.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        }


}