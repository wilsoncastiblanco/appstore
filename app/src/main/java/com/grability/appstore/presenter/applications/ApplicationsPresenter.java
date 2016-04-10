package com.grability.appstore.presenter.applications;

import android.content.Context;

import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.models.database.dataServices.RealmService;
import com.grability.appstore.presenter.IRealmPresenter;
import com.grability.appstore.presenter.applications.async.ApplicationsInteractor;
import com.grability.appstore.presenter.applications.async.IApplicationsListener;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by wilson on 9/04/16.
 */
public class ApplicationsPresenter implements IApplicationsPresenter, IApplicationsListener, IRealmPresenter {

    private IApplicationsView listener;
    private ApplicationsInteractor applicationsInteractor;
    private CompositeSubscription compositeSubscription;
    private RealmService realmService;

    public ApplicationsPresenter(Context context, IApplicationsView listener){
        this.listener = listener;
        this.applicationsInteractor = new ApplicationsInteractor(this);
        this.realmService = new RealmService(context.getApplicationContext());
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
        compositeSubscription.add(subscriptionApplicationEntries(applicationEntryList));
    }

    @Override
    public void OnRequestFailed() {
        listener.OnApplicationsListFailed();
    }

    private Subscription subscriptionApplicationEntries(List<ApplicationEntry> applicationEntryList){
        return realmService.newApplications(applicationEntryList).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io())
                .subscribe(
                        new Action1<List<ApplicationEntry>>() {
                            @Override
                            public void call(List<ApplicationEntry> applicationEntries) {
                                listener.OnApplicationsListLoaded();
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                listener.OnApplicationsListFailed();
                            }
                        }

                );
    }
}
