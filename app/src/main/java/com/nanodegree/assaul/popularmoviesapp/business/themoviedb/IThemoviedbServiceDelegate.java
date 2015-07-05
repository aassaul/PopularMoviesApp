package com.nanodegree.assaul.popularmoviesapp.business.themoviedb;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;

import java.util.List;

/**
 * Created by Andrey Assaul on 05.07.2015.
 */
public interface IThemoviedbServiceDelegate {
    void onGetMoviesResult(@Nullable List<MovieVO> movies, @NonNull ThemoviedbService sender);
}
