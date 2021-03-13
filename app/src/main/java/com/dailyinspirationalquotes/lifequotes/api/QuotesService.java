package com.dailyinspirationalquotes.lifequotes.api;

import com.dailyinspirationalquotes.lifequotes.models.Quotes;
import com.dailyinspirationalquotes.lifequotes.models.TotalQuotesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface QuotesService {

    @FormUrlEncoded
    @POST("api_quotes/")
    Call<List<Quotes>> getQuoteByCategory(
            @Field("cat") String category,
            @Field("key") String key
    );


    @FormUrlEncoded
    @POST("api_quotes/count")
    Call<TotalQuotesModel> getTotalQuotes(
            @Field("cat") String cat,
            @Field("key") String key
    );
}
