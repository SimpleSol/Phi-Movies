package com.example.leon.phimovies.favorite_fragment;

import android.content.Context;
import android.support.v4.content.CursorLoader;

import com.example.leon.phimovies.retrofit.Movie;

/**
 * @author Leon
 */
public class FavoriteLoader extends CursorLoader {
    public FavoriteLoader(Context context) {
        super(context, Movie.URI, null, null, null, null);
    }
}
