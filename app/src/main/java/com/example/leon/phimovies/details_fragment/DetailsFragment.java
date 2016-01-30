package com.example.leon.phimovies.details_fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.retrofit.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 29.01.2016.
 */
public class DetailsFragment extends Fragment {

    @Bind(R.id.details_poster) SimpleDraweeView mPoster;
    @Bind(R.id.details_overview) TextView mOverview;
    @Bind(R.id.details_rating) TextView mRating;
    @Bind(R.id.details_release_date) TextView mReleaseDate;
    @Bind(R.id.details_title) TextView mTitle;

    private Movie mMovie;

    public static DetailsFragment getInstance(Movie movie){
        DetailsFragment fragment = new DetailsFragment();
        fragment.mMovie = movie;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w300" + mMovie.getPoster());
        mPoster.setImageURI(uri);

        mOverview.setText(mMovie.getOverview());
        mRating.setText(mMovie.getRating());
        mTitle.append(mMovie.getTitle());
        mReleaseDate.append(mMovie.getReleaseDate());
    }
}
