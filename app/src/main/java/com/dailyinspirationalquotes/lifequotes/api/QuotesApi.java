package com.dailyinspirationalquotes.lifequotes.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class QuotesApi {

    public static final String BASE_URL = "https://hadisol.com/";
    private static QuotesApi mInstance;
    private static Retrofit retrofit;

    private QuotesApi() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(20, TimeUnit.MINUTES)
                .writeTimeout(20, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized QuotesApi getInstance() {
        if (mInstance == null) {
            mInstance = new QuotesApi();
        }
        return mInstance;
    }

    public QuotesService getApi() {
        return retrofit.create(QuotesService.class);
    }
}

