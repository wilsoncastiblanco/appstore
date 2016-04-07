package com.grability.appstore.models;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationLink {
    private final String rel;
    private final String type;
    private final String href;

    public ApplicationLink(String href, String rel, String type) {
        this.href = href;
        this.rel = rel;
        this.type = type;
    }
}
