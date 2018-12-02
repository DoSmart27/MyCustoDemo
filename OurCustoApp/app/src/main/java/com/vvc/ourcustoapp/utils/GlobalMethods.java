package com.vvc.ourcustoapp.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.vvc.ourcustoapp.CustomerApplication;
import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.SplashScreenActivity;
import com.vvc.ourcustoapp.custom.edittext.PrefixEditText;
import com.vvc.ourcustoapp.custom.progressbar.SmoothProgressBar;
import com.vvc.ourcustoapp.models.DefaultResponse;
import com.vvc.ourcustoapp.presentation.login.fragments.SignUpDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GlobalMethods {


    public static boolean isOnline(Context context) throws Exception {
        try {
            return SingletonClass.getInstance().isDeviceOnline(context);
        } catch (Exception e) {
            throw e;
        }

    }

    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER)) {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            return false;
        }
    }

    public static boolean isNull(String data) {

        boolean isnull = false;
        if (data != null) {
            if (!data.equals("") && !data.equals("null") && data != null
                    && !data.equals("-1")) {
                isnull = true;
            }
        } else {
            isnull = false;
        }

        return isnull;
    }


    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static boolean isGPSServiceOn(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

/*
    public static void callForWordActivity(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }

    public static void callBackWordActivity(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }

    public static void callFinishForBackWordActivity(Context context, boolean isForwardAnimation) {
        try {

            ((Activity) context).finish();
            if (isForwardAnimation) {
                ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
            } else {
                ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callForWordActivityForResult(Context context, Class forwardClass, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(myIntent, ResultCode);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }

    }

    public static void callBackWordActivityForResult(Context context, Bundle bundle, int ResultCode, boolean isForwardAnimation) {
        Intent myIntent = new Intent();
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).setResult(Activity.RESULT_OK, myIntent);
        ((Activity) context).finish();
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }
    }

    public static CharSequence setEditextError(Context context, String str, EditText editext) {

        editext.requestFocus();
        editext.setError(str);

        return "";
    }

*/
    /*public static ProgressDialog showProgress(Context context) {

        ProgressDialog progress = ProgressDialog.show(context, null, null);
//        progress.setMessage(message);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setContentView(R.layout.progress_layout);
        progress.setCancelable(false);
        progress.show();
        return progress;
    }*/

    public static void hideProgress(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }



    public static void showLoginBottomSheetDialog(Activity activity, boolean showSignUp) {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(activity);
        View bottomSheetLayout = LayoutInflater.from(activity).inflate(R.layout.bottom_sheet_login, null);
        ((LinearLayout) bottomSheetLayout.findViewById(R.id.layout_sign_up)).setVisibility(showSignUp ? View.VISIBLE : View.GONE);

        // LinearLayout layout_login = (LinearLayout) bottomSheetLayout.findViewById(R.id.layout_login);
        PrefixEditText mobile_number = (PrefixEditText) bottomSheetLayout.findViewById(R.id.input_mobile);
        ((AppCompatButton) bottomSheetLayout.findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SplashScreenActivity.this, "login", Toast.LENGTH_SHORT).show();
                String mobile = mobile_number.getText().toString().trim();
                if (!TextUtils.isEmpty(mobile)) {
                    callVerifyAndLogin(mobile);
                } else {
                    Toast.makeText(activity, activity.getString(R.string.enter_mobile), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ((AppCompatTextView) bottomSheetLayout.findViewById(R.id.sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                showSignUp(activity, mobile_number.getText().toString().trim());
            }
        });

        ((AppCompatImageButton) bottomSheetLayout.findViewById(R.id.button_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });


        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.show();
    }

    private static void showSignUp(Activity activity, String mobile) {
        SignUpDialogFragment signUpDialogFragment = new SignUpDialogFragment(activity, mobile);
        AndroidUtils.dialogFragmentShowRight(activity, signUpDialogFragment);
    }

    private static void callVerifyAndLogin(String mobile) {
        Call<DefaultResponse> defaultResponseCall = CustomerApplication.getApplicationInstance().getAppWebService().doVerifyMobileNumber(mobile);
        defaultResponseCall.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefaultResponse> call, @NonNull Response<DefaultResponse> response) {
                Log.e("response", "" + response.toString());

                Log.e("response", "" + response.body());

                Log.e("response", "" + response.body().getMsg());


                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    Log.e("response", "" + response.body().getStatus());
                } else if (response.body().getStatus().equalsIgnoreCase("1")) {


                }
            }

            @Override
            public void onFailure(@NonNull Call<DefaultResponse> call, @NonNull Throwable t) {
                call.cancel();
            }
        });
    }


    public static void enableGpsLocation(Activity activity, int REQUEST_LOCATION) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        // googleApiClient.connect();
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {

                        Log.d("Location error", "Location error " + connectionResult.getErrorCode());
                    }
                }).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3 * 1000);
        locationRequest.setFastestInterval(1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(activity, REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                }
            }
        });
    }


    public static SmoothProgressBar showProgress(Activity activity, int resourceId) {
        SmoothProgressBar smoothProgressBar = (SmoothProgressBar) activity.findViewById(resourceId);
        smoothProgressBar.setVisibility(View.VISIBLE);
        smoothProgressBar.progressiveStart();
        return smoothProgressBar;
    }

    public static void stopProgress(SmoothProgressBar smoothProgressBar) {
        smoothProgressBar.setVisibility(View.GONE);
        smoothProgressBar.progressiveStop();
    }


}
