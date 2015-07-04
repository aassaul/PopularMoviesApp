package com.nanodegree.assaul.popularmoviesapp.business.themoviedb;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanodegree.assaul.popularmoviesapp.business.BaseParameter;

/**
 * Created by Андрей on 28.06.2015.
 */
public abstract class BaseApiKeyParameter<T extends Enum> extends BaseParameter<T, String> {
    public BaseApiKeyParameter(@NonNull T type, @Nullable String value) {
        super(type, value);
    }
}
