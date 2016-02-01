package com.example.leon.phimovies.main_fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.leon.phimovies.tabs.InTheatersFragment;
import com.example.leon.phimovies.tabs.PopularFragment;

/**
 * Created by Leon on 26.01.2016.
 */
public class MainAdapter extends FragmentPagerAdapter {

    private String[] mTabs;

    public MainAdapter(FragmentManager fm) {
        super(fm);
        mTabs = new String[]{
                "In Theaters",
                "Popular"
        };
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return InTheatersFragment.getInstance();
            case 1:
                return PopularFragment.getInstance();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

}
