package com.grability.appstore.presenter.categories;

import com.grability.appstore.models.Category;
import com.grability.appstore.presenter.categories.async.CategoriesInteractor;
import com.grability.appstore.presenter.categories.async.ICategoriesListener;

import java.util.List;

/**
 * Created by wilson on 7/04/16.
 */
public class CategoriesPresenter implements ICategoriesPresenter, ICategoriesListener{

    private ICategoriesView view;
    private CategoriesInteractor categoriesInteractor;

    public CategoriesPresenter(ICategoriesView view){
        this.view = view;
        categoriesInteractor = new CategoriesInteractor(this);
    }

    @Override
    public void loadCategories() {
        categoriesInteractor.loadCategoriesList();
    }

    @Override
    public void OnRequestSuccess(List<Category> list) {
        view.OnCategoriesListLoaded(list);
    }

    @Override
    public void OnRequestFailed() {
        view.OnCategoriesListFailed();
    }
}
