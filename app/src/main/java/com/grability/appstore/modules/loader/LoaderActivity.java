package com.grability.appstore.modules.loader;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;

import com.grability.appstore.R;
import com.grability.appstore.base.SvgView;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.presenter.applications.ApplicationsPresenter;
import com.grability.appstore.presenter.applications.IApplicationsView;
import com.grability.appstore.utils.IntentUtil;

import java.util.List;

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
        validateScreenOrientation();
        initView();
        initPresenter();
        initHandler();
    }

    private void validateScreenOrientation() {
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView(){
        loaderSvgView.setSvgResource(R.raw.appstore);
    }

    private void initPresenter(){
        presenter = new ApplicationsPresenter(getApplicationContext(),this);
    }

    /**
     * This function was created intentionally just for showing loading animation
     * It is because data is loading after to show the animation
     * */
    private void initHandler(){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                presenter.loadApplicationsList();
            }
        }, 5000);
    }

    @Override
    public void OnApplicationsListLoaded(List<ApplicationEntry> applicationEntryList) {
        IntentUtil.startCategoriesActivity(this);
    }

    @Override
    public void OnApplicationsListFailed() {
        Snackbar.make(loaderSvgView, R.string.message_error_loader, Snackbar.LENGTH_SHORT).show();
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
