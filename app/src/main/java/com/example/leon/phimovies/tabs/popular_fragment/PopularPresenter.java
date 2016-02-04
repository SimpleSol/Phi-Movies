package com.example.leon.phimovies.tabs.popular_fragment;

import android.support.annotation.NonNull;

import com.example.leon.phimovies.NetworkUtils;
import com.example.leon.phimovies.retrofit.ApiResults;
import com.example.leon.phimovies.retrofit.MoviesService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author Leon
 */
public class PopularPresenter {

    private final MoviesService mService;

    private PopularView mView;

    public PopularPresenter(@NonNull PopularView view) {
        mView = view;
        mService = NetworkUtils.getInstance().getService(MoviesService.class);
    }

    public void loadPopularResults(String sortBy, String page, String apiKey) {
        Call<ApiResults> call = mService.getPopular(sortBy, page, apiKey);
        call.enqueue(new Callback<ApiResults>() {
            @Override
            public void onResponse(Response<ApiResults> response, Retrofit retrofit) {
                ApiResults popularResults = response.body();
                mView.putPopularData(popularResults.getMovies());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
