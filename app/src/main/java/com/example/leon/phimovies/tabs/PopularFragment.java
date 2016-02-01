package com.example.leon.phimovies.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.mvp.MainPresenter;

import butterknife.ButterKnife;

/**
 * Created by Leon on 26.01.2016.
 */
public class PopularFragment extends BaseTabFragment {

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

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        showDetails(mMovies, position);
                    }

                    @Override
                    public void onItemLongPress(View childView, int position) {

                    }
                }
        ));

        mPresenter = new MainPresenter(this);
        mPresenter.loadMovie("popularity.desc", String.valueOf(mPage), "19ebd84dd0335ec8d6d277b2d60e9724");
    }


}
