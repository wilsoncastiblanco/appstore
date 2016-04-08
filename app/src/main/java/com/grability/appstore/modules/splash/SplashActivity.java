package com.grability.appstore.modules.splash;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;

import com.grability.appstore.R;
import com.grability.appstore.base.StateView;
import com.grability.appstore.utils.IntentUtil;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final StateView stateView = (StateView) findViewById(R.id.state);
        stateView.setSvgResource(R.raw.grability_logo);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                IntentUtil.startCategoriesActivity(SplashActivity.this);
            }
        }, 4000);
    }
}
