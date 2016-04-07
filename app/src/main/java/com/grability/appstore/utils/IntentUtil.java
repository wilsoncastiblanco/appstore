package com.grability.appstore.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.grability.appstore.modules.categories.CategoriesActivity;


public class IntentUtil {

  public static final String KEY_DATA = "key_data";
  public static final String KEY_AUX = "key_aux";

  public static void startActivity(Context context, Class<?> cls) {
    context.startActivity(new Intent(context, cls));
  }

  public static void startCategoriesActivity(Activity activity){
    startActivity(activity, CategoriesActivity.class);
  }


}
