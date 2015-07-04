package com.nanodegree.assaul.popularmoviesapp.vo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Andrey Assaul on 29.06.2015.
 */
public class MovieVO {
    private final static String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    private final static DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault());

    public enum JSONField {
        ADULT,
        BACKDROP_PATH,
        GENRE_IDS,
        ID,
        ORIGINAL_LANGUAGE,
        ORIGINAL_TITLE,
        OVERVIEW,
        RELEASE_DATE,
        POSTER_PATH,
        POPULARITY,
        TITLE,
        VIDEO,
        VOTE_AVERAGE,
        VOTE_COUNT;

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private final boolean adult;
    private final String backdropPath;
    private final long[] genreIds;
    private final long id;
    private final String originalLanguage;
    private final String originalTitle;
    private final String overview;
    private final Date releaseDate;
    private final String posterPath;
    private final double popularity;
    private final String title;
    private final boolean video;
    private final double voteAverage;
    private final int voteCount;

    public MovieVO(@NonNull JSONObject json) throws JSONException{
        adult = json.getBoolean(JSONField.ADULT.toString());
        JSONArray genreIdsJSON = json.getJSONArray(JSONField.GENRE_IDS.toString());
        genreIds = new long[genreIdsJSON.length()];
        for (int i = 0; i < genreIds.length; i++) {
            genreIds[i] = genreIdsJSON.getLong(i);
        }
        backdropPath = json.getString(JSONField.BACKDROP_PATH.toString());
        id = json.getLong(JSONField.ID.toString());
        originalLanguage = json.getString(JSONField.ORIGINAL_LANGUAGE.toString());
        originalTitle = json.getString(JSONField.ORIGINAL_TITLE.toString());
        overview = json.getString(JSONField.OVERVIEW.toString());
        Date releaseDate = null;
        try {
            releaseDate = DATE_FORMAT.parse(json.getString(JSONField.RELEASE_DATE.toString()));
        }catch (ParseException e){
            releaseDate = null;
        } finally {
            this.releaseDate = releaseDate;
        }
        posterPath = json.getString(JSONField.POSTER_PATH.toString());
        popularity = json.getDouble(JSONField.POPULARITY.toString());
        title = json.getString(JSONField.TITLE.toString());
        video = json.getBoolean(JSONField.VIDEO.toString());
        voteAverage = json.getDouble(JSONField.VOTE_AVERAGE.toString());
        voteCount = json.getInt(JSONField.VOTE_COUNT.toString());
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public long[] getGenreIds() {
        return genreIds;
    }

    public long getId() {
        return id;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }


}


