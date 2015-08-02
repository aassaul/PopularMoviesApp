package com.nanodegree.assaul.popularmoviesapp.business.themoviedb;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.DiscoverMovieMethod;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
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
    @NonNull
    private final List<AsyncTask> currentTasks = new LinkedList<>();

    public ThemoviedbService(@NonNull Resources resources, @NonNull IThemoviedbServiceDelegate delegate) {
        discoverMovieMethod = new DiscoverMovieMethod(resources);
        this.delegate = new WeakReference<>(delegate);
    }

    public void getMovies(@NonNull SortBy sortBy) {
        stopTask(discoverMovieTask);
        discoverMovieTask = getGetMoviesTask();
        currentTasks.add(discoverMovieTask);
        discoverMovieTask.execute(sortBy);
    }

    public void stop() {
        for(AsyncTask task : currentTasks) {
            stopTask(task);
        }
    }

    @NonNull
    private AsyncTask<SortBy, Void, List<MovieVO>> getGetMoviesTask() {
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
                currentTasks.remove(discoverMovieTask);
                discoverMovieTask = null;
                delegate.get().onGetMoviesResult(result, sender);
            }
        };
    }

    private void stopTask(@Nullable AsyncTask task) {
        if(task != null && AsyncTask.Status.RUNNING.equals(task.getStatus())) {
            task.cancel(true);
        }
    }
}
