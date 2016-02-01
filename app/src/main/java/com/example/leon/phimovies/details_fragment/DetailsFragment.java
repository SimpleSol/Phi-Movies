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
import com.example.leon.phimovies.mvp.DetailsPresenter;
import com.example.leon.phimovies.mvp.DetailsView;
import com.example.leon.phimovies.retrofit.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 29.01.2016.
 */
public class DetailsFragment extends Fragment implements DetailsView {

    private static final String KEY_MOVIE = "MOVIE";
    @Bind(R.id.details_poster)
    SimpleDraweeView mPoster;
    @Bind(R.id.details_overview)
    TextView mOverview;
    @Bind(R.id.details_rating)
    TextView mRating;
    @Bind(R.id.details_release_date)
    TextView mReleaseDate;
    @Bind(R.id.details_title)
    TextView mTitle;
    private DetailsPresenter mPresenter;

    public static DetailsFragment getInstance(Movie movie) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new DetailsPresenter(this, getActivity());
        return inflater.inflate(R.layout.fmt_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (getArguments() != null && getArguments().containsKey(KEY_MOVIE)) {
            mPresenter.initMovieData((Movie) getArguments().getSerializable(KEY_MOVIE));
        }

    }


    @OnClick(R.id.button_add)
    public void onClick() {
        mPresenter.saveToDataBase();
    }

    @Override
    public void showMovieInfo(Uri uri, Movie movie) {
        mPoster.setImageURI(uri);
        mOverview.setText(movie.getOverview());
        mRating.setText(movie.getRating());
        mTitle.append(movie.getTitle());
        mReleaseDate.append(movie.getReleaseDate());
    }
}
