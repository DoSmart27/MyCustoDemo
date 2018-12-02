package com.vvc.ourcustoapp.common.webservices;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
import retrofit2.Response;

public class ApiResponse2<T extends AppResponse> {

    private static final String INTERNET_ERROR = "No Internet found. Check your connection or try again";
    private static final String PARSING_ERROR = "Sorry! Unable to process your request";

    private final int code;
    @Nullable
    public final T body;
    @NonNull
    public final Status status;

    private String apiMessage;

    public ApiResponse2(Throwable error) {
        Log.d("Api Response:- " ,""+ error);
        code = 500;
        body = null;
        status = error instanceof JsonSyntaxException || error instanceof IllegalStateException ? Status.JSON_PARSING_ERROR : Status.INTERNET_CONNECTION_ERROR;
    }

    public ApiResponse2(Response<T> response) {
        code = response.code();
        if (response.isSuccessful()) {
            body = response.body();
            if (body == null) {
                status = Status.JSON_PARSING_ERROR;
                return;
            }
            if (!body.getStatus()) {
                status = Status.ERROR;
                apiMessage = body.getError();
                return;
            }
            status = Status.SUCCESS;
            apiMessage = body.getMessage();
        } else {
            String message = null;
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().string();
                } catch (IOException ignored) {
                    Log.e("error while parsing res", ""+ignored);
                }
            }
            if (message == null || message.trim().length() == 0) {
                message = response.message();
            }
            apiMessage = message;
            status = Status.JSON_PARSING_ERROR;
            body = null;
        }
    }

    public String getResponseMessage() {
        return apiMessage;
    }
}
