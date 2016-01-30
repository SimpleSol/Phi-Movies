package com.example.leon.phimovies.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.leon.phimovies.R;
import com.example.leon.phimovies.favorite_fragment.FavoriteFragment;
import com.example.leon.phimovies.main_fragment.MainFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Leon on 25.01.2016.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.search_view) MaterialSearchView mSearchView;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.drawer_layout) DrawerLayout mDrawerLayout;

    @Bind(R.id.navigation) NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
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
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

//        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//
//            }
//        });
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
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.favorite:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_view, new FavoriteFragment())
                                .commit();
                        break;
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container_view, new MainFragment())
                                .commit();
                        break;
                }
                return true;
            }
        });

    }

}
