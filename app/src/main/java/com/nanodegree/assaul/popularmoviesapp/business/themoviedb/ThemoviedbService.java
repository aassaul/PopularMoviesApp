package com.nanodegree.assaul.popularmoviesapp.business.themoviedb;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.DiscoverMovieMethod;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Андрей on 28.06.2015.
 */
public class ThemoviedbService {
    @NonNull
    private final WeakReference<IThemoviedbServiceDelegate> delegate;
    @NonNull
    private final DiscoverMovieMethod discoverMovieMethod;
    @Nullable
    private AsyncTask<SortBy, Void, List<MovieVO>> discoverMovieTask;

    public ThemoviedbService(@NonNull Resources resources, @NonNull IThemoviedbServiceDelegate delegate) {
        discoverMovieMethod = new DiscoverMovieMethod(resources);
        this.delegate = new WeakReference<>(delegate);
    }

    public void getMovies(@NonNull SortBy sortBy){
        if (discoverMovieTask != null && AsyncTask.Status.RUNNING.equals(discoverMovieTask.getStatus())){
            discoverMovieTask.cancel(true);
        }
        discoverMovieTask = getGetMoviesTask();
        discoverMovieTask.execute(sortBy);
    }

    @NonNull
    private AsyncTask<SortBy, Void, List<MovieVO>> getGetMoviesTask(){
        final ThemoviedbService sender = this;
        return new AsyncTask<SortBy, Void, List<MovieVO>>() {
            @Override
            protected List<MovieVO> doInBackground(SortBy... params) {
                discoverMovieMethod.setSortBy(params[0]);
                discoverMovieMethod.run();
                return discoverMovieMethod.getResult();
            }
            @Override
            protected void onPostExecute(List<MovieVO> result) {
                delegate.get().onGetMoviesResult(result, sender);
            }
        };
    }
}
