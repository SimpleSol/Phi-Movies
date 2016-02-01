package com.example.leon.phimovies.mvp;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.leon.phimovies.NetworkUtils;
import com.example.leon.phimovies.retrofit.MoviesService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

    public void loadMovie(String... params) {
        if (params.length == 2) { // loading popular movies
            mService.getMovies(params[0], params[1])
                    .flatMap(apiResponse -> {
                        Log.d(TAG, "call: " + Thread.currentThread().getName());
                        return Observable.just(apiResponse.getMovies());
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(movies -> {
                                mView.putData(movies);
                            },
                            throwable -> {

                                Log.d(TAG, "onError: " + Thread.currentThread().getName());
                                Log.e(TAG, "onError: " + throwable.getMessage(), throwable);
                            });

            Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    String string = "string";
//                    slghl
                    if (TextUtils.isEmpty(string)) {
                        subscriber.onError(new NullPointerException());
                    } else {
                        subscriber.onNext(string);
                    }
                    subscriber.onCompleted();
                }

            }).flatMap(new Func1<String, Observable<?>>() {
                @Override
                public Observable<?> call(String s) {
                    return null;
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Object>() {
                        @Override
                        public void call(Object o) {
                            Log.d(TAG, "call: ZBS");
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Log.d(TAG, "call: ERROR");
                        }
                    }, new Action0() {
                        @Override
                        public void call() {
                            //cursor.close();
                        }
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
