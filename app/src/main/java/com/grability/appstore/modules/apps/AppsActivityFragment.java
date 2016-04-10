package com.grability.appstore.modules.apps;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.grability.appstore.R;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.modules.apps.adapters.AppsAdapter;
import com.grability.appstore.modules.categories.adapters.CategoriesAdapter;
import com.grability.appstore.presenter.applications.ApplicationsPresenter;
import com.grability.appstore.presenter.applications.IApplicationsView;
import com.grability.appstore.utils.AppUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class AppsActivityFragment extends Fragment implements IApplicationsView, AppsAdapter.ItemClickListener {
    @Bind(R.id.recyclerViewApps)
    RecyclerView recyclerViewApps;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    ApplicationsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    private void initPresenter(){
        presenter = new ApplicationsPresenter(getActivity().getApplicationContext(),this);
        presenter.realmSubscribe();
        presenter.loadApplicationsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apps, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void OnApplicationsListLoaded(List<ApplicationEntry> applicationEntryList) {
        if(getActivity() != null){
            recyclerViewApps.setHasFixedSize(true);
            AppsAdapter adapter = new AppsAdapter(getActivity().getApplicationContext(),applicationEntryList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerViewApps.setAdapter(adapter);
            recyclerViewApps.setLayoutManager(mLayoutManager);
            recyclerViewApps.setItemAnimator(new DefaultItemAnimator());
            AppUtil.hideGoneViews(progressBar);
            adapter.setOnItemClickListener(this);
        }
    }

    @Override
    public void OnApplicationsListFailed() {
        Snackbar.make(recyclerViewApps, R.string.message_error_apps, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.realmUnsubscribe();
    }

    @Override
    public void onItemClickListener(View view, ApplicationEntry applicationEntry) {

    }
}