package com.example.leon.phimovies.search_result_fragment;

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
import com.example.leon.phimovies.tabs.RecyclerGridAdapter;
import com.example.leon.phimovies.tabs.RecyclerItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Тихон on 02.02.2016.
 */
public class SearchResultFragment extends Fragment implements SearchResultView{

    private static final String KEY_MOVIE = "MOVIE";
    private static final String QUERY = "QUERY";
    private SearchResultPresenter mPresenter;
    private int mPage = 1;
    private GridLayoutManager mGridLayoutManager;
    @Bind(R.id.in_theaters_recycler_view)
    RecyclerView mRecyclerView;
    RecyclerGridAdapter mAdapter;
    private boolean mIsLoading = false;

    public static SearchResultFragment getInstance(String query) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString(QUERY, query);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_grid_layout, container, false);

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
        mPresenter = new SearchResultPresenter(this);
        mPresenter.loadingSearchResults(getArguments().getString(QUERY), String.valueOf(mPage), "19ebd84dd0335ec8d6d277b2d60e9724");


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mGridLayoutManager.findLastVisibleItemPosition() > (mAdapter.getItemCount() / 2)
                        && !mIsLoading) {
                    mIsLoading = true;
                    mPresenter.loadingSearchResults(getArguments().getString(QUERY), String.valueOf(++mPage), "19ebd84dd0335ec8d6d277b2d60e9724");
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
    public void putSearchData(List<Movie> movies) {
        mAdapter.addList(movies);
        mIsLoading = false;
    }

}
