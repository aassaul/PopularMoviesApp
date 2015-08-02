package com.nanodegree.assaul.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.assaul.popularmoviesapp.R;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;
import com.squareup.picasso.Picasso;

/**
 * Created by Andrey Assaul on 06.07.2015.
 */
public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_DATA = "MOVIE_DATA";

    private MovieVO movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initData();
        initContent();
    }

    private void initData() {
        if(movie == null){
            movie = (MovieVO)getIntent().getSerializableExtra(MOVIE_DATA);
        }
    }

    private void initContent() {
        Picasso picasso = Picasso.with(this);
        TextView movieNameTextView = (TextView)findViewById(R.id.movieNemaTextView);
        movieNameTextView.setText(movie.getTitle());
        picasso.load(getBaseUrl()+movie.getPosterPath()).into((ImageView)findViewById(R.id.movieImageView));
    }

    private String getBaseUrl(){
        return getString(R.string.themoviedb_image_base_url)+"154/";
    }
}
