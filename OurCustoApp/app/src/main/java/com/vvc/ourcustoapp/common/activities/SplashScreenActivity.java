package com.vvc.ourcustoapp.common.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vvc.ourcustoapp.CustomerApplication;
import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.presentation.home.activities.HomeActivity;
import com.vvc.ourcustoapp.presentation.location.activities.SelectSavedLocationAct;
import com.vvc.ourcustoapp.presentation.login.fragments.SignUpDialogFragment;
import com.vvc.ourcustoapp.utils.AndroidUtils;
import com.vvc.ourcustoapp.utils.Constants;
import com.vvc.ourcustoapp.common.webservices.AppWebService;
import com.vvc.ourcustoapp.custom.edittext.PrefixEditText;
import com.vvc.ourcustoapp.models.DefaultResponse;
import com.vvc.ourcustoapp.utils.GlobalMethods;
import com.vvc.ourcustoapp.utils.RuntimePermissionUtil;
import com.vvc.ourcustoapp.utils.SharedUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends BaseActivity {

    private AppCompatButton give_permissions;
    private LinearLayout layout_bottom;
    private AppWebService mAppWebService;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    public void initGUI() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        layout_bottom = (LinearLayout) findViewById(R.id.layout_bottom);
        give_permissions = (AppCompatButton) findViewById(R.id.give_permissions);

        mAppWebService = CustomerApplication.getApplicationInstance().getAppWebService();

        //RuntimePermissionUtil.checkPermissionGranted(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (!RuntimePermissionUtil.hasPermissions(SplashScreenActivity.this, Constants.permissionsList)) {
            // layout_login.setVisibility(View.GONE);
            // give_permissions.setVisibility(View.VISIBLE);
            SharedUtils.setPermitted(getApplicationContext(), false);
        }
        //showProgressVisibility(true);


        if (SharedUtils.getPermitted(getApplicationContext())) {
            showProgressVisibility(true);
        } else {
            showProgressVisibility(false);
        }

        ((AppCompatButton) findViewById(R.id.give_permissions)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermission();
            }
        });

        ((AppCompatButton) findViewById(R.id.button_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeLoginBottomSheet();
            }
        });

        ((AppCompatButton) findViewById(R.id.button_set_delivery)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showSignUp(null);
               // startActivity(new Intent(SplashScreenActivity.this, SelectSavedLocationAct.class));

                //startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                startActivity(new Intent(SplashScreenActivity.this, SelectSavedLocationAct.class));

            }
        });


    }

    @Override
    public void initData() {

    }

    private void initializeLoginBottomSheet() {

        GlobalMethods.showLoginBottomSheetDialog(SplashScreenActivity.this,true);

        /*BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(SplashScreenActivity.this);
        View bottomSheetLayout = LayoutInflater.from(SplashScreenActivity.this).inflate(R.layout.bottom_sheet_login, null);

        LinearLayout layout_login = (LinearLayout) bottomSheetLayout.findViewById(R.id.layout_login);
        PrefixEditText mobile_number = (PrefixEditText) bottomSheetLayout.findViewById(R.id.input_mobile);

        ((AppCompatButton) bottomSheetLayout.findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SplashScreenActivity.this, "login", Toast.LENGTH_SHORT).show();
                String mobile = mobile_number.getText().toString().trim();
                if (!TextUtils.isEmpty(mobile)) {
                    callVerifyAndLogin(mobile);
                } else {
                    Toast.makeText(SplashScreenActivity.this, getString(R.string.enter_mobile), Toast.LENGTH_SHORT).show();
                }

            }
        });
        ((AppCompatTextView) bottomSheetLayout.findViewById(R.id.sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                showSignUp(mobile_number.getText().toString().trim());
            }
        });

        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.show();*/

    }



    private void askPermission() {
        if (!RuntimePermissionUtil.hasPermissions(SplashScreenActivity.this, Constants.permissionsList)) {
            RuntimePermissionUtil.requestPermission(SplashScreenActivity.this, Constants.permissionsList, Constants.MULTIPLE_PERMISSIONS);
        } else {
            showProgressVisibility(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SharedUtils.setPermitted(getApplicationContext(), true);
                    showProgressVisibility(true);
                }
                break;
            }
        }
    }


    private void showProgressVisibility(boolean show) {
        if (show) {
            layout_bottom.setVisibility(View.VISIBLE);
            give_permissions.setVisibility(View.GONE);

        } else {
            layout_bottom.setVisibility(View.GONE);
            give_permissions.setVisibility(View.VISIBLE);
        }
    }


    /* private void callVerifyAndLogin(String mobile) {
        Call<DefaultResponse> defaultResponseCall = mAppWebService.doVerifyMobileNumber(mobile);
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
*/
}
