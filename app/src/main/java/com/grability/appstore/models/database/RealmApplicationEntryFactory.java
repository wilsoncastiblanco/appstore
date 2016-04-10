package com.grability.appstore.models.database;

import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.GenericParameters;
import com.grability.appstore.models.Images;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by wilson on 10/04/16.
 */
public class RealmApplicationEntryFactory {

    public static List<RealmApplicationEntry> getRealmObjectsByList(Realm realm, List<ApplicationEntry> applicationEntries){
        List<RealmApplicationEntry> realmApplicationEntries = new ArrayList<>(applicationEntries.size());

        for(ApplicationEntry applicationEntry : applicationEntries){
            RealmApplicationEntry realmApplicationEntry = getRealmObject(realm, applicationEntry);
            realmApplicationEntries.add(realmApplicationEntry);
        }

        return realmApplicationEntries;
    }

    public static RealmApplicationEntry getRealmObject(Realm realm, ApplicationEntry applicationEntry){
        final RealmList<RealmImages> imagesApplicationRealm = new RealmList<>();
        for (GenericParameters<Images> image : applicationEntry.getImagesList()) {
            RealmImages imageApplicationRealm = new RealmImages();
            imageApplicationRealm.setHeight(image.getAttributes().getHeight());
            imageApplicationRealm.setUrl(image.getLabel());
            imagesApplicationRealm.add(imageApplicationRealm);
        }

        final RealmArtist artistApplicationRealm = new RealmArtist();
        artistApplicationRealm.setArtistName(applicationEntry.getArtist().getLabel());
        artistApplicationRealm.setArtistUrl(applicationEntry.getArtist().getAttributes().getHref());

        final RealmPrice priceApplicationRealm = new RealmPrice();
        priceApplicationRealm.setAmount(applicationEntry.getPrice().getAttributes().getAmount());
        priceApplicationRealm.setCurrency(applicationEntry.getPrice().getAttributes().getCurrency());

        final RealmCategory categoryRealm = new RealmCategory();
        categoryRealm.setId(applicationEntry.getCategory().getAttributes().getId());
        categoryRealm.setLabel(applicationEntry.getCategory().getAttributes().getLabel());
        categoryRealm.setScheme(applicationEntry.getCategory().getAttributes().getScheme());
        categoryRealm.setTerm(applicationEntry.getCategory().getAttributes().getTerm());


        RealmList<RealmImages> imagesApplication = new RealmList<>();
        for (RealmImages imagesRealm : imagesApplicationRealm) {
            imagesApplication.add(realm.copyToRealm(imagesRealm));
        }

        RealmArtist artistApplication = realm.copyToRealm(artistApplicationRealm);
        RealmPrice priceApplication = realm.copyToRealm(priceApplicationRealm);
        RealmCategory categoryApplication = realm.copyToRealm(categoryRealm);


        // create RealmIssue instance and save it
        RealmApplicationEntry realmApplicationEntry = new RealmApplicationEntry();
        realmApplicationEntry.setId(applicationEntry.getId().getAttributes().getId());
        realmApplicationEntry.setArtist(artistApplication);
        realmApplicationEntry.setCategory(categoryApplication);
        realmApplicationEntry.setImagesList(imagesApplication);
        realmApplicationEntry.setLink(applicationEntry.getLink().getAttributes().getHref());
        realmApplicationEntry.setName(applicationEntry.getName().getLabel());
        realmApplicationEntry.setPrice(priceApplication);
        realmApplicationEntry.setReleaseDate(applicationEntry.getReleaseDate().getAttributes().getLabel());
        realmApplicationEntry.setRights(applicationEntry.getRights().getLabel());
        realmApplicationEntry.setSummary(applicationEntry.getSummary().getLabel());
        realmApplicationEntry.setTitle(applicationEntry.getTitle().getLabel());

        return realm.copyToRealm(realmApplicationEntry);
    }

}
