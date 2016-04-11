package com.grability.appstore.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

public class NetworkUtil {

  public static boolean isOnline() {
    Runtime runtime = Runtime.getRuntime();
    try {
      Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
      int     exitValue = ipProcess.waitFor();
      return (exitValue == 0);
    } catch (IOException | InterruptedException e){
      e.printStackTrace();
    }
    return false;
  }
}
