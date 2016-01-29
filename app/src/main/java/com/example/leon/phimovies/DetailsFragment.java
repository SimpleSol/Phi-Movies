package com.example.leon.phimovies;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 29.01.2016.
 */
public class DetailsFragment extends Fragment {

    @Bind(R.id.details_poster) SimpleDraweeView mPoster;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w300/4oy4e0DP6LRwRszfx8NY8EYBj8V.jpg");
        mPoster.setImageURI(uri);
    }
}
