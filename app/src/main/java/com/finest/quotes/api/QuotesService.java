package com.finest.quotes.api;

import com.finest.quotes.models.Quotes;
import com.finest.quotes.models.ResultsApiQuotes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuotesService {
    @GET("api/v1/all")
    Call<ResultsApiQuotes> getQuotes(
            @Query("type") String type,
            @Query("val") String val
    );
  //  https://goquotes-api.herokuapp.com/api/v1/all?type=&val=
    //https://goquotes-api.herokuapp.com/api/v1/all/quotes
    //https://goquotes-api.herokuapp.com/api/v1/all?type=tag&val=general
}
