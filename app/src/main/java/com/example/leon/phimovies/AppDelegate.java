package com.example.leon.phimovies;

import android.app.Application;

import com.example.leon.phimovies.retrofit.ApiInterface;
import com.facebook.drawee.backends.pipeline.Fresco;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Leon on 26.01.2016.
 */
public class AppDelegate extends Application {

    private static ApiInterface apiInterface;

    public static ApiInterface getApiInterface() {
        return apiInterface;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext());

        apiInterface = new Retrofit.Builder()
                .baseUrl(ApiInterface.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface.class);
    }
}
