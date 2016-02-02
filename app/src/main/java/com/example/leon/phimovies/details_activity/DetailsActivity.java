package com.example.leon.phimovies.details_activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.retrofit.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Тихон on 02.02.2016.
 */
public class DetailsActivity extends AppCompatActivity implements DetailsView {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_details);

        ButterKnife.bind(this);

        mPresenter = new DetailsPresenter(this, this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(KEY_MOVIE)) {
            Bundle bundle = getIntent().getExtras();
            Movie movie = (Movie) bundle.getSerializable(KEY_MOVIE);
            mPresenter.initMovieData(movie);
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
