package com.nanodegree.assaul.popularmoviesapp.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nanodegree.assaul.popularmoviesapp.R;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Andrey Assaul on 05.07.2015.
 */
public class MovieListAdapter extends BaseAdapter {
    @Nullable
    private final List<MovieVO> movies;
    @IntRange(from = 0)
    private final int count;
    @NonNull
    private final LayoutInflater layoutInflater;
    @NonNull
    private final Picasso picasso;
    @NonNull
    private final String baseContentUrl;

    public MovieListAdapter(@NonNull Context context, @Nullable List<MovieVO> movies) {
        this.movies = movies;
        this.count = movies!=null?movies.size():0;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.picasso = Picasso.with(context);
        int columnWidth = context.getResources().getDimensionPixelOffset(R.dimen.movies_grid_column_width);
        this.baseContentUrl = context.getString(R.string.themoviedb_image_base_url)+String.valueOf(columnWidth)+"/";
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    @Nullable
    public Object getItem(int position) {
        return (movies != null)?movies.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        MovieVO movieVO = (MovieVO)getItem(position);
        return (movieVO != null)?movieVO.getId():-1;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        MovieVO movieVO = (MovieVO)getItem(position);
        if (movieVO == null) return  null;
        ImageView view = (ImageView)((convertView == null)?layoutInflater.inflate(R.layout.grid_item_movie, parent, false):convertView);
        view.setContentDescription(movieVO.getTitle());
        picasso.load(baseContentUrl+movieVO.getPosterPath()).into(view);
        return view;
    }
}