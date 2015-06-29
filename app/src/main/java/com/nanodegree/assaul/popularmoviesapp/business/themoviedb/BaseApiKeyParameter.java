package com.nanodegree.assaul.popularmoviesapp.business.themoviedb;

import com.nanodegree.assaul.popularmoviesapp.business.BaseParameter;

/**
 * Created by Андрей on 28.06.2015.
 */
public abstract class BaseApiKeyParameter<T extends Enum> extends BaseParameter<T, String> {
    public BaseApiKeyParameter(T type, String value) {
        super(type, value);
    }
}
