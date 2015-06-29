package com.nanodegree.assaul.popularmoviesapp.business.themoviedb;

import android.content.res.Resources;
import android.os.AsyncTask;

import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.DiscoverMovieMethod;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;
import com.nanodegree.assaul.popularmoviesapp.vo.MovieVO;

import java.io.IOException;
import java.util.List;

/**
 * Created by Андрей on 28.06.2015.
 */
public class ThemoviedbService {

    private final DiscoverMovieMethod discoverMovieMethod;
    private final AsyncTask<SortBy, Void, List<MovieVO>> discoverMovieTask;

    public ThemoviedbService(Resources resources) {
        discoverMovieMethod = new DiscoverMovieMethod(resources);
        discoverMovieTask = new AsyncTask<SortBy, Void, List<MovieVO>>() {
            @Override
            protected List<MovieVO> doInBackground(SortBy... params) {
                try {
                    discoverMovieMethod.setSortBy(params[0]);
                    discoverMovieMethod.run();
                }catch (IOException e){
                    android.util.Log.w("ThemoviedbService", e);
                }
                return discoverMovieMethod.getResult();
            }
            @Override
            protected void onPostExecute(List<MovieVO> result) {
                System.out.println();
            }
        };
    }

    public void getMovies(SortBy sortBy){
        discoverMovieTask.execute(sortBy);
    }
}
