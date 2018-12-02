package com.vvc.ourcustoapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SingletonClass {

    private static SingletonClass INSTANCE = null;

    private SingletonClass() {
    }

    public static SingletonClass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonClass();
        }
        return INSTANCE;
    }


    public boolean isDeviceOnline(Context context) throws Exception {
        try {
            final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                // notify user you are online
                return true;
            } else {
                // notify user you are not online
                return false;
            }
        } catch (Exception e) {
            throw e;
        }

    }

}
