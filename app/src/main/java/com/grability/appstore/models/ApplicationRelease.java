package com.grability.appstore.models;

import io.realm.RealmObject;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationRelease{
    private final String label;

    public ApplicationRelease(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
