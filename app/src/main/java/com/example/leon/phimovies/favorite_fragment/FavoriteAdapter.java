package com.example.leon.phimovies.favorite_fragment;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.retrofit.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Тихон on 03.02.2016.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<Movie> mMovies = new ArrayList<>();

    public FavoriteAdapter() {

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie movie = mMovies.get(position);
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w300" + movie.getPoster());
        viewHolder.mPoster.setImageURI(uri);

        viewHolder.mTitle.setText(movie.getTitle());
        viewHolder.mRating.setText(movie.getRating());
        viewHolder.mReleaseDate.setText(movie.getReleaseDate());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) return 0;
        return mMovies.size();
    }

    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    public void swapList(List<Movie> newMovies) {
        if (newMovies != null) {
            mMovies = newMovies;
            notifyDataSetChanged();
        }
    }

    public void removeItem(int position) {
        mMovies.remove(position);
        notifyItemRemoved(position);
    }

    public void addItem(int position, Movie movie) {
        if (TextUtils.equals(movie.getIsShowing(), "true")) {
            mMovies.add(position, movie);
            notifyItemInserted(position);
        }
    }

    public void addList(List<Movie> newMovies) {
        if (newMovies != null) {
            mMovies.addAll(newMovies);
            notifyDataSetChanged();
        }
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
