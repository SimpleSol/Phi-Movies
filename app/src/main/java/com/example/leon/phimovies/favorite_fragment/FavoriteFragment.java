package com.example.leon.phimovies.favorite_fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.details_fragment.DetailsFragment;
import com.example.leon.phimovies.retrofit.Movie;
import com.example.leon.phimovies.tabs.RecyclerItemClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 28.01.2016.
 */
public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
        RecyclerItemClickListener.OnItemClickListener {

    @Bind(R.id.favorite_recycler_view)
    RecyclerView mRecyclerView;
    private CursorRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_favorite, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getLoaderManager().initLoader(R.id.favorite_loader, Bundle.EMPTY, this);


        mAdapter = new MyListCursorAdapter(
                getActivity(),
                getActivity().getContentResolver().query(Movie.URI, null, null, null, null));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Movie movie = null;
                final Cursor cursor = getActivity().getContentResolver().query(Movie.URI, null, null, null, null);
                if (cursor != null && cursor.moveToPosition(position)) {
                    movie = Movie.fromCursor(cursor);
                    getActivity().getContentResolver().delete(Movie.URI, Movie.Columns._ID + "=?",
                            new String[]{cursor.getString(cursor.getColumnIndex(Movie.Columns._ID))});
                    cursor.close();
                }
                final Movie finalMovie = movie;
                Snackbar.make(getView(), "Deleted", Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (finalMovie != null) {
                            ContentValues values = new ContentValues();
                            values.put(Movie.Columns.TITLE, finalMovie.getTitle());
                            values.put(Movie.Columns.OVERVIEW, finalMovie.getOverview());
                            values.put(Movie.Columns.POSTER, finalMovie.getPoster());
                            values.put(Movie.Columns.RATING, finalMovie.getRating());
                            values.put(Movie.Columns.RELEASE_DATE, finalMovie.getReleaseDate());

                            getActivity().getContentResolver().insert(Movie.URI, values);
                        }
                    }
                })
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                            }
                        }).show();
            }
        });

        helper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), this));
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (id == R.id.favorite_loader) {
            return new FavoriteLoader(getActivity().getApplicationContext());
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (loader.getId() == R.id.favorite_loader) {
            mAdapter.changeCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (mAdapter != null) {
            mAdapter.changeCursor(null);
        }
    }

    @Override
    public void onItemClick(View childView, int position) {
        Cursor cursor = getActivity().getContentResolver().query(Movie.URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToPosition(position);
        }
        Movie movie = Movie.fromCursor(cursor);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(DetailsFragment.class.getName())
                .replace(R.id.container_view, DetailsFragment.getInstance(movie))
                .commit();
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
