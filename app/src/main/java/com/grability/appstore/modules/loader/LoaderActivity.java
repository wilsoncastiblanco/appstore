package com.grability.appstore.modules.loader;

import android.app.Activity;
import android.os.Bundle;

import com.grability.appstore.R;
import com.grability.appstore.base.SvgView;
import com.grability.appstore.presenter.applications.IApplicationsView;
import com.grability.appstore.utils.IntentUtil;

public class LoaderActivity extends Activity implements IApplicationsView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        final SvgView stateView = (SvgView) findViewById(R.id.loader);
        stateView.setSvgResource(R.raw.appstore);
    }

    @Override
    public void OnApplicationsListLoaded() {
        IntentUtil.startCategoriesActivity(this);
    }

    @Override
    public void OnApplicationsListFailed() {

    }
}
