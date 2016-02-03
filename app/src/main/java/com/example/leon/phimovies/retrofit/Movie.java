package com.example.leon.phimovies.retrofit;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import com.example.leon.phimovies.sqlite.SQLiteProvider;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Leon on 29.01.2016.
 */
public class Movie implements Serializable {

    public static final String TABLE = "movies";

    public static final Uri URI = Uri.parse("content://" + SQLiteProvider.AUTHORITY + "/" + TABLE);

    @SerializedName("poster_path")
    private String mPoster;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("original_title")
    private String mTitle;
    @SerializedName("vote_average")
    private String mRating;

    private String isShowing;

    public String getIsShowing() {
        return isShowing;
    }

    public void setIsShowing(String isShowing) {
        this.isShowing = isShowing;
    }

    public static Movie fromCursor(Cursor cursor) {
        Movie movie = new Movie();
        movie.setTitle(cursor.getString(cursor.getColumnIndex(Columns.TITLE)));
        movie.setPoster(cursor.getString(cursor.getColumnIndex(Columns.POSTER)));
        movie.setRating(cursor.getString(cursor.getColumnIndex(Columns.RATING)));
        movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(Columns.RELEASE_DATE)));
        movie.setOverview(cursor.getString(cursor.getColumnIndex(Columns.OVERVIEW)));
        movie.setIsShowing(cursor.getString(cursor.getColumnIndex(Columns.IS_SHOWING)));
        return movie;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

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

    public interface Columns extends BaseColumns {
        String TITLE = "title";
        String RATING = "rating";
        String OVERVIEW = "overview";
        String POSTER = "poster";
        String RELEASE_DATE = "release_date";
        String IS_SHOWING = "is_shoving";
    }
}
