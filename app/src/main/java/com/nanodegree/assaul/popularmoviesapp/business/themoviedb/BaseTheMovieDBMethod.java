package com.nanodegree.assaul.popularmoviesapp.business.themoviedb;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanodegree.assaul.popularmoviesapp.R;
import com.nanodegree.assaul.popularmoviesapp.business.BaseMethod;
import com.nanodegree.assaul.popularmoviesapp.business.BaseParameter;
import com.nanodegree.assaul.popularmoviesapp.business.enums.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Андрей on 28.06.2015.
 */
public abstract class BaseTheMovieDBMethod<T extends Enum, Result> extends BaseMethod<T, Result> {

    private static <T extends Enum> List<BaseParameter<T, ?>> getParams(@NonNull BaseApiKeyParameter<T> apiKeyParameter, @NonNull String key,  BaseParameter<T, ?>[]parameters){
        apiKeyParameter.setValue(key);
        List<BaseParameter<T, ?>> params = new ArrayList<>();
        params.add(apiKeyParameter);
        params.addAll(Arrays.asList(parameters));
        return params;
    }

    public BaseTheMovieDBMethod(@NonNull RequestMethod requestMethod, @NonNull Resources resources, @Nullable String methodUrl, @NonNull BaseApiKeyParameter<T> apiKeyParameter, BaseParameter<T, ?>... parameters) {
        super(requestMethod, resources.getString(R.string.themoviedb_base_url) + methodUrl, getParams(apiKeyParameter, resources.getString(R.string.themoviedb_api_key), parameters));
    }
}
