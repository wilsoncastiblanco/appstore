package com.grability.appstore.models;

/**
 * Created by wilson on 7/04/16.
 */
public class GenericParameters<T> {
    private final String label;
    private final T attributes;

    public GenericParameters(T attributes, String label) {
        this.attributes = attributes;
        this.label = label;
    }
}
