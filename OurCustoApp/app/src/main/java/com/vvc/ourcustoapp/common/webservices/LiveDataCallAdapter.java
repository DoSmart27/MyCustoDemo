package com.vvc.ourcustoapp.common.webservices;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;


import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R extends AppResponse> implements CallAdapter<R, LiveData<ApiResponse2<R>>> {
    private final Type responseType;

    LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<ApiResponse2<R>> adapt(@NonNull final Call<R> call) {
        return new LiveData<ApiResponse2<R>>() {
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (started.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(@NonNull Call<R> call, @NonNull Response<R> response) {
                            postValue(new ApiResponse2<>(response));
                        }

                        @Override
                        public void onFailure(@NonNull Call<R> call, @NonNull Throwable throwable) {
                            postValue(new ApiResponse2<R>(throwable));
                        }
                    });
                }
            }
        };
    }
}

