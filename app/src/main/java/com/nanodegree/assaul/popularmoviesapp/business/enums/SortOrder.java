package com.nanodegree.assaul.popularmoviesapp.business.enums;

import android.support.annotation.NonNull;

/**
 * Created by Андрей on 28.06.2015.
 */
public enum SortOrder {
    ASC("asc"),
    DESC("desc");

    private final String name;

    SortOrder(@NonNull String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
