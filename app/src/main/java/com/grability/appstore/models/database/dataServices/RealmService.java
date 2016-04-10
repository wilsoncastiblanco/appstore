package com.grability.appstore.models.database.dataServices;

import android.content.Context;

import com.grability.appstore.models.factories.ApplicationEntryFactory;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.Category;
import com.grability.appstore.models.database.RealmApplicationEntryFactory;
import com.grability.appstore.models.database.RealmApplicationEntry;
import com.grability.appstore.models.database.RealmCategory;
import com.grability.appstore.models.database.observables.RealmObservable;
import com.grability.appstore.models.factories.CategoryFactory;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by wilson on 8/04/16.
 */
public class RealmService implements IRealmService {
    private final Context context;

    public RealmService(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public Observable<List<ApplicationEntry>> applications() {
        return RealmObservable.results(context, new Func1<Realm, RealmResults<RealmApplicationEntry>>() {
            @Override
            public RealmResults<RealmApplicationEntry> call(Realm realm) {
                return realm.where(RealmApplicationEntry.class).findAll();
            }
        }).map(new Func1<RealmResults<RealmApplicationEntry>, List<ApplicationEntry>>() {
            @Override
            public List<ApplicationEntry> call(RealmResults<RealmApplicationEntry> realmApplicationEntries) {
                return ApplicationEntryFactory.getObjectListByRealmList(realmApplicationEntries);
            }
        });
    }

    @Override
    public Observable<List<ApplicationEntry>> applicationsByCategory(final String categoryId) {
        return RealmObservable.results(context, new Func1<Realm, RealmResults<RealmApplicationEntry>>() {
            @Override
            public RealmResults<RealmApplicationEntry> call(Realm realm) {
                return realm.where(RealmApplicationEntry.class).
                        equalTo("category.id", categoryId).
                        findAll();
            }
        }).map(new Func1<RealmResults<RealmApplicationEntry>, List<ApplicationEntry>>() {
            @Override
            public List<ApplicationEntry> call(RealmResults<RealmApplicationEntry> realmApplicationEntries) {
                return ApplicationEntryFactory.getObjectListByRealmList(realmApplicationEntries);
            }
        });
    }

    @Override
    public Observable<List<ApplicationEntry>> newApplications(final List<ApplicationEntry> applicationEntries) {
        return RealmObservable.list(context, new Func1<Realm, List<RealmApplicationEntry>>() {
            @Override
            public List<RealmApplicationEntry> call(Realm realm) {
                return RealmApplicationEntryFactory.getRealmObjectsByList(realm, applicationEntries);
            }
        }).map(new Func1<List<RealmApplicationEntry>, List<ApplicationEntry>>() {
            @Override
            public List<ApplicationEntry> call(List<RealmApplicationEntry> realmApplicationEntries) {
                return ApplicationEntryFactory.getObjectListByRealmList(realmApplicationEntries);
            }
        });
    }

    @Override
    public Observable<List<Category>> categories() {
        return RealmObservable.results(context, new Func1<Realm, RealmResults<RealmCategory>>() {
            @Override
            public RealmResults<RealmCategory> call(Realm realm) {
                return realm.where(RealmCategory.class).findAll();
            }
        }).map(new Func1<RealmResults<RealmCategory>, List<Category>>() {
            @Override
            public List<Category> call(RealmResults<RealmCategory> realmCategories) {
                return CategoryFactory.getObjectListByRealmList(realmCategories);
            }
        });
    }




}