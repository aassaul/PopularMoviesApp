package com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums;

/**
 * Created by Андрей on 28.06.2015.
 */
public enum ParameterType {
    SORT_BY("sort_by"),
    API_KEY("api_key");

    private final String name;

    ParameterType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
