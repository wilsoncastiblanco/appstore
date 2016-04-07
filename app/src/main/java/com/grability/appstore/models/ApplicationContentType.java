package com.grability.appstore.models;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationContentType {
    private final String term;
    private final String label;

    public ApplicationContentType(String label, String term) {
        this.label = label;
        this.term = term;
    }
}
