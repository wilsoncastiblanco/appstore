package com.grability.appstore.models.factories;

import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.ApplicationId;
import com.grability.appstore.models.ApplicationLink;
import com.grability.appstore.models.ApplicationRelease;
import com.grability.appstore.models.Artist;
import com.grability.appstore.models.Category;
import com.grability.appstore.models.GenericParameters;
import com.grability.appstore.models.Images;
import com.grability.appstore.models.Price;
import com.grability.appstore.models.database.RealmApplicationEntry;
import com.grability.appstore.models.database.RealmImages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilson on 10/04/16.
 */
public class ApplicationEntryFactory {
    public static List<ApplicationEntry> getObjectListByRealmList(List<RealmApplicationEntry> realmApplicationEntries){
        List<ApplicationEntry> applicationEntryList = new ArrayList<>(realmApplicationEntries.size());
        for(RealmApplicationEntry realmApplicationEntry : realmApplicationEntries){
            applicationEntryList.add(getObjectByRealmObject(realmApplicationEntry));
        }
        return applicationEntryList;
    }

    public static ApplicationEntry getObjectByRealmObject(RealmApplicationEntry realmApplicationEntry){
        final GenericParameters name = new GenericParameters(realmApplicationEntry.getName());

        final List<GenericParameters<Images>>  imagesList = new ArrayList<>(realmApplicationEntry.getImagesList().size());
        for(RealmImages imagesRealm : realmApplicationEntry.getImagesList()){
            Images images = new Images(imagesRealm.getHeight());
            GenericParameters genericParameters = new GenericParameters<>(images, imagesRealm.getUrl());
            imagesList.add(genericParameters);
        }

        final GenericParameters summary = new GenericParameters(realmApplicationEntry.getSummary());

        Price applicationPrice = new Price(realmApplicationEntry.getPrice().getAmount(), realmApplicationEntry.getPrice().getCurrency());
        final GenericParameters<Price> price = new GenericParameters<>(applicationPrice);

        final GenericParameters rights = new GenericParameters(realmApplicationEntry.getRights());
        final GenericParameters title = new GenericParameters(realmApplicationEntry.getTitle());

        ApplicationLink applicationLink = new ApplicationLink(realmApplicationEntry.getLink());
        final GenericParameters<ApplicationLink> link = new GenericParameters<>(applicationLink);

        ApplicationId applicationId = new ApplicationId(realmApplicationEntry.getId());
        final GenericParameters<ApplicationId> id = new GenericParameters<>(applicationId);

        Artist applicationArtist = new Artist(realmApplicationEntry.getArtist().getArtistUrl());
        final GenericParameters<Artist> artist = new GenericParameters<>(applicationArtist, realmApplicationEntry.getArtist().getArtistName());

        Category applicationCategory = new Category(realmApplicationEntry.getCategory().getId(), realmApplicationEntry.getCategory().getTerm(), realmApplicationEntry.getCategory().getScheme(), realmApplicationEntry.getCategory().getLabel());
        final GenericParameters<Category> category = new GenericParameters<>(applicationCategory);

        ApplicationRelease applicationRelease = new ApplicationRelease(realmApplicationEntry.getReleaseDate());
        final GenericParameters<ApplicationRelease> releaseDate = new GenericParameters<>(applicationRelease);

        return new ApplicationEntry(artist, name, imagesList, summary, price, rights, title, link, id, category, releaseDate);
    }
}
