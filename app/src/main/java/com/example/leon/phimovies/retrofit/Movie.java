package com.example.leon.phimovies.retrofit;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Leon on 29.01.2016.
 */
public class Movie {

    @SerializedName("poster_path")
    private String mPoster;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("original_title")
    private String mTitle;

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    @SerializedName("vote_average")
    private String mRating;

    public String getPoster() {
        return mPoster;
    }

    public void setPoster(String poster) {
        mPoster = poster;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
