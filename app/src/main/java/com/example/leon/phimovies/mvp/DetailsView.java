package com.example.leon.phimovies.mvp;

import android.net.Uri;

import com.example.leon.phimovies.retrofit.Movie;

/**
 * @author Leon
 */
public interface DetailsView {

    void showMovieInfo(Uri uri, Movie movie);
}
