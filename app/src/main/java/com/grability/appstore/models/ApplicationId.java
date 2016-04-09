package com.grability.appstore.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationId {
    @SerializedName("im:id")
    private final String id;

    public ApplicationId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
