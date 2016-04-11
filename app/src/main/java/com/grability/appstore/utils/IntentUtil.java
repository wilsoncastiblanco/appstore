package com.grability.appstore.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.grability.appstore.R;
import com.grability.appstore.models.ApplicationEntry;
import com.grability.appstore.modules.apps.AppsActivity;
import com.grability.appstore.modules.apps.detail.AppDetailActivity;
import com.grability.appstore.modules.apps.detail.AppDetailActivityFragment;
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

  public static void startAppsActivity(Activity activity, String categoryId, String categoryName){
    Intent intent = new Intent(activity, AppsActivity.class);
    intent.putExtra(KEY_DATA, categoryId);
    intent.putExtra(KEY_AUX, categoryName);
    activity.startActivity(intent);
  }

  public static void startAppDetailActivity(Activity activity, ApplicationEntry applicationEntry){
    Intent intent = new Intent(activity, AppDetailActivity.class);
    intent.putExtra(KEY_DATA, new Gson().toJson(applicationEntry));
    activity.startActivity(intent);
  }

  public static void replaceAppDetailFragment(AppCompatActivity activity, ApplicationEntry applicationEntry){
    Bundle arguments = new Bundle();
    arguments.putString(IntentUtil.KEY_DATA, new Gson().toJson(applicationEntry));
    AppDetailActivityFragment fragment = new AppDetailActivityFragment();
    fragment.setArguments(arguments);
    activity.getSupportFragmentManager().beginTransaction().replace(R.id.itemDetailContainer, fragment).commit();
  }


}
