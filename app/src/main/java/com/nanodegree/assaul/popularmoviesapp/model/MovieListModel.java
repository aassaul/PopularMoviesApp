package com.nanodegree.assaul.popularmoviesapp.model;

import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrey Assaul on 02.08.2015.
 */
public class MovieListModel implements Serializable {
    private List<MovieVO> movies;
    private SortBy currentOrder = SortBy.POPULARITY_DESC;
    private MovieVO selectedMovie;

    public List<MovieVO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieVO> movies) {
        this.movies = movies;
    }

    public SortBy getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(SortBy currentOrder) {
        this.currentOrder = currentOrder;
    }

    public MovieVO getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(MovieVO selectedMovie) {
        this.selectedMovie = selectedMovie;
    }
}
