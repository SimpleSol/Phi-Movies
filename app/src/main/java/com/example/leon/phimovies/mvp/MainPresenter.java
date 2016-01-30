package com.example.leon.phimovies.mvp;

import com.example.leon.phimovies.AppDelegate;
import com.example.leon.phimovies.retrofit.ApiResponse;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Leon on 29.01.2016.
 */
public class MainPresenter {

    private MainContract mView;

    public MainPresenter(MainContract view) {
        mView = view;
    }

    public void loadMovie(String... params) {
        if (params.length == 2) { // loading popular movies
            Call<ApiResponse> call = AppDelegate.getApiInterface().getResponse(
                    params[0],
                    params[1]
            );
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Response<ApiResponse> response, Retrofit retrofit) {
                    ApiResponse inTheaters = response.body();
                    mView.putData(inTheaters.getMovies());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } else if (params.length == 3){ //loading in theater movies
            Call<ApiResponse> call = AppDelegate.getApiInterface().getResponse(
                    params[0],
                    params[1],
                    params[2]
            );
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Response<ApiResponse> response, Retrofit retrofit) {
                    ApiResponse inTheaters = response.body();
                    mView.putData(inTheaters.getMovies());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

}
