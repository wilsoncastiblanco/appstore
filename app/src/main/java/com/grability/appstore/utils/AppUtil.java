package com.grability.appstore.utils;


import android.app.Activity;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;

import com.grability.appstore.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AppUtil {

  public static void hideViews(View... views) {
    for (View v : views) {
      toggleViewVisible(v, false, View.INVISIBLE);
    }
  }

  public static void hideGoneViews(View... views) {
    for (View v : views) {
      toggleViewVisible(v, false, View.GONE);
    }
  }

  public static void showViews(View... views) {
    for (View v : views) {
      toggleViewVisible(v, true, View.INVISIBLE);
    }
  }

  public static void animateViews(Animation anim, View... views) {
    for (View v : views) {
      v.startAnimation(anim);
    }
  }
  
  public static void disableViews(View... views){
    for (View v : views) {
      v.setEnabled(false);
    }
  }
  
  public static void enableViews(View... views){
    for (View v : views) {
      v.setEnabled(true);
    }
  }

  private static void toggleViewVisible(View v, boolean isVisible, int visibility) {
    v.setVisibility(isVisible ? View.VISIBLE : visibility);
  }

  public static String convertStreamToString(InputStream is) {
    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
    return s.hasNext() ? s.next() : "";
  }

  public static boolean checkNetworkAndShowDialogIfOffline(Activity context) {
    boolean online = NetworkUtil.isOnline(context);
    if (online == false) {
      //FragmentUtil.showOfflineDialog(context, DialogStyle.BLUE);
    }
    return online;
  }
  
  public static void hideSoftKeyboard(View v) {
    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
  }
  
  public static void showSoftKeyboard(View v) {
    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
    imm.showSoftInput(v, 0);
  }

  public static String getRealPathFromURI(Context context, Uri contentUri) {
    Cursor cursor = null;
    try {
      String[] proj = {MediaStore.Images.Media.DATA, MediaStore.Images.Media.DISPLAY_NAME};
      cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
      int dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      return cursor.getString(dataIndex);
    } finally {
      try {
        cursor.close();
      } catch (Exception e) {
      }
    }
  }
  
  
  
  public static File getFileFromPicasa(Context context, Uri uri){
    File tempFile = null;
    FileOutputStream fout = null;  
    InputStream in = null;
    try { 
      tempFile = File.createTempFile("tempFile", ".tmp");  
      tempFile.deleteOnExit();  
      ContentResolver res = context.getContentResolver();
      in =  res.openInputStream(uri);
      fout = new FileOutputStream(tempFile);  
      int c;  
      while ((c = in.read()) != -1) {  
        fout.write(c);  
      }  
    } catch (IOException e) {
      e.printStackTrace();
    }finally {  
      try {
        if (in != null) {  
          in.close();  
        }  
        if (fout != null) {  
          fout.close();  
        }  
      } catch (Exception e2) {}
    }  
    return tempFile;
  }
  
  public static boolean isValidEmail(CharSequence target) {
    return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
  }

  public static void validateScreenOrientation(Activity activity) {
    if(activity.getApplicationContext().getResources().getBoolean(R.bool.portrait_only)){
      activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }else{
      activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
  }

}
