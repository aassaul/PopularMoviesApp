package com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.nanodegree.assaul.popularmoviesapp.R;
import com.nanodegree.assaul.popularmoviesapp.business.enums.RequestMethod;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.BaseTheMovieDBMethod;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.ParameterType;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums.SortBy;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.parameters.ApiKeyParameter;
import com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.parameters.SortByParameter;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Андрей on 28.06.2015.
 */
public class DiscoverMovieMethod extends BaseTheMovieDBMethod<ParameterType, List<MovieVO>> {

    public DiscoverMovieMethod(Resources resources) {
        super(RequestMethod.GET, resources, resources.getString(R.string.discover_movies), new ApiKeyParameter(), new SortByParameter());
    }

    public SortBy getSortBy() {
        return getSortByParameter().getValue();
    }

    public void setSortBy(SortBy sortBy) {
      getSortByParameter().setValue(sortBy);
    }

    @Override
    protected void onResult(@NonNull StringBuilder response) {
        try {
            JSONObject jsonObject = new JSONObject(response.toString());
            JSONArray results = jsonObject.getJSONArray("results");
            List<MovieVO> result = new ArrayList<>(results.length());
            for (int i = 0; i < results.length(); i++) {
                result.add(new MovieVO(results.getJSONObject(i)));
            }
            setResult(result);
        }catch (JSONException e){
            android.util.Log.w("DiscoverMovieMethod", e.toString());
        }
    }

    private SortByParameter getSortByParameter(){
        return (SortByParameter)getParameterForType(ParameterType.SORT_BY);
    }
}
