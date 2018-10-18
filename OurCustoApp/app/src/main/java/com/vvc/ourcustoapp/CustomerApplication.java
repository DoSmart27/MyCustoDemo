package com.vvc.ourcustoapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.vvc.ourcustoapp.common.Constants;
import com.vvc.ourcustoapp.database.AppDataBase;

public class CustomerApplication extends Application {

    private AppDataBase mDatabase;
    private static CustomerApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mApplication =this;
      //  mDatabase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, Constants.DATA_BASE_NAME).build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static CustomerApplication getApplicationInstance() {
        return mApplication;
    }

    public AppDataBase getDatabase() {
        return mDatabase;
    }
}
