package com.nanodegree.assaul.popularmoviesapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.nanodegree.assaul.popularmoviesapp.R;
import com.nanodegree.assaul.popularmoviesapp.adapter.MovieListAdapter;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.IThemoviedbServiceDelegate;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.ThemoviedbService;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;
import com.nanodegree.assaul.popularmoviesapp.model.MovieListModel;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IThemoviedbServiceDelegate {

    private static final String MOVIE_LIST_MODEL = "MOVIE_LIST_MODEL";
    private static final Map<Integer, SortBy> menuItemToSortByMap;
    private static final Map<SortBy, Integer> sortByToMenuItemMap;

    static {
        menuItemToSortByMap = new HashMap<>();
        menuItemToSortByMap.put(R.id.most_popular_sort, SortBy.POPULARITY_DESC);
        menuItemToSortByMap.put(R.id.highest_rated_sort, SortBy.VOTE_AVERAGE_DESC);

        sortByToMenuItemMap = new HashMap<>();
        sortByToMenuItemMap.put(SortBy.POPULARITY_DESC, R.id.most_popular_sort);
        sortByToMenuItemMap.put(SortBy.VOTE_AVERAGE_DESC, R.id.highest_rated_sort);
    }

    private GridView moviesGridView;

    private ThemoviedbService themoviedbService;

    private MovieListModel model;

    private SortBy currentOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initModel(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMoviesGridView(R.id.moviesGridView);
        completeCreation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setSortSettings(menu.findItem(sortByToMenuItemMap.get(model.getCurrentOrder())));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(setSortSettings(item) && currentOrder != model.getCurrentOrder()) {
            themoviedbService.getMovies(currentOrder);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetMoviesResult(@Nullable List<MovieVO> movies, @NonNull ThemoviedbService sender) {
        model.setMovies(movies);
        model.setCurrentOrder(currentOrder);
        moviesGridView.setAdapter(new MovieListAdapter(this, model.getMovies()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        themoviedbService.stop();
        super.onSaveInstanceState(outState);
        outState.putSerializable(MOVIE_LIST_MODEL, model);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return model;
    }

    private void completeCreation() {
        themoviedbService = new ThemoviedbService(getResources(), this);
        if(model.getMovies() == null) {
            currentOrder = model.getCurrentOrder();
            themoviedbService.getMovies(currentOrder);
        }
    }

    private void initModel(Bundle savedInstanceState) {
        model = (MovieListModel)getLastCustomNonConfigurationInstance();
        if(model == null && savedInstanceState != null) {
            model = (MovieListModel)savedInstanceState.getSerializable(MOVIE_LIST_MODEL);
        }
        if(model == null){
            model = new MovieListModel();
        }
    }

    private boolean setSortSettings(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.highest_rated_sort:
            case R.id.most_popular_sort:
                item.setChecked(true);
                currentOrder = menuItemToSortByMap.get(id);
                return true;
            default:
                return false;
        }
    }

    private void initMoviesGridView(@IdRes int id) {
        moviesGridView = (GridView) findViewById(id);
        moviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovieVO movie = (MovieVO) (parent.getAdapter().getItem(position));
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra(MovieDetailsActivity.MOVIE_DATA, movie);
                startActivity(intent);
            }
        });
        if(model.getMovies() != null){
            moviesGridView.setAdapter(new MovieListAdapter(this, model.getMovies()));
        }
    }
}
