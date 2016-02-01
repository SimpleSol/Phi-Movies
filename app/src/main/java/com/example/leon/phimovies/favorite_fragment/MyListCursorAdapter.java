package com.example.leon.phimovies.favorite_fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.retrofit.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Leon on 26.12.2015.
 */
public class MyListCursorAdapter extends CursorRecyclerViewAdapter<MyListCursorAdapter.ViewHolder> {

    private final Context mContext;

    public MyListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mContext = context;
    }

    @Override
    public void onBindViewHolder(MyListCursorAdapter.ViewHolder viewHolder, Cursor cursor) {
        Movie movie = Movie.fromCursor(cursor);
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w300" + movie.getPoster());
        viewHolder.mPoster.setImageURI(uri);

        viewHolder.mTitle.setText(movie.getTitle());
        viewHolder.mRating.setText(movie.getRating());
        viewHolder.mReleaseDate.setText(movie.getReleaseDate());
    }

    @Override
    public MyListCursorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_favorite, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.favorite_poster)
        SimpleDraweeView mPoster;
        @Bind(R.id.favorite_title)
        TextView mTitle;
        @Bind(R.id.favorite_release_date)
        TextView mReleaseDate;
        @Bind(R.id.favorite_rating)
        TextView mRating;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}