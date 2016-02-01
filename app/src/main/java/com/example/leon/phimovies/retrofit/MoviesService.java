package com.example.leon.phimovies.retrofit;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Leon on 29.01.2016.
 */
public interface MoviesService {

    @GET("/3/discover/movie")
    Call<ApiResponse> getResponse(
            @Query("primary_release_date.gte") String dateGte,
            @Query("primary_release_date.lte") String dateLte,
            @Query("api_key") String apiKey);

    @GET("/3/discover/movie")
    Call<ApiResponse> getResponse(
            @Query("sort_by") String sortBy,
            @Query("api_key") String apiKey);

    @GET("/3/discover/movie")
    Observable<ApiResponse> getMovies(
            @Query("primary_release_date.gte") String dateGte,
            @Query("primary_release_date.lte") String dateLte,
            @Query("api_key") String apiKey
    );

    @GET("/3/discover/movie")
    Observable<ApiResponse> getMovies(
            @Query("sort_by") String sortBy,
            @Query("api_key") String apiKey);
}
