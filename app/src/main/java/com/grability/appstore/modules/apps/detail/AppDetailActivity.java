package com.grability.appstore.modules.apps.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.grability.appstore.R;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.utils.AppUtil;
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
        AppUtil.validateScreenOrientation(this);

        initData();
        initView();
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
