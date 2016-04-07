package com.grability.appstore.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wilson on 7/04/16.
 */
public class Category {
    @SerializedName("im:id")
    private final String id;
    private final String term;
    private final String scheme;
    private final String label;

    public Category(String id, String term, String scheme, String label) {
        this.id = id;
        this.term = term;
        this.scheme = scheme;
        this.label = label;
    }
}
