package com.nanodegree.assaul.popularmoviesapp.business.enums;

/**
 * Created by Андрей on 28.06.2015.
 */
public enum SortOrder {
    ASC("asc"),
    DESC("desc");

    private final String name;

    SortOrder(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
