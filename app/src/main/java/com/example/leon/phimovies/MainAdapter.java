package com.example.leon.phimovies;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Leon on 26.01.2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private String[] mTestUris;

    public MainAdapter(String[] testUris) {
        mTestUris = testUris;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fresco_simple_drawee_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Uri uri = Uri.parse(mTestUris[position]);
        holder.mDraweeView.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mTestUris.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mDraweeView;
        public ViewHolder(View itemView) {
            super(itemView);
            mDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.simple_drawee_view);
        }
    }
}
