package com.nanodegree.assaul.popularmoviesapp.business.themoviedb.discovermovie.enums;

import com.nanodegree.assaul.popularmoviesapp.business.enums.SortOrder;

/**
 * Created by Андрей on 28.06.2015.
 */
public enum SortBy {
    POPULARITY_ASC("popularity", SortOrder.ASC),
    POPULARITY_DESC("popularity", SortOrder.DESC),

    RELEASE_DATE_ASC("release_date", SortOrder.ASC),
    RELEASE_DATE_DESC("release_date", SortOrder.DESC),

    REVENUE_ASC("revenue", SortOrder.ASC),
    REVENUE_DESC("revenue", SortOrder.DESC),

    ORIGINAL_TITLE_ASC("original_title", SortOrder.ASC),
    ORIGINAL_TITLE_DESC("original_title", SortOrder.DESC),

    VOTE_AVERAGE_ASC("vote_average", SortOrder.ASC),
    VOTE_AVERAGE_DESC("vote_average", SortOrder.DESC),

    VOTE_COUNT_ASC("vote_count", SortOrder.ASC),
    VOTE_COUNT_DESC("vote_count", SortOrder.DESC);

    private final String name;
    private final SortOrder order;

    SortBy(String name, SortOrder order) {
        this.name = name;
        this.order = order;
    }

    @Override
    public String toString() {
        return name+"."+order.toString();
    }
}
