package com.grability.appstore.presenter.categories.async;

import com.grability.appstore.models.Category;

import java.util.List;

/**
 * Created by wilson on 7/04/16.
 */
public interface ICategoriesListener {
    void OnRequestSuccess(List<Category> list);
    void OnRequestFailed();
}
