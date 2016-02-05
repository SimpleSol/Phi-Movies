package com.example.leon.phimovies.tabs;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.retrofit.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 26.01.2016.
 */
public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.ViewHolder> {

    private List<Movie> mMovies;

    public RecyclerGridAdapter() {
        mMovies = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w300" + mMovies.get(position).getPoster());
        viewHolder.mPoster.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        if (mMovies == null) return 0;
        return mMovies.size();
    }

    public void addList(List<Movie> newMovies) {
        if (newMovies != null) {
            mMovies.addAll(newMovies);
            notifyDataSetChanged();
        }
    }

    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.simple_drawee_view)
        SimpleDraweeView mPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
