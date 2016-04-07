package com.grability.appstore.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationId {
    @SerializedName("im:id")
    private final String id;
    @SerializedName("im:bundleId")
    private final String bundle;

    public ApplicationId(String bundle, String id) {
        this.bundle = bundle;
        this.id = id;
    }
}
