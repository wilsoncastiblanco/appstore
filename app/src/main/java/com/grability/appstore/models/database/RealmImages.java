package com.grability.appstore.models.database;

import io.realm.RealmObject;

/**
 * Created by wilson on 7/04/16.
 */
public class RealmImages extends RealmObject {
    private String url;
    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
