package com.grability.appstore.models.database.dataServices;

import android.content.Context;

import com.grability.appstore.models.ApplicationEntry;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by wilson on 8/04/16.
 */
public class RealmDatabaseHelper {

    private static RealmDatabaseHelper instance;
    private static RealmService dataService;

    public static void init(Context context) {
        if (instance == null) {
            instance = new RealmDatabaseHelper(context);

        }
    }

    public RealmDatabaseHelper(Context context){
        dataService = new RealmService(context.getApplicationContext());
    }

    public static Subscription addApplicationList(List<ApplicationEntry> applicationEntries){
        return dataService.newApplications(applicationEntries).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io())
                .subscribe(
                        new Action1<List<ApplicationEntry>>() {
                            @Override
                            public void call(List<ApplicationEntry> applicationEntries) {
                                
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        }

                );
    }


}
