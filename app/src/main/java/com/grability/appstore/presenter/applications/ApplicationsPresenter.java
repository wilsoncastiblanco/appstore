package com.grability.appstore.presenter.applications;

import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.database.dataServices.RealmDatabaseHelper;
import com.grability.appstore.presenter.applications.async.ApplicationsInteractor;
import com.grability.appstore.presenter.applications.async.IApplicationsListener;

import java.util.List;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by wilson on 9/04/16.
 */
public class ApplicationsPresenter implements IApplicationsPresenter, IApplicationsListener {

    private IApplicationsView listener;
    private ApplicationsInteractor applicationsInteractor;
    private CompositeSubscription compositeSubscription;

    public ApplicationsPresenter(IApplicationsView listener){
        this.listener = listener;
        this.applicationsInteractor = new ApplicationsInteractor(this);
    }

    @Override
    public void loadApplicationsList() {
        applicationsInteractor.loadApplicationList();
    }

    @Override
    public void realmSubscribe() {
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void realmUnsubscribe() {
        compositeSubscription.unsubscribe();
    }

    @Override
    public void OnRequestSuccess(List<ApplicationEntry> applicationEntryList) {
        Subscription subscription = RealmDatabaseHelper.addApplicationList(applicationEntryList);
        compositeSubscription.add(subscription);
        listener.OnApplicationsListLoaded();
    }

    @Override
    public void OnRequestFailed() {
        listener.OnApplicationsListFailed();
    }
}
