package com.example.leon.phimovies.tabs.in_theaters_fragment;

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
public class InTheatersPresenter {

    private final MoviesService mService;

    private InTheatersView mView;

    public InTheatersPresenter(@NonNull InTheatersView view) {
        mView = view;
        mService = NetworkUtils.getInstance().getService(MoviesService.class);
    }

    public void loadInTheatersResults(String dateGte, String dateLte, String page, String apiKey) {
            Call<ApiResults> call = mService.getInTheaters(dateGte, dateLte, page, apiKey);
            call.enqueue(new Callback<ApiResults>() {
                @Override
                public void onResponse(Response<ApiResults> response, Retrofit retrofit) {
                    ApiResults inTheatersResults = response.body();
                    mView.putInTheatersData(inTheatersResults.getMovies());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
    }

}
