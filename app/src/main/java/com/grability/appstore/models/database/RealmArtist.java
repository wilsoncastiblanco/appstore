package com.grability.appstore.models.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wilson on 7/04/16.
 */
public class RealmArtist extends RealmObject {
    @PrimaryKey
    private String artistName;
    private String artistUrl;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistUrl() {
        return artistUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }
}
