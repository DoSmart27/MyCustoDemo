package com.vvc.ourcustoapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvc.ourcustoapp.utils.Constants;
import com.vvc.ourcustoapp.common.webservices.AppWebService;
import com.vvc.ourcustoapp.common.webservices.DateGsonTypeAdapter;
import com.vvc.ourcustoapp.common.webservices.GsonFieldExclusionStrategy;
import com.vvc.ourcustoapp.common.webservices.LiveDataCallAdapterFactory;
import com.vvc.ourcustoapp.database.AppDataBase;

import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerApplication extends Application {

    private AppWebService mAppWebService;
    private AppDataBase mDatabase;
    private static CustomerApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mApplication = this;

        mAppWebService = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(AppWebService.class);

        mDatabase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, Constants.DATA_BASE_NAME).build();
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


    public AppWebService getAppWebService() {
        return mAppWebService;
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    private Gson getGson() {
        return new GsonBuilder()
                .registerTypeHierarchyAdapter(Calendar.class, new DateGsonTypeAdapter())
                .setExclusionStrategies(new GsonFieldExclusionStrategy())
                .setLenient()
                .create();
    }
}
