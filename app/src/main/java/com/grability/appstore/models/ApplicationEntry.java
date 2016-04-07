package com.grability.appstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationEntry {
    @SerializedName("im:name")
    private final GenericParameters name;
    @SerializedName("im:image")
    private final List<GenericParameters<ApplicationImages>> imagesList;
    private final GenericParameters summary;
    @SerializedName("im:price")
    private final GenericParameters<ApplicationPrice> price;
    @SerializedName("im:contentType")
    private final GenericParameters<ApplicationContentType> contentType;
    private final GenericParameters rights;
    private final GenericParameters title;
    private final GenericParameters<ApplicationLink> link;
    private final GenericParameters<ApplicationId> id;
    @SerializedName("im:artist")
    private final GenericParameters<ApplicationArtist> artist;
    private final GenericParameters<Category> category;
    @SerializedName("im:releaseDate")
    private final GenericParameters<ApplicationRelease> releaseDate;

    public ApplicationEntry(GenericParameters<ApplicationArtist> artist, GenericParameters name, List<GenericParameters<ApplicationImages>> imagesList, GenericParameters summary, GenericParameters<ApplicationPrice> price, GenericParameters<ApplicationContentType> contentType, GenericParameters rights, GenericParameters title, GenericParameters<ApplicationLink> link, GenericParameters<ApplicationId> id, GenericParameters<Category> category, GenericParameters<ApplicationRelease> releaseDate) {
        this.artist = artist;
        this.name = name;
        this.imagesList = imagesList;
        this.summary = summary;
        this.price = price;
        this.contentType = contentType;
        this.rights = rights;
        this.title = title;
        this.link = link;
        this.id = id;
        this.category = category;
        this.releaseDate = releaseDate;
    }
}
