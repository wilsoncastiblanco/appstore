package com.grability.appstore.modules.categories;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.grability.appstore.R;
import com.grability.appstore.utils.AppUtil;
import com.grability.appstore.utils.IntentUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoriesActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        ButterKnife.bind(this);
        AppUtil.validateScreenOrientation(this);
        initView();
    }

    private void initView(){
        setSupportActionBar(toolbar);
    }

}
