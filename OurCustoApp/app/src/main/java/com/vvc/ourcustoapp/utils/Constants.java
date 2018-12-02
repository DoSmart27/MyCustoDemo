package com.vvc.ourcustoapp.utils;

import android.Manifest;

public interface Constants {
    String PACKAGE_NAME = "com.vvc.ourcustoapp";
    String DATA_BASE_NAME = PACKAGE_NAME + "app_data_base";
    String permission = "permission";

    String PREFERENCES = "preferences";
    String LOGIN = "login";
    String NAME = "name";

    String BASE_URL = " http://yourfood.in/";

    String APP_DATE_FORMAT = "dd-MM-yyyy";

    int MULTIPLE_PERMISSIONS = 100;
    int REQUEST_LOCATION = 101;

    String permission_location = Manifest.permission.ACCESS_FINE_LOCATION;
    String permission_storage = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    String permission_call_phone = Manifest.permission.CALL_PHONE;

    String[] permissionsList = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE
    };

    int REQUEST_CODE_SET_LOCATION = 102;
    int REQUEST_SEARCH_MIC = 103;

    int SUCCESS_RESULT = 0;
    int FAILURE_RESULT = 1;
    String RECEIVER = "addressReceiver";
    String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

}
