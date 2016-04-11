package com.grability.appstore.models;

import com.google.gson.annotations.SerializedName;
import com.grability.appstore.models.ApplicationId;
import com.grability.appstore.models.ApplicationLink;
import com.grability.appstore.models.ApplicationRelease;
import com.grability.appstore.models.Artist;
import com.grability.appstore.models.Category;
import com.grability.appstore.models.GenericParameters;
import com.grability.appstore.models.Images;
import com.grability.appstore.models.Price;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationEntry {
    @SerializedName("im:name")
    private final GenericParameters name;
    @SerializedName("im:image")
    private final List<GenericParameters<Images>> imagesList;
    private final GenericParameters summary;
    @SerializedName("im:price")
    private final GenericParameters<Price> price;
    @SerializedName("im:contentType")
    private final GenericParameters rights;
    private final GenericParameters title;
    private final GenericParameters<ApplicationLink> link;
    private final GenericParameters<ApplicationId> id;
    @SerializedName("im:artist")
    private final GenericParameters<Artist> artist;
    private final GenericParameters<Category> category;
    @SerializedName("im:releaseDate")
    private final GenericParameters<ApplicationRelease> releaseDate;

    public ApplicationEntry(GenericParameters<Artist> artist, GenericParameters name, List<GenericParameters<Images>> imagesList, GenericParameters summary, GenericParameters<Price> price, GenericParameters rights, GenericParameters title, GenericParameters<ApplicationLink> link, GenericParameters<ApplicationId> id, GenericParameters<Category> category, GenericParameters<ApplicationRelease> releaseDate) {
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

    public GenericParameters<Artist> getArtist() {
        return artist;
    }

    public GenericParameters<Category> getCategory() {
        return category;
    }

    public GenericParameters<ApplicationId> getId() {
        return id;
    }

    public List<GenericParameters<Images>> getImagesList() {
        return imagesList;
    }

    public GenericParameters<ApplicationLink> getLink() {
        return link;
    }

    public GenericParameters getName() {
        return name;
    }

    public GenericParameters<Price> getPrice() {
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

    public String getBiggestAppImage(List<GenericParameters<Images>> imagesList ){
        return imagesList.get(imagesList.size() - 1).getLabel();
    }
}
