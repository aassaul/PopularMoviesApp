package com.nanodegree.assaul.popularmoviesapp.business;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Андрей on 28.06.2015.
 */
public abstract class BaseParameter<T extends Enum, V> {

    @NonNull
    private final T type;
    @Nullable
    private V value;

    public BaseParameter(@NonNull T type, @Nullable V value) {
        this.type = type;
        this.value = value;
    }

    @NonNull
    public final T getType() {
        return type;
    }

    @Nullable
    public V getValue() {
        return value;
    }

    public void setValue(@Nullable V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return type.toString()+"="+String.valueOf(value);
    }
}