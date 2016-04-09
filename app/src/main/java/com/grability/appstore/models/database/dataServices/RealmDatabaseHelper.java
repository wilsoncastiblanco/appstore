package com.grability.appstore.models.database.dataServices;

import android.content.Context;

import com.grability.appstore.models.ApplicationEntry;

import java.util.List;

import io.realm.Realm;

/**
 * Created by wilson on 8/04/16.
 */
public class RealmDatabaseHelper {

    private static RealmDatabaseHelper instance;
    private static Realm realmObject;
    private RealmDataService dataService;

    public static void init(Context context) {
        if (instance == null) {
            instance = new RealmDatabaseHelper(context);

        }
    }

    public RealmDatabaseHelper(Context context){
        dataService = new RealmDataService(context.getApplicationContext());
    }

    public static boolean addApplicationList(List<ApplicationEntry> applicationEntries){
        for(ApplicationEntry applicationEntry : applicationEntries){

        }
        return true;
    }


}
