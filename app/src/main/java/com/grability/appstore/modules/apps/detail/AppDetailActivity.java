package com.grability.appstore.modules.apps.detail;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.grability.appstore.R;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.utils.IntentUtil;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppDetailActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.imageViewAppDetail)
    ImageView imageViewAppDetail;
    @Bind(R.id.collapsingToolbarDetail)
    CollapsingToolbarLayout collapsingToolbarDetail;

    private ApplicationEntry applicationEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);
        ButterKnife.bind(this);
        validateScreenOrientation();
        initData();
        initView();
    }

    private void validateScreenOrientation() {
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarDetail.setTitle(applicationEntry.getName().getLabel());
        setSupportActionBar(toolbar);
        Picasso.with(getApplicationContext()).load(applicationEntry.getBiggestAppImage(applicationEntry.getImagesList())).into(imageViewAppDetail);
    }

    private void initData() {
        String applicationEntryString = getIntent().getStringExtra(IntentUtil.KEY_DATA);
        applicationEntry = new Gson().fromJson(applicationEntryString, ApplicationEntry.class);
    }
}
