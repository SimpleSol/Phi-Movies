package com.example.leon.phimovies.search_result_fragment;

import com.example.leon.phimovies.retrofit.Movie;

import java.util.List;

/**
 * Created by Тихон on 02.02.2016.
 */
public interface SearchResultView {

    void putSearchData(List<Movie> movies);

}
