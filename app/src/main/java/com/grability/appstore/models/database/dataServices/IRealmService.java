package com.grability.appstore.models.database.dataServices;

import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.Category;

import java.util.List;

import rx.Observable;

/**
 * Created by wilson on 8/04/16.
 */
public interface IRealmService {
    Observable<List<ApplicationEntry>> applications();
    Observable<List<ApplicationEntry>> newApplications(List<ApplicationEntry> applicationEntries);
    Observable<List<Category>> categories();
    Observable<List<ApplicationEntry>> applicationsByCategory(String categoryId);
}
