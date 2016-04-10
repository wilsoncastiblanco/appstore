package com.grability.appstore.app;


import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.grability.appstore.api.RESTConstants;
import com.grability.appstore.utils.preferences.PreferenceUtil;


public class App extends Application{

  @Override
  public void onCreate() {
    super.onCreate();
    PreferenceUtil.init(this);
    RESTConstants.init();
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }
}
