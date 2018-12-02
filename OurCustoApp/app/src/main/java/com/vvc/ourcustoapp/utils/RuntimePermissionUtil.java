package com.vvc.ourcustoapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

public class RuntimePermissionUtil {

  private RuntimePermissionUtil() {

  }

  public static void onRequestPermissionsResult(int[] grantResults, RPResultListener rpResultListener) {
    if (grantResults.length > 0) {
      for (int grantResult : grantResults) {
        if (grantResult == PackageManager.PERMISSION_GRANTED) {
          rpResultListener.onPermissionGranted();
        } else {
          rpResultListener.onPermissionDenied();
        }
      }
    }
  }

  public static void requestPermission(final Activity activity, final String[] permissions, final int REQUEST_CODE) {
    // No explanation needed, we can request the permission.
    ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE);
  }

  public static void requestPermission(final Activity activity, final String permission, final int REQUEST_CODE) {
    // No explanation needed, we can request the permission.
    ActivityCompat.requestPermissions(activity, new String[] { permission }, REQUEST_CODE);
  }

  public static boolean checkPermissionGranted(Context context, String permission) {
    return (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
  }

  public static boolean hasPermissions(Context context, String... permissions) {
    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
      for (String permission : permissions) {
        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
          return false;
        }
      }
    }
    return true;
  }
}
