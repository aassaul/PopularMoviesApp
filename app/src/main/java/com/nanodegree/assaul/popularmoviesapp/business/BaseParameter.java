package com.nanodegree.assaul.popularmoviesapp.business;

/**
 * Created by Андрей on 28.06.2015.
 */
public abstract class BaseParameter<T extends Enum, V> {

    private final T type;
    private V value;

    public BaseParameter(T type, V value) {
        this.type = type;
        this.value = value;
    }

    public T getType() {
        return type;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return type.toString()+"="+value.toString();
    }
}
