package com.grability.appstore.presenter.categories;

import android.content.Context;

import com.grability.appstore.models.Category;
import com.grability.appstore.models.database.dataServices.RealmService;
import com.grability.appstore.presenter.IRealmPresenter;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by wilson on 7/04/16.
 */
public class CategoriesPresenter implements ICategoriesPresenter, IRealmPresenter{

    private ICategoriesView view;
    private RealmService realmService;
    private CompositeSubscription compositeSubscription;

    public CategoriesPresenter(Context context,ICategoriesView view){
        this.view = view;
        this.realmService = new RealmService(context.getApplicationContext());
    }


    @Override
    public void loadCategories() {
        compositeSubscription.add(subscriptionCategories());
    }

    private Subscription subscriptionCategories(){
        return realmService.categories().
                observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new Action1<List<Category>>() {
                            @Override
                            public void call(List<Category> categories) {
                                if(categories.size() > 0){
                                    view.OnCategoriesListLoaded(categories);
                                }else{
                                    view.OnCategoriesEmpty();
                                }
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                view.OnCategoriesListFailed();
                            }
                        }
                );
    }

    @Override
    public void realmSubscribe() {
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void realmUnsubscribe() {
        compositeSubscription.unsubscribe();
    }
}
