package com.grability.appstore.presenter.applications.async;


import com.grability.appstore.models.ApplicationEntry;

import java.util.List;

/**
 * Created by wilson on 8/04/16.
 */
public interface IApplicationsListener {
    void OnRequestSuccess(List<ApplicationEntry> applicationEntries);
    void OnRequestFailed();
}
