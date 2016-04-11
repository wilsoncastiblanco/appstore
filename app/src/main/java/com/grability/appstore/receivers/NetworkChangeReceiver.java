package com.grability.appstore.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.grability.appstore.R;
import com.grability.appstore.utils.NetworkUtil;

/**
 * Created by johan on 19/01/15.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    String message = context.getString(R.string.message_error_no_internet);
    if(NetworkUtil.isOnline()){
      message = context.getString(R.string.message_success_internet_connection);
    }
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }

  /**
   * disable the broadcastreceiver
   * @param context
   */
  public static void disableNetWorkReceiver(Context context){
    ComponentName component=new ComponentName(context, NetworkChangeReceiver.class);
    context.getPackageManager()
        .setComponentEnabledSetting(component,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP);
  }

  /**
   * enable the broadcastreceiver
   * @param context
   */
  public  static void enableNetworkReceiver(Context context){
    ComponentName component=new ComponentName(context, NetworkChangeReceiver.class);
    context.getPackageManager()
        .setComponentEnabledSetting(component,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP);
  }

}
