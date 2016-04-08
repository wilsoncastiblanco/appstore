package com.grability.appstore.presenter.categories;

import com.grability.appstore.models.Category;

import java.util.List;

/**
 * Created by wilson on 7/04/16.
 */
public interface ICategoriesView {
    void OnCategoriesListLoaded(List<Category> list);
    void OnCategoriesListFailed();
}
