package com.vvc.ourcustoapp.common.webservices;

//import androidx.lifecycle.LiveData;
import com.vvc.ourcustoapp.models.DefaultResponse;
import com.vvc.ourcustoapp.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AppWebService {

   // @Headers("Authorization: Basic YWRtaW46dHJpYmFsV2VsZmFyZQ==")
   //// @POST("login")
  ////  LiveData<ApiResponse2<LoginResponse>> login(@Body LoginRequest query);

   // @Headers("Authorization: Basic YWRtaW46dHJpYmFsV2VsZmFyZQ==")
   // @POST("attendance/addUpdateAttendance")
   // LiveData<ApiResponse2<TribalResponse>> saveAttendance(@Body TribalRequest query);

    @GET("services/customers/verify/{mobile}")
    Call<DefaultResponse> doVerifyMobileNumber(@Query("mobile") String mobile);

    @POST("services/customers/addUser")
    Call<DefaultResponse> doSignUp(@Body User user);

    //@POST("/api/users")
    //Call<User> createUser(@Body User user);


}
