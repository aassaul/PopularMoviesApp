package com.nanodegree.assaul.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.nanodegree.assaul.popularmoviesapp.R;
import com.nanodegree.assaul.popularmoviesapp.adapter.MovieListAdapter;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.IThemoviedbServiceDelegate;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.ThemoviedbService;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IThemoviedbServiceDelegate {

    private static final Map<Integer, SortBy> menuItemToSortByMap = new HashMap<>();

    private GridView moviesGridView;

    private ThemoviedbService themoviedbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        themoviedbService = new ThemoviedbService(getResources(), this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMoviesGridView(R.id.moviesGridView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menuItemToSortByMap.put(R.id.most_popular_sort, SortBy.POPULARITY_DESC);
        menuItemToSortByMap.put(R.id.highest_rated_sort, SortBy.VOTE_AVERAGE_DESC);
        handleSortSettings(menu.findItem(R.id.most_popular_sort));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return handleSortSettings(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetMoviesResult(@Nullable List<MovieVO> movies, @NonNull ThemoviedbService sender) {
        moviesGridView.setAdapter(new MovieListAdapter(this,movies));
    }

    private boolean handleSortSettings(@NonNull MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.highest_rated_sort:
            case R.id.most_popular_sort:
                item.setChecked(true);
                themoviedbService.getMovies(menuItemToSortByMap.get(id));
                return true;
            default: return false;
        }
    }

    private void initMoviesGridView(@IdRes int id){
        moviesGridView = (GridView)findViewById(id);
    }
}
