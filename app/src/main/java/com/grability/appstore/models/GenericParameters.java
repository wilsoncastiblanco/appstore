package com.grability.appstore.models;

/**
 * Created by wilson on 7/04/16.
 */
public class GenericParameters<T> {
    private  String label;
    private  T attributes;

    public GenericParameters(T attributes, String label) {
        this.attributes = attributes;
        this.label = label;
    }
}
