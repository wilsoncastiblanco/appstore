package com.grability.appstore.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

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

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getScheme() {
        return scheme;
    }

    public String getTerm() {
        return term;
    }
}
