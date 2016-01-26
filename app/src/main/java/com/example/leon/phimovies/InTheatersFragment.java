package com.example.leon.phimovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Leon on 26.01.2016.
 */
public class InTheatersFragment extends Fragment {

    private String[] mTestUris = {
            "http://st.kp.yandex.net/images/film_iphone/iphone360_713051.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_838856.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_585585.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_782792.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_586380.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_817679.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_782932.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_522941.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_673157.jpg",
            "http://st.kp.yandex.net/images/film_iphone/iphone360_819101.jpg"
    };

    private View mView;

    public static InTheatersFragment getInstance() {
        return new InTheatersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fmt_in_theaters_layout, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.in_theaters_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        MainAdapter adapter = new MainAdapter(mTestUris);
        recyclerView.setAdapter(adapter);
    }
}
