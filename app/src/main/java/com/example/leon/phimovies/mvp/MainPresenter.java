package com.example.leon.phimovies.mvp;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.leon.phimovies.NetworkUtils;
import com.example.leon.phimovies.retrofit.MoviesService;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Leon
 */
public class MainPresenter {

    private static final String TAG = MainPresenter.class.getName();
    private final MoviesService mService;

    private MainContract mView;

    public MainPresenter(@NonNull MainContract view) {
        mView = view;
        mService = NetworkUtils.getInstance().getService(MoviesService.class);
    }

    public void loadMovie(String... params) {
        if (params.length == 2) { // loading popular movies
            mService.getMovies(params[0], params[1])
                    .flatMap(apiResponse -> {
                        Log.d(TAG, "call: " + Thread.currentThread().getName());
                        return Observable.just(apiResponse.getMovies());
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((posters) -> {
                                mView.putData(posters);
                            },
                            throwable -> {
                                Log.d(TAG, "onError: " + Thread.currentThread().getName());
                                Log.e(TAG, "onError: " + throwable.getMessage(), throwable);
                            });
//            Call<ApiResponse> call = AppDelegate.getMoviesService().getResponse(
//                    params[0],
//                    params[1]
//            );
//            call.enqueue(new Callback<ApiResponse>() {
//                @Override
//                public void onResponse(Response<ApiResponse> response, Retrofit retrofit) {
//                    ApiResponse inTheaters = response.body();
//                    mView.putData(inTheaters.getMovies());
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//
//                }
//            });
        } else if (params.length == 3) { //loading in theater movies
            mService.getMovies(params[0], params[1], params[2])
                    .flatMap(apiResponse ->
                            Observable.just(apiResponse.getMovies()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(movies -> {
                        mView.putData(movies);
                    }, throwable -> {
                        Log.e(TAG, "call: " + throwable.getMessage(), throwable);
                    });
//            Call<ApiResponse> call = AppDelegate.getMoviesService().getResponse(
//                    params[0],
//                    params[1],
//                    params[2]
//            );
//            call.enqueue(new Callback<ApiResponse>() {
//                @Override
//                public void onResponse(Response<ApiResponse> response, Retrofit retrofit) {
//                    ApiResponse inTheaters = response.body();
//                    mView.putData(inTheaters.getMovies());
//                }
//
//                @Override
//                public void onFailure(Throwable t) {
//
//                }
//            });
        }
    }

}
