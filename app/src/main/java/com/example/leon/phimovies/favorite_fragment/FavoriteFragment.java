package com.example.leon.phimovies.favorite_fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.details_activity.DetailsActivity;
import com.example.leon.phimovies.retrofit.Movie;
import com.example.leon.phimovies.tabs.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Leon on 28.01.2016.
 */
public class FavoriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>,
        RecyclerItemClickListener.OnItemClickListener {

    private static final String KEY_MOVIE = "MOVIE";
    private static final String TAG = "Swipe Problems";
    @Bind(R.id.favorite_recycler_view)
    RecyclerView mRecyclerView;
    private FavoriteAdapter mAdapter;
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

        ActionBar toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (toolbar != null) {
            toolbar.setTitle("Favorite");
        }

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        getLoaderManager().initLoader(R.id.favorite_loader, Bundle.EMPTY, this);


        mAdapter = new FavoriteAdapter();
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Movie movie = mAdapter.getItem(position);
                String title = movie.getTitle();
                Context context = getActivity();
                mAdapter.removeItem(position);

                ContentValues values = new ContentValues();
                values.put(Movie.Columns.IS_SHOWING, "false");
//                if (cursor != null && cursor.moveToPosition(position)) {
                    context.getContentResolver().update(Movie.URI,
                            values,
                            Movie.Columns.TITLE + "=?", new String[]{title});
//                }

                Log.d(TAG, "Delete Item: " + String.valueOf(position));

                Snackbar.make(getView(), "Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mAdapter.addItem(position, movie);

                                ContentValues values = new ContentValues();
                                values.put(Movie.Columns.IS_SHOWING, "true");
//                                if (cursor != null && cursor.moveToPosition(position)) {
                                    context.getContentResolver().update(Movie.URI,
                                            values,
                                            Movie.Columns.TITLE + "=?", new String[]{title});
//                                }

                                Log.d(TAG, "Restore Item: " + String.valueOf(position));
                            }
                        })
                        .setCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                super.onDismissed(snackbar, event);
                                if (event != Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
//                                    if (cursor != null && cursor.moveToPosition(position)) {
//                                        context.getContentResolver().delete(Movie.URI,
//                                                Movie.Columns._ID + "=?",
//                                                new String[]{cursor.getString(cursor.getColumnIndex(Movie.Columns._ID))});
//                                        Log.d(TAG, "Delete from Database: " + String.valueOf(position));
//                                    }
                                }
                            }
                        })
                        .show();
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
            List<Movie> movies = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Movie movie = Movie.fromCursor(cursor);
                    if (TextUtils.equals(movie.getIsShowing(), "true")) {
                        movies.add(movie);
                        mAdapter.swapList(movies);
                    }
                } while (cursor.moveToNext());

            }
            Log.d(TAG, "onLoadFinished: ");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onItemClick(View childView, int position) {
        Cursor cursor = getActivity().getContentResolver().query(Movie.URI, null, null, null, null);
        if (cursor != null) {
            cursor.moveToPosition(position);
        }
        Movie movie = Movie.fromCursor(cursor);
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        Bundle args = new Bundle();
        args.putSerializable(KEY_MOVIE, movie);
        intent.putExtras(args);
        startActivity(intent);
    }

    @Override
    public void onItemLongPress(View childView, int position) {

    }
}
