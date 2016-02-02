package com.example.leon.phimovies.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.details_activity.DetailsActivity;
import com.example.leon.phimovies.retrofit.Movie;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 26.01.2016.
 */
public class PopularFragment extends Fragment implements MainView {

    private static final String KEY_MOVIE = "MOVIE";
    private static final String TAG = PopularFragment.class.getName();
    private MainPresenter mPresenter;
    private int mPage = 1;
    private GridLayoutManager mGridLayoutManager;
    @Bind(R.id.in_theaters_recycler_view)
    RecyclerView mRecyclerView;
    RecyclerGridAdapter mAdapter;
    private boolean mIsLoading = false;

    public static PopularFragment getInstance() {
        return new PopularFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_in_theaters, container, false);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        showDetails(mAdapter.getItem(position));
                    }

                    @Override
                    public void onItemLongPress(View childView, int position) {

                    }
                }
        ));

        mAdapter = new RecyclerGridAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new MainPresenter(this);
        mPresenter.loadMovies("popularity.desc", String.valueOf(mPage), "19ebd84dd0335ec8d6d277b2d60e9724");


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mGridLayoutManager.findLastVisibleItemPosition() > (mAdapter.getItemCount() / 2)
                        && !mIsLoading) {
                    mIsLoading = true;
                    mPresenter.loadMovies("popularity.desc", String.valueOf(++mPage), "19ebd84dd0335ec8d6d277b2d60e9724");
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }


    private void showDetails(Movie movie) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        Bundle args = new Bundle();
        args.putSerializable(KEY_MOVIE, movie);
        intent.putExtras(args);
        startActivity(intent);
    }

    @Override
    public void putData(List<Movie> movies) {
        mAdapter.addList(movies);
        mIsLoading = false;
    }
}
