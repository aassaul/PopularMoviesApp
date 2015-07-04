package com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.parameters;

import android.support.annotation.NonNull;

import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.BaseApiKeyParameter;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.ParameterType;

/**
 * Created by Андрей on 28.06.2015.
 */
public class ApiKeyParameter extends BaseApiKeyParameter<ParameterType> {
    public ApiKeyParameter(String value) {
        super(ParameterType.API_KEY, value);
    }

    public ApiKeyParameter() {
        this(null);
    }
}
