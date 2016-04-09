package com.grability.appstore.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;

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
    private final GenericParameters rights;
    private final GenericParameters title;
    private final GenericParameters<ApplicationLink> link;
    private final GenericParameters<ApplicationId> id;
    @SerializedName("im:artist")
    private final GenericParameters<ApplicationArtist> artist;
    private final GenericParameters<Category> category;
    @SerializedName("im:releaseDate")
    private final GenericParameters<ApplicationRelease> releaseDate;

    public ApplicationEntry(GenericParameters<ApplicationArtist> artist, GenericParameters name, List<GenericParameters<ApplicationImages>> imagesList, GenericParameters summary, GenericParameters<ApplicationPrice> price, GenericParameters rights, GenericParameters title, GenericParameters<ApplicationLink> link, GenericParameters<ApplicationId> id, GenericParameters<Category> category, GenericParameters<ApplicationRelease> releaseDate) {
        this.artist = artist;
        this.name = name;
        this.imagesList = imagesList;
        this.summary = summary;
        this.price = price;
        this.rights = rights;
        this.title = title;
        this.link = link;
        this.id = id;
        this.category = category;
        this.releaseDate = releaseDate;
    }

    public GenericParameters<ApplicationArtist> getArtist() {
        return artist;
    }

    public GenericParameters<Category> getCategory() {
        return category;
    }

    public GenericParameters<ApplicationId> getId() {
        return id;
    }

    public List<GenericParameters<ApplicationImages>> getImagesList() {
        return imagesList;
    }

    public GenericParameters<ApplicationLink> getLink() {
        return link;
    }

    public GenericParameters getName() {
        return name;
    }

    public GenericParameters<ApplicationPrice> getPrice() {
        return price;
    }

    public GenericParameters<ApplicationRelease> getReleaseDate() {
        return releaseDate;
    }

    public GenericParameters getRights() {
        return rights;
    }

    public GenericParameters getSummary() {
        return summary;
    }

    public GenericParameters getTitle() {
        return title;
    }
}
