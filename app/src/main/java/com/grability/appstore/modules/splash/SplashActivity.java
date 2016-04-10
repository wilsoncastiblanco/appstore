package com.grability.appstore.modules.splash;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;

import com.grability.appstore.R;
import com.grability.appstore.base.SvgView;
import com.grability.appstore.utils.IntentUtil;

import butterknife.Bind;

public class SplashActivity extends Activity {
    @Bind(R.id.splashLogo)
    SvgView splashSvgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashSvgView.setSvgResource(R.raw.grability_logo);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                IntentUtil.startLoaderActivity(SplashActivity.this);
            }
        }, 4000);
    }
}
