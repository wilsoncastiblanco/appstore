package com.grability.appstore.models;


/**
 * Created by wilson on 7/04/16.
 */
public class GenericParameters<T>{
    private String label;
    private T attributes;

    public GenericParameters(T attributes, String label) {
        this.attributes = attributes;
        this.label = label;
    }

    public GenericParameters(String label){
        this.label = label;
    }

    public GenericParameters(T attributes){
        this.attributes = attributes;
    }

    public T getAttributes() {
        return attributes;
    }

    public String getLabel() {
        return label;
    }
}
