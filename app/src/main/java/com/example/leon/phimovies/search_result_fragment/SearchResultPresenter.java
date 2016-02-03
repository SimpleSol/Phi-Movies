package com.example.leon.phimovies.search_result_fragment;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.leon.phimovies.NetworkUtils;
import com.example.leon.phimovies.retrofit.ApiResults;
import com.example.leon.phimovies.retrofit.MoviesService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Тихон on 02.02.2016.
 */
public class SearchResultPresenter {

    private static final String TAG = SearchResultFragment.class.getName();
    private final MoviesService mService;

    private SearchResultView mView;

    public SearchResultPresenter(@NonNull SearchResultView view) {
        mView = view;
        mService = NetworkUtils.getInstance().getService(MoviesService.class);
    }

    public void loadingSearchResults(String query, String page, String apiKey) {
        Log.d(TAG, "loadingSearchResults: query");
        Call<ApiResults> call = mService.getSearchResult(query, page, apiKey);
        call.enqueue(new Callback<ApiResults>() {
            @Override
            public void onResponse(Response<ApiResults> response, Retrofit retrofit) {
                ApiResults searchResults = response.body();
                mView.putSearchData(searchResults.getMovies());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
