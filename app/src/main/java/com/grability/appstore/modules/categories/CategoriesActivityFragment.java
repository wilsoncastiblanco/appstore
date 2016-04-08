package com.grability.appstore.modules.categories;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grability.appstore.R;
import com.grability.appstore.models.Category;
import com.grability.appstore.presenter.categories.CategoriesPresenter;
import com.grability.appstore.presenter.categories.ICategoriesView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoriesActivityFragment extends Fragment implements ICategoriesView {
    @Bind(R.id.recyclerViewCategories)
    RecyclerView recyclerViewCategories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CategoriesPresenter presenter = new CategoriesPresenter(this);
        presenter.loadCategories();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void OnCategoriesListLoaded(List<Category> list) {

    }

    @Override
    public void OnCategoriesListFailed() {

    }
}
