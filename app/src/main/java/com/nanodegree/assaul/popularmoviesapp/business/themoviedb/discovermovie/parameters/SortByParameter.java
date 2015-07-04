package com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.parameters;

import android.support.annotation.Nullable;

import com.nanodegree.assaul.popularmoviesapp.business.BaseParameter;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.ParameterType;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;

/**
 * Created by Андрей on 28.06.2015.
 */
public class SortByParameter extends BaseParameter<ParameterType, SortBy> {
    public SortByParameter(@Nullable SortBy value) {
        super(ParameterType.SORT_BY, value);
    }

    public SortByParameter() {
        this(null);
    }
}
