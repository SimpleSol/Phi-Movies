package com.example.leon.phimovies;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Leon on 29.01.2016.
 */
public interface ApiInterface {

    String ENDPOINT = "https://api.themoviedb.org";

    @GET("/3/discover/movie")
    Call<ApiResponse> getResponse(
            @Query("primary_release_date.gte") String dateGte,
            @Query("primary_release_date.lte") String dateLte,
            @Query("api_key") String apiKey);

    @GET("/3/discover/movie")
    Call<ApiResponse> getResponse(
            @Query("sort_by") String sortBy,
            @Query("api_key") String apiKey);

}
