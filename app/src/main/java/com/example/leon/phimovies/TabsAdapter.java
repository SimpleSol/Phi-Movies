package com.example.leon.phimovies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Leon on 26.01.2016.
 */
public class TabsAdapter extends FragmentPagerAdapter{

    private String[] mTabs;

    public TabsAdapter(FragmentManager fm) {
        super(fm);
        mTabs = new String[] {
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
