package com.vvc.ourcustoapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {

    public static void setPermitted(Context context, boolean flag) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE).edit();
        editor.putBoolean(Constants.permission, flag);
        editor.apply();
        editor.commit();
    }

    public static boolean getPermitted(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        return prefs.getBoolean(Constants.permission, false);
    }

    public static void setLogin(Context context, boolean isLogin) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFERENCES, context.MODE_PRIVATE).edit();
        editor.putBoolean(Constants.LOGIN, isLogin);
        editor.apply();
        editor.commit();
    }

    public static boolean isLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        return prefs.getBoolean(Constants.LOGIN, false);
    }

    public static void setName(Context context,String name)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.PREFERENCES, context.MODE_PRIVATE).edit();
        editor.putString(Constants.NAME, name);
        editor.apply();
        editor.commit();
    }

    public static String getName(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFERENCES, Context.MODE_PRIVATE);
        return prefs.getString(Constants.NAME, null);
    }
}
