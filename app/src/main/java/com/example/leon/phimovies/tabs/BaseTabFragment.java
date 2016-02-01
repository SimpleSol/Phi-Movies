package com.example.leon.phimovies.tabs;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.details_fragment.DetailsFragment;
import com.example.leon.phimovies.mvp.MainPresenter;
import com.example.leon.phimovies.mvp.MainView;
import com.example.leon.phimovies.retrofit.Movie;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Leon on 30.01.2016.
 */
public class BaseTabFragment extends Fragment implements MainView {

    protected MainPresenter mPresenter;
    protected List<Movie> mMovies;
    @Bind(R.id.in_theaters_recycler_view)
    RecyclerView mRecyclerView;
    protected int mPage = 1;

    protected void showDetails(List<Movie> movies, int position) {
        Movie movie = movies.get(position);
        getActivity().getSupportFragmentManager().beginTransaction()
                .addToBackStack(DetailsFragment.class.getName())
                .replace(R.id.container_view, DetailsFragment.getInstance(movie))
                .commit();
    }

    @Override
    public void putData(List<Movie> movies) {
        mMovies = movies;
        RecyclerAdapter adapter = new RecyclerAdapter(movies);
        mRecyclerView.setAdapter(adapter);
    }

}
