package com.grability.appstore.models.database.dataServices;

import com.grability.appstore.models.ApplicationArtist;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.ApplicationId;
import com.grability.appstore.models.ApplicationImages;
import com.grability.appstore.models.ApplicationLink;
import com.grability.appstore.models.ApplicationPrice;
import com.grability.appstore.models.ApplicationRelease;
import com.grability.appstore.models.Category;
import com.grability.appstore.models.GenericParameters;

import java.util.List;

import rx.Observable;

/**
 * Created by wilson on 8/04/16.
 */
public interface DataService {
    Observable<List<ApplicationEntry>> applications();
    Observable<ApplicationEntry> newApplication(GenericParameters<ApplicationArtist> artist, GenericParameters name, List<GenericParameters<ApplicationImages>> imagesList, GenericParameters summary, GenericParameters<ApplicationPrice> price, GenericParameters rights, GenericParameters title, GenericParameters<ApplicationLink> link, GenericParameters<ApplicationId> id, GenericParameters<Category> category, GenericParameters<ApplicationRelease> releaseDate);
}
