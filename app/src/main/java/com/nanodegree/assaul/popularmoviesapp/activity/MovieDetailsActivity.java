package com.nanodegree.assaul.popularmoviesapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.assaul.popularmoviesapp.R;
import com.nanodegree.assaul.popularmoviesapp.model.MovieVO;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Andrey Assaul on 06.07.2015.
 */
public class MovieDetailsActivity extends AppCompatActivity {
    private final static String DATE_FORMAT_PATTERN = "yyyy";
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault());

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
        picasso.load(getBaseUrl()+movie.getPosterPath()).into((ImageView)findViewById(R.id.movieImageView));
        ((TextView)findViewById(R.id.movieNameTextView)).setText(movie.getTitle());
        ((TextView)findViewById(R.id.movieSynopsisTextView)).setText(movie.getOverview());
        ((TextView)findViewById(R.id.movieVoteTextView)).setText(String.valueOf(movie.getVoteAverage()) + "/10");
        ((TextView)findViewById(R.id.movieYaerTextView)).setText(DATE_FORMAT.format(movie.getReleaseDate()));
    }

    private String getBaseUrl(){
        return getString(R.string.themoviedb_image_base_url)+"185/";
    }
}
