package com.example.leon.phimovies.mvp;

import com.example.leon.phimovies.retrofit.Movie;

import java.util.List;

/**
 * Created by Leon on 29.01.2016.
 */
public interface MainContract {

    void putData(List<Movie> posters);

}
