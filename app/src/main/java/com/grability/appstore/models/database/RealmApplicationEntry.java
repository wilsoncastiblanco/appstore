package com.grability.appstore.models.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wilson on 7/04/16.
 */
public class RealmApplicationEntry extends RealmObject{
    @PrimaryKey
    private String id;
    private String name;
    private RealmList<RealmImages> imagesList;
    private String summary;
    private RealmPrice price;
    private String rights;
    private String title;
    private String link;
    private RealmArtist artist;
    private RealmCategory category;
    private String releaseDate;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public RealmArtist getArtist() {
        return artist;
    }

    public void setArtist(RealmArtist artist) {
        this.artist = artist;
    }

    public RealmCategory getCategory() {
        return category;
    }

    public void setCategory(RealmCategory category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<RealmImages> getImagesList() {
        return imagesList;
    }

    public void setImagesList(RealmList<RealmImages> imagesList) {
        this.imagesList = imagesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmPrice getPrice() {
        return price;
    }

    public void setPrice(RealmPrice price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
