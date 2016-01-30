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
public class InTheatersFragment extends BaseTabFragment {


    public static InTheatersFragment getInstance() {
        return new InTheatersFragment();
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

        // TODO: 30.01.2016 replace hardcoded date with actual date / remove hardcode in general
        mPresenter = new MainPresenter(this);
        mPresenter.loadMovie("2016-01-01",
                "2016-01-29",
                "19ebd84dd0335ec8d6d277b2d60e9724");
    }


}
