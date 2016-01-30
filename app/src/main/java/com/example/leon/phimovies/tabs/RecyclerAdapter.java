package com.example.leon.phimovies.tabs;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.retrofit.Movie;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Leon on 26.01.2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Movie> mMovies;

    public RecyclerAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Uri uri = Uri.parse("http://image.tmdb.org/t/p/w300" + mMovies.get(position).getPoster());
        holder.mDraweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            mDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.simple_drawee_view);
        }
    }
}
