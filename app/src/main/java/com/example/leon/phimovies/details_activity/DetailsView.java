package com.example.leon.phimovies.details_activity;

import android.net.Uri;

import com.example.leon.phimovies.retrofit.Movie;

/**
 * @author Leon
 */
public interface DetailsView {

    void showMovieInfo(Uri uri, Movie movie);
}
