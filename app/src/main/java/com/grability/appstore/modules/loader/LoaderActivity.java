package com.grability.appstore.modules.loader;

import android.app.Activity;
import android.os.Bundle;

import com.grability.appstore.R;
import com.grability.appstore.base.SvgView;
import com.grability.appstore.presenter.applications.ApplicationsPresenter;
import com.grability.appstore.presenter.applications.IApplicationsView;
import com.grability.appstore.utils.IntentUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoaderActivity extends Activity implements IApplicationsView{
    @Bind(R.id.loader)
    SvgView loaderSvgView;
    ApplicationsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        ButterKnife.bind(this);
        loaderSvgView.setSvgResource(R.raw.appstore);

        presenter = new ApplicationsPresenter(this);
        presenter.loadApplicationsList();
    }

    @Override
    public void OnApplicationsListLoaded() {
        IntentUtil.startCategoriesActivity(this);
    }

    @Override
    public void OnApplicationsListFailed() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.realmSubscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.realmUnsubscribe();
    }
}
