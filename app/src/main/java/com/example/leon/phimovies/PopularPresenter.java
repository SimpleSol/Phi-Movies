package com.example.leon.phimovies;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Leon on 29.01.2016.
 */
public class PopularPresenter {

    private MainContract mView;

    public PopularPresenter(MainContract view) {
        mView = view;
    }

    public void loadMovie() {
        Call<ApiResponse> call = AppDelegate.getApiInterface().getResponse(
                "popularity.desc",
                "19ebd84dd0335ec8d6d277b2d60e9724"
        );
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Response<ApiResponse> response, Retrofit retrofit) {
                ApiResponse inTheaters = response.body();
                final List<Movie> movies = inTheaters.getMovies();
                List<String> posters = new ArrayList<>();
                for (Movie movie : movies) {
                    posters.add(movie.getPoster());
                }
                mView.putData(posters);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
