package com.grability.appstore.models.database.dataServices;

import android.content.Context;

import com.grability.appstore.models.ApplicationArtist;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.ApplicationId;
import com.grability.appstore.models.ApplicationImages;
import com.grability.appstore.models.ApplicationLink;
import com.grability.appstore.models.ApplicationPrice;
import com.grability.appstore.models.ApplicationRelease;
import com.grability.appstore.models.Category;
import com.grability.appstore.models.GenericParameters;
import com.grability.appstore.models.database.ApplicationArtistRealm;
import com.grability.appstore.models.database.ApplicationEntryRealm;
import com.grability.appstore.models.database.ApplicationImagesRealm;
import com.grability.appstore.models.database.ApplicationLinkRealm;
import com.grability.appstore.models.database.ApplicationPriceRealm;
import com.grability.appstore.models.database.CategoryRealm;
import com.grability.appstore.models.database.observables.RealmObservable;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by wilson on 8/04/16.
 */
public class RealmDataService implements DataService {
    private final Context context;

    public RealmDataService(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<List<ApplicationEntry>> applications() {
        return RealmObservable.results(context, new Func1<Realm, RealmResults<ApplicationEntryRealm>>() {
            @Override
            public RealmResults<ApplicationEntryRealm> call(Realm realm) {
                // find all issues
                return realm.where(ApplicationEntryRealm.class).findAll();
            }
        }).map(new Func1<RealmResults<ApplicationEntryRealm>, List<ApplicationEntry>>() {
            @Override
            public List<ApplicationEntry> call(RealmResults<ApplicationEntryRealm> realmIssues) {
                // map them to UI objects
                final List<ApplicationEntry> issues = new ArrayList<>(realmIssues.size());
                for (ApplicationEntryRealm realmIssue : realmIssues) {
                    issues.add(applicationFromRealm(realmIssue));
                }
                return issues;
            }
        });
    }

    @Override
    public Observable<ApplicationEntry> newApplication(final GenericParameters<ApplicationArtist> artist,
                                                       final GenericParameters name,
                                                       final List<GenericParameters<ApplicationImages>> imagesList,
                                                       final GenericParameters summary,
                                                       final GenericParameters<ApplicationPrice> price,
                                                       final GenericParameters rights,
                                                       final GenericParameters title,
                                                       final GenericParameters<ApplicationLink> link,
                                                       final GenericParameters<ApplicationId> id,
                                                       final GenericParameters<Category> category,
                                                       final GenericParameters<ApplicationRelease> releaseDate) {

        final RealmList<ApplicationImagesRealm> imagesApplicationRealm = new RealmList<>();
        for (GenericParameters<ApplicationImages> image : imagesList) {
            ApplicationImagesRealm imageApplicationRealm = new ApplicationImagesRealm();
            imageApplicationRealm.setHeight(image.getAttributes().getHeight());
            imageApplicationRealm.setUrl(image.getLabel());
            imagesApplicationRealm.add(imageApplicationRealm);
        }

        final ApplicationArtistRealm artistApplicationRealm = new ApplicationArtistRealm();
        artistApplicationRealm.setArtistName(artist.getLabel());
        artistApplicationRealm.setArtistUrl(artist.getAttributes().getHref());

        final ApplicationPriceRealm priceApplicationRealm = new ApplicationPriceRealm();
        priceApplicationRealm.setAmount(price.getAttributes().getAmount());
        priceApplicationRealm.setCurrency(price.getAttributes().getCurrency());

        final ApplicationLinkRealm linkApplicationRealm = new ApplicationLinkRealm();
        linkApplicationRealm.setHref(link.getAttributes().getHref());
        linkApplicationRealm.setRel(link.getAttributes().getRel());
        linkApplicationRealm.setType(link.getAttributes().getType());

        final CategoryRealm categoryRealm = new CategoryRealm();
        categoryRealm.setId(category.getAttributes().getId());
        categoryRealm.setLabel(category.getAttributes().getLabel());
        categoryRealm.setScheme(category.getAttributes().getScheme());
        categoryRealm.setTerm(category.getAttributes().getTerm());



        return RealmObservable.object(context, new Func1<Realm, ApplicationEntryRealm>() {
            @Override
            public ApplicationEntryRealm call(Realm realm) {
                // internal object instances are not created by realm
                // saving them using copyToRealm returning instance associated with realm
                RealmList<ApplicationImagesRealm> imagesApplication = new RealmList<ApplicationImagesRealm>();
                for (ApplicationImagesRealm imagesRealm : imagesApplicationRealm) {
                    imagesApplication.add(realm.copyToRealm(imagesRealm));
                }

                ApplicationArtistRealm artistApplication = realm.copyToRealm(artistApplicationRealm);
                ApplicationPriceRealm priceApplication = realm.copyToRealm(priceApplicationRealm);
                ApplicationLinkRealm linkApplication = realm.copyToRealm(linkApplicationRealm);
                CategoryRealm categoryApplication = realm.copyToRealm(categoryRealm);


                // create RealmIssue instance and save it
                ApplicationEntryRealm application = new ApplicationEntryRealm();
                application.setId(id.getAttributes().getId());
                application.setArtist(artistApplication);
                application.setCategory(categoryApplication);
                application.setImagesList(imagesApplication);
                application.setLink(linkApplication);
                application.setName(name.getLabel());
                application.setPrice(priceApplication);
                application.setReleaseDate(releaseDate.getAttributes().getLabel());
                application.setRights(rights.getLabel());
                application.setSummary(summary.getLabel());
                application.setTitle(title.getLabel());
                return realm.copyToRealm(application);
            }
        }).map(new Func1<ApplicationEntryRealm, ApplicationEntry>() {
            @Override
            public ApplicationEntry call(ApplicationEntryRealm applicationEntryRealm) {
                // map to UI object
                return applicationFromRealm(applicationEntryRealm);
            }
        });
    }

    private static ApplicationEntry applicationFromRealm(ApplicationEntryRealm applicationEntryRealm) {
        final GenericParameters name = new GenericParameters(applicationEntryRealm.getName());

        final List<GenericParameters<ApplicationImages>>  imagesList = new ArrayList<>(applicationEntryRealm.getImagesList().size());
        for(ApplicationImagesRealm imagesRealm : applicationEntryRealm.getImagesList()){
            ApplicationImages applicationImages = new ApplicationImages(imagesRealm.getHeight());
            GenericParameters genericParameters = new GenericParameters<>(applicationImages, imagesRealm.getUrl());
            imagesList.add(genericParameters);
        }

        final GenericParameters summary = new GenericParameters(applicationEntryRealm.getSummary());

        ApplicationPrice applicationPrice = new ApplicationPrice(applicationEntryRealm.getPrice().getAmount(), applicationEntryRealm.getPrice().getCurrency());
        final GenericParameters<ApplicationPrice> price = new GenericParameters<>(applicationPrice);

        final GenericParameters rights = new GenericParameters(applicationEntryRealm.getRights());
        final GenericParameters title = new GenericParameters(applicationEntryRealm.getTitle());

        ApplicationLink applicationLink = new ApplicationLink(applicationEntryRealm.getLink().getHref(), applicationEntryRealm.getLink().getType(), applicationEntryRealm.getLink().getRel());
        final GenericParameters<ApplicationLink> link = new GenericParameters<>(applicationLink);

        ApplicationId applicationId = new ApplicationId(applicationEntryRealm.getId());
        final GenericParameters<ApplicationId> id = new GenericParameters<>(applicationId);

        ApplicationArtist applicationArtist = new ApplicationArtist(applicationEntryRealm.getArtist().getArtistUrl());
        final GenericParameters<ApplicationArtist> artist = new GenericParameters<>(applicationArtist, applicationEntryRealm.getArtist().getArtistName());

        Category applicationCategory = new Category(applicationEntryRealm.getCategory().getId(), applicationEntryRealm.getCategory().getTerm(), applicationEntryRealm.getCategory().getScheme(), applicationEntryRealm.getCategory().getLabel());
        final GenericParameters<Category> category = new GenericParameters<>(applicationCategory);

        ApplicationRelease applicationRelease = new ApplicationRelease(applicationEntryRealm.getReleaseDate());
        final GenericParameters<ApplicationRelease> releaseDate = new GenericParameters<>(applicationRelease);

        return new ApplicationEntry(artist, name, imagesList, summary, price, rights, title, link, id, category, releaseDate);
    }

}