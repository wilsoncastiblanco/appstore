package com.grability.appstore.models.events;

import com.grability.appstore.models.ApplicationEntry;

/**
 * Created by wilson on 11/04/16.
 */
public class ApplicationEntryEvent {
    private ApplicationEntry applicationEntry;

    public ApplicationEntryEvent(ApplicationEntry applicationEntry) {
        this.applicationEntry = applicationEntry;
    }

    public ApplicationEntry getApplicationEntry() {
        return applicationEntry;
    }

    public void setApplicationEntry(ApplicationEntry applicationEntry) {
        this.applicationEntry = applicationEntry;
    }
}
