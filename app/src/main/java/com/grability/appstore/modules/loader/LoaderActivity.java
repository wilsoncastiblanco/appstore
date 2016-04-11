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
import com.grability.appstore.receivers.NetworkChangeReceiver;
import com.grability.appstore.utils.AppUtil;
import com.grability.appstore.utils.IntentUtil;
import com.grability.appstore.utils.NetworkUtil;

import java.util.ArrayList;
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
        AppUtil.validateScreenOrientation(this);
        initView();
        initPresenter();
        initHandler();
    }

    private void initView(){
        loaderSvgView.setSvgResource(R.raw.appstore);
    }

    private void initPresenter(){
        presenter = new ApplicationsPresenter(getApplicationContext(),this);
    }

    /**
     * This function was created intentionally just for showing loading animation
     * It is because of data is loading before to show the animation
     * */
    private void initHandler(){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if(NetworkUtil.isOnline()){
                    presenter.loadApplicationsList();
                }else{
                    OnApplicationsListLoaded(new ArrayList<ApplicationEntry>());
                }
            }
        }, 6500);
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
        NetworkChangeReceiver.enableNetworkReceiver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.realmUnsubscribe();
    }
}
