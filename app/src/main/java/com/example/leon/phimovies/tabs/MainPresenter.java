package com.example.leon.phimovies.tabs;

import android.support.annotation.NonNull;

import com.example.leon.phimovies.NetworkUtils;
import com.example.leon.phimovies.retrofit.ApiResponse;
import com.example.leon.phimovies.retrofit.MoviesService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * @author Leon
 */
public class MainPresenter {

    private static final String TAG = MainPresenter.class.getName();
    private final MoviesService mService;

    private MainView mView;

    public MainPresenter(@NonNull MainView view) {
        mView = view;
        mService = NetworkUtils.getInstance().getService(MoviesService.class);
    }

    public void loadMovies(String... params) {
        if (params.length == 3) { // loading popular movies
            Call<ApiResponse> call = mService.getResponse(
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
        } else if (params.length == 4) { //loading in theater movies
            Call<ApiResponse> call = mService.getResponse(
                    params[0],
                    params[1],
                    params[2],
                    params[3]
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
