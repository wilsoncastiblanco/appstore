package com.grability.appstore.presenter.applications;

import com.grability.appstore.presenter.applications.async.ApplicationsInteractor;
import com.grability.appstore.presenter.applications.async.IApplicationsListener;

/**
 * Created by wilson on 9/04/16.
 */
public class ApplicationsPresenter implements IApplicationsPresenter, IApplicationsListener {

    private IApplicationsView listener;
    private ApplicationsInteractor applicationsInteractor;

    public ApplicationsPresenter(IApplicationsView listener){
        this.listener = listener;
        this.applicationsInteractor = new ApplicationsInteractor(this);
    }

    @Override
    public void loadApplicationsList() {
        applicationsInteractor.loadApplicationList();
    }

    @Override
    public void OnRequestSuccess() {
        listener.OnApplicationsListLoaded();
    }

    @Override
    public void OnRequestFailed() {
        listener.OnApplicationsListFailed();
    }
}
