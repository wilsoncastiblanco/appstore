package com.grability.appstore.modules.categories;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.grability.appstore.R;
import com.grability.appstore.models.Category;
import com.grability.appstore.modules.categories.adapters.CategoriesAdapter;
import com.grability.appstore.presenter.categories.CategoriesPresenter;
import com.grability.appstore.presenter.categories.ICategoriesView;
import com.grability.appstore.utils.AppUtil;
import com.grability.appstore.utils.IntentUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoriesActivityFragment extends Fragment implements ICategoriesView, CategoriesAdapter.ItemClickListener {
    @Bind(R.id.recyclerViewCategories)
    RecyclerView recyclerViewCategories;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.textViewNoCategories)
    TextView textViewNoCategories;

    private CategoriesPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    private void initPresenter(){
        presenter = new CategoriesPresenter(getActivity().getApplicationContext(),this);
        presenter.realmSubscribe();
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
    public void OnCategoriesListLoaded(List<Category> categoryList) {
        if(getActivity() != null){
            setUpRecyclerView(categoryList);
        }
    }

    private void setUpRecyclerView(List<Category> categoryList){
        recyclerViewCategories.setHasFixedSize(true);
        CategoriesAdapter adapter = new CategoriesAdapter(getActivity().getApplicationContext(), categoryList);
        configureLayoutManager();
        recyclerViewCategories.setAdapter(adapter);
        recyclerViewCategories.setItemAnimator(new SlideInUpAnimator());
        AppUtil.hideGoneViews(progressBar);
        adapter.setOnItemClickListener(this);
    }

    private void configureLayoutManager(){
        if(getResources().getBoolean(R.bool.portrait_only)){
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerViewCategories.setLayoutManager(mLayoutManager);
        }else{
            GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerViewCategories.setLayoutManager(mLayoutManager);
        }
    }

    @Override
    public void OnCategoriesListFailed() {
        AppUtil.hideGoneViews(progressBar);
        Snackbar.make(recyclerViewCategories, R.string.message_error_categories, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void OnCategoriesEmpty() {
        AppUtil.hideGoneViews(progressBar);
        AppUtil.showViews(textViewNoCategories);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.realmUnsubscribe();
    }

    @Override
    public void onItemClickListener(View view, Category category) {
        IntentUtil.startAppsActivity(getActivity(), category.getId(), category.getLabel());
    }
}
