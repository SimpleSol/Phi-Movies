package com.example.leon.phimovies.notification_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.main_activity.MainActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Тихон on 04.02.2016.
 */
public class NotificationFragment extends Fragment {

    private int mCount;
    private MainActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fmt_notifications, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mCount = 0;
    }

    @OnClick(R.id.button_kinda_button)
    void onClick() {
        mActivity.increaseNotification(R.id.menu_item_notifications, String.valueOf(++mCount));
    }
}
