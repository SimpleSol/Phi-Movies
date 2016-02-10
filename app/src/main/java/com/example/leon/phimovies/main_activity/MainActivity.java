package com.example.leon.phimovies.main_activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.favorite_fragment.FavoriteFragment;
import com.example.leon.phimovies.main_fragment.MainFragment;
import com.example.leon.phimovies.notification_fragment.NotificationFragment;
import com.example.leon.phimovies.search_result_fragment.SearchResultFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 25.01.2016.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private final int QUERY_DELAY = 1000;

    @Bind(R.id.search_view)
    MaterialSearchView mSearchView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.navigation)
    NavigationView mNavigationView;

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_view, new MainFragment())
                    .commit();
        }

        initToolbar();
        initSearchView();
        initNavigationView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView.setMenuItem(item);
        return true;
    }


    private void initToolbar() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    private void initSearchView() {
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            Handler handler = new Handler();

            @Override
            public boolean onQueryTextSubmit(String query) {
                handler.removeCallbacksAndMessages(null);
                searchMovies(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(() -> searchMovies(newText), QUERY_DELAY);
                return false;
            }
        });

    }

    private void searchMovies(String query) {
        if (!TextUtils.isEmpty(query)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_view, SearchResultFragment.getInstance(query))
                    .commit();
        }
    }

    private void initNavigationView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.view_navigation_open,
                R.string.view_navigation_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(item -> {
            mDrawerLayout.closeDrawers();
            switch (item.getItemId()) {
                case R.id.menu_item_favorite:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_view, new FavoriteFragment())
                            .commit();
                    break;
                case R.id.menu_item_home:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_view, new MainFragment())
                            .commit();
                    break;
                case R.id.menu_item_notifications:
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_view, new NotificationFragment())
                            .commit();
            }
            return true;
        });

    }

    public void increaseNotification(@IdRes int itemId, String count) {
        Menu menu = mNavigationView.getMenu();

        MenuItem item = menu.findItem(itemId);
        TextView textView = ((TextView) item.getActionView());
        if (textView != null) {
            textView.setText(count);
        }

    }


}
