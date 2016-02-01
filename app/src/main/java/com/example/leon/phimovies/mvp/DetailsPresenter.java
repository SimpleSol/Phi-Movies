package com.example.leon.phimovies.mvp;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.leon.phimovies.retrofit.Movie;

/**
 * @author Leon
 */
public class DetailsPresenter {

    private final DetailsView mView;

    private final Context mContext;

    @Nullable
    private Movie mMovie;

    public DetailsPresenter(@NonNull DetailsView view, @NonNull Context context) {
        mView = view;
        mContext = context;
    }

    public void saveToDataBase() {
        if (mMovie != null) {
            ContentValues values = new ContentValues();
            values.put(Movie.Columns.TITLE, mMovie.getTitle());
            values.put(Movie.Columns.OVERVIEW, mMovie.getOverview());
            values.put(Movie.Columns.POSTER, mMovie.getPoster());
            values.put(Movie.Columns.RATING, mMovie.getRating());
            values.put(Movie.Columns.RELEASE_DATE, mMovie.getReleaseDate());

            mContext.getContentResolver().insert(Movie.URI, values);
        }
    }

    public void initMovieData(Movie movie) {
        mMovie = movie;
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w300" + movie.getPoster());
        mView.showMovieInfo(uri, movie);
    }
}
