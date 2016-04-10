package com.grability.appstore.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.grability.appstore.modules.apps.AppsActivity;
import com.grability.appstore.modules.categories.CategoriesActivity;
import com.grability.appstore.modules.loader.LoaderActivity;


public class IntentUtil {

  public static final String KEY_DATA = "key_data";
  public static final String KEY_AUX = "key_aux";

  public static void startActivity(Context context, Class<?> cls) {
    context.startActivity(new Intent(context, cls));
  }

  public static void startLoaderActivity(Activity activity){
    startActivity(activity, LoaderActivity.class);
  }

  public static void startCategoriesActivity(Activity activity){
    startActivity(activity, CategoriesActivity.class);
  }

  public static void startAppsActivity(Activity activity, String categoryId){
    Intent intent = new Intent(activity, AppsActivity.class);
    intent.putExtra(KEY_DATA, categoryId);
    activity.startActivity(intent);
  }


}
