package com.grability.appstore.models.database.observables;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by wilson on 8/04/16.
 */
public final class RealmObservable {
    private RealmObservable() {
    }

    public static <T extends RealmObject> Observable<T> object(Context context, final Func1<Realm, T> function) {
        return Observable.create(new OnSubscribeRealm<T>(context) {
            @Override
            public T get(Realm realm) {
                return function.call(realm);
            }
        });
    }

    public static <T extends RealmObject> Observable<List<T>> list(Context context, final Func1<Realm, List<T>> function) {
        return Observable.create(new OnSubscribeRealm<List<T>>(context) {
            @Override
            public List<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }

    public static <T extends RealmObject> Observable<RealmResults<T>> results(Context context, final Func1<Realm, RealmResults<T>> function) {
        return Observable.create(new OnSubscribeRealm<RealmResults<T>>(context) {
            @Override
            public RealmResults<T> get(Realm realm) {
                return function.call(realm);
            }
        });
    }
}
