package com.example.leon.phimovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 26.01.2016.
 */
public class PopularFragment extends Fragment implements MainContract{

    @Bind(R.id.in_theaters_recycler_view) RecyclerView mRecyclerView;

    private PopularPresenter mPresenter;

    public static PopularFragment getInstance() {
        return new PopularFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_in_theaters, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mPresenter = new PopularPresenter(this);
        mPresenter.loadMovie();
    }

    @Override
    public void putData(List<String> posters) {
        MainAdapter adapter = new MainAdapter(posters);
        mRecyclerView.setAdapter(adapter);
    }
}
