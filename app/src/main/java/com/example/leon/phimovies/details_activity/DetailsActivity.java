package com.example.leon.phimovies.details_activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
    @Bind(R.id.toolbar_details)
    Toolbar mToolbar;
    private DetailsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppDefault);
        setContentView(R.layout.ac_details);

        ButterKnife.bind(this);

        mPresenter = new DetailsPresenter(this, this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(KEY_MOVIE)) {
            Bundle bundle = getIntent().getExtras();
            Movie movie = (Movie) bundle.getSerializable(KEY_MOVIE);
            initToolbar(movie != null ? movie.getTitle() : getString(R.string.app_name));
            mPresenter.initMovieData(movie);
        }
    }

    private void initToolbar(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            setSupportActionBar(mToolbar);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
                supportActionBar.setDisplayShowHomeEnabled(true);
                supportActionBar.setHomeButtonEnabled(true);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button_add)
    public void onClick() {
        mPresenter.saveToDataBase();
        finish();
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
