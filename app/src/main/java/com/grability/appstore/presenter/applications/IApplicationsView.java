package com.grability.appstore.presenter.applications;

import com.grability.appstore.models.ApplicationEntry;

import java.util.List;

/**
 * Created by wilson on 9/04/16.
 */
public interface IApplicationsView {
    void OnApplicationsListLoaded(List<ApplicationEntry> applicationEntryList);
    void OnApplicationsListFailed();
}
