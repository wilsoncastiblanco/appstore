package com.grability.appstore.modules.apps;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.grability.appstore.R;
import com.grability.appstore.utils.IntentUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppsActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        ButterKnife.bind(this);
        validateScreenOrientation();
        initView();
    }

    private void validateScreenOrientation() {
        if(getResources().getBoolean(R.bool.portrait_only)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView(){
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra(IntentUtil.KEY_AUX);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
