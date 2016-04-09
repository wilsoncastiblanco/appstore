package com.grability.appstore.models;

import io.realm.RealmObject;

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

    public String getHref() {
        return href;
    }

    public String getRel() {
        return rel;
    }

    public String getType() {
        return type;
    }
}
