package com.grability.appstore.modules.apps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.grability.appstore.R;
import com.grability.appstore.models.events.ApplicationEntryEvent;
import com.grability.appstore.utils.AppUtil;
import com.grability.appstore.utils.IntentUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        AppUtil.validateScreenOrientation(this);
        initView();
    }

    private void initView(){
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra(IntentUtil.KEY_AUX);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void applicationEntrySelected(ApplicationEntryEvent applicationEntryEvent) {
        if(getResources().getBoolean(R.bool.dual_pane)){
            IntentUtil.replaceAppDetailFragment(this, applicationEntryEvent.getApplicationEntry());
        }else{
            IntentUtil.startAppDetailActivity(this, applicationEntryEvent.getApplicationEntry());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

}
