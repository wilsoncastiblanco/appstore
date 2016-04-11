package com.grability.appstore.modules.categories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.grability.appstore.R;
import com.grability.appstore.receivers.NetworkChangeReceiver;
import com.grability.appstore.utils.AppUtil;

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

    @Override
    protected void onStop() {
        super.onStop();
        NetworkChangeReceiver.disableNetWorkReceiver(this);
    }
}
