package com.grability.appstore.models.database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by wilson on 7/04/16.
 */
public class ApplicationEntryRealm extends RealmObject{
    @PrimaryKey
    private String id;
    private String name;
    private RealmList<ApplicationImagesRealm> imagesList;
    private String summary;
    private ApplicationPriceRealm price;
    private String rights;
    private String title;
    private ApplicationLinkRealm link;
    private ApplicationArtistRealm artist;
    private CategoryRealm category;
    private String releaseDate;

    public ApplicationArtistRealm getArtist() {
        return artist;
    }

    public void setArtist(ApplicationArtistRealm artist) {
        this.artist = artist;
    }

    public CategoryRealm getCategory() {
        return category;
    }

    public void setCategory(CategoryRealm category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<ApplicationImagesRealm> getImagesList() {
        return imagesList;
    }

    public void setImagesList(RealmList<ApplicationImagesRealm> imagesList) {
        this.imagesList = imagesList;
    }

    public ApplicationLinkRealm getLink() {
        return link;
    }

    public void setLink(ApplicationLinkRealm link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApplicationPriceRealm getPrice() {
        return price;
    }

    public void setPrice(ApplicationPriceRealm price) {
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
