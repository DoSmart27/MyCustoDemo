package com.vvc.ourcustoapp.presentation.location.activities;

import android.Manifest;
import android.app.IntentService;
import android.arch.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;

import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.BaseActivity;
import com.vvc.ourcustoapp.custom.progressbar.SmoothProgressBar;
import com.vvc.ourcustoapp.database.tables.MyAddress;
import com.vvc.ourcustoapp.database.viewmodel.AddressViewModel;

import com.vvc.ourcustoapp.utils.AndroidUtils;
import com.vvc.ourcustoapp.utils.Constants;
import com.vvc.ourcustoapp.utils.GlobalMethods;
import com.vvc.ourcustoapp.utils.RuntimePermissionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class SaveLocationActivity extends BaseActivity implements OnMapReadyCallback, View.OnClickListener {

    private FusedLocationProviderClient mFusedLocationClient;
    private View bottomSheet;
    private GoogleMap mGoogleMap;
    private SmoothProgressBar smoothProgressBar;

    private AddressViewModel addressViewModel;

    private String location_tag = "";
    Bitmap location_bitmap;

    private MyAddress myAddress;

    private AppCompatImageView image_home,image_work,image_other;


    @Override
    public int setLayoutResource() {
        return R.layout.activity_set_save_location;
    }

    @Override
    public void initGUI() {
        configureUIPart();
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        addressViewModel = ViewModelProviders.of(this).get(AddressViewModel.class);
        smoothProgressBar = (SmoothProgressBar) findViewById(R.id.progress_show);
        ((AppCompatImageView) findViewById(R.id.back_pressed)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveLocationActivity.this.finish();
            }
        });

        ((AppCompatImageButton) findViewById(R.id.currentLocationImageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!GlobalMethods.isGPSEnabled(SaveLocationActivity.this)) {
                    GlobalMethods.enableGpsLocation(SaveLocationActivity.this, Constants.REQUEST_LOCATION);
                }else {
                    getCurrentLocation();
                }
            }
        });
    }

    private void configureUIPart() {
        int heightPixels = AndroidUtils.getScreenHeight();
        int fragmentHeight = (heightPixels / 2) + 100;
        int bottomSheetHeight = (heightPixels - fragmentHeight) - 87;

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        ViewGroup.LayoutParams params = frameLayout.getLayoutParams();
        params.height = fragmentHeight;
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        frameLayout.setLayoutParams(params);

        bottomSheet = findViewById(R.id.bottom_sheet_location);
        BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(bottomSheetHeight);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        bottomSheet.findViewById(R.id.edit_text_others).setVisibility(View.GONE);
        bottomSheet.findViewById(R.id.layout_home).setOnClickListener(this);
        bottomSheet.findViewById(R.id.layout_work).setOnClickListener(this);
        bottomSheet.findViewById(R.id.layout_other).setOnClickListener(this);

        image_home = (AppCompatImageView) bottomSheet.findViewById(R.id.image_tag_home);
        image_work = (AppCompatImageView) bottomSheet.findViewById(R.id.image_tag_work);
        image_other = (AppCompatImageView) bottomSheet.findViewById(R.id.image_tag_others);

        ((AppCompatButton) bottomSheet.findViewById(R.id.add_location_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (location_tag.isEmpty()) {
                    AndroidUtils.showMessage(v, getString(R.string.set_address_tag));
                } else {
                    if (!location_tag.equalsIgnoreCase(getString(R.string.title_others))) {
                        if (location_tag.equalsIgnoreCase(getString(R.string.title_home)) || location_tag.equalsIgnoreCase(getString(R.string.title_work))) {
                            saveAddressIntoDB(location_tag);
                        } else {
                            String sub_tag = ((AppCompatEditText) bottomSheet.findViewById(R.id.edit_text_others)).getText().toString().trim();
                            if (sub_tag.isEmpty()) {
                                AndroidUtils.showMessage(bottomSheet, getString(R.string.other_text));
                            }
                        }

                    }
                    if (location_tag.equalsIgnoreCase(getString(R.string.title_others))) {
                        String sub_tag = ((AppCompatEditText) bottomSheet.findViewById(R.id.edit_text_others)).getText().toString().trim();
                        if (sub_tag.isEmpty()) {
                            AndroidUtils.showMessage(bottomSheet, getString(R.string.other_text));
                        } else {
                            if (isOtherTag(sub_tag)) {
                                AndroidUtils.showMessage(bottomSheet, getString(R.string.other_same_error));
                            } else {
                                location_tag = sub_tag;
                                saveAddressIntoDB(location_tag);
                            }
                        }
                    }
                }
            }
        });
 /*
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    //tapactionlayout.setVisibility(View.VISIBLE);
                   // Toast.makeText(ShowMapsLocation.this, "STATE_COLLAPSED", Toast.LENGTH_SHORT).show();
                }
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    // tapactionlayout.setVisibility(View.GONE);
                  //  Toast.makeText(ShowMapsLocation.this, "STATE_EXPANDED", Toast.LENGTH_SHORT).show();
                }
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    // tapactionlayout.setVisibility(View.GONE);
                   // Toast.makeText(ShowMapsLocation.this, "STATE_DRAGGING", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });*/

    }

    private boolean isOtherTag(String other_tag) {
        if (other_tag.toLowerCase().equalsIgnoreCase("others") || other_tag.toLowerCase().equalsIgnoreCase("other"))
            return true;
        else return other_tag.toLowerCase().contains("other");
    }

    private void saveAddressIntoDB(String location_tag) {
        String landmark = ((AppCompatEditText) bottomSheet.findViewById(R.id.input_landmark)).getText().toString().trim();
        if (landmark.isEmpty()) {
            AndroidUtils.showMessage(bottomSheet, getString(R.string.landmark_error));
        } else {
            String house_no = ((AppCompatEditText) bottomSheet.findViewById(R.id.input_house_number)).getText().toString().trim();
            String user_area = ((AppCompatEditText) bottomSheet.findViewById(R.id.input_area)).getText().toString().trim();
            byte[] image = AndroidUtils.convertBitmapToByte(location_bitmap);

            MyAddress saveAddress = new MyAddress(location_tag,image, myAddress.getAddress(), myAddress.getAdmin_area(),myAddress.getSub_admin_area(),
                    myAddress.getPinCode(),house_no,user_area,landmark,myAddress.getAddress_latitude(),myAddress.getAddress_longitude(),myAddress.getLocality(),
                    myAddress.getCountry(),myAddress.getSub_locality(),myAddress.getFeature_name(), myAddress.getThorough_fare());

            addressViewModel.insert(saveAddress);
            finish();
        }
    }


    @Override
    public void initData() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        configureGoogleMap(mGoogleMap);
        enableMyLocation();
        getCurrentLocation();
    }

    private void getCurrentLocation() {
        mGoogleMap.clear();
        if (ActivityCompat.checkSelfPermission(this, Constants.permission_location) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    movePointerOnMap(location.getLatitude(), location.getLongitude());
                }
            }
        });
    }

    private void movePointerOnMap(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        mGoogleMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(true)  //Making the marker draggable
                .title("Drag & Drop Marker")); //Adding a title
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(18.3f));
        getAddressFromLatLong(latLng);
        mGoogleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }
            @Override
            public void onMarkerDragEnd(Marker marker) {
                LatLng position = marker.getPosition();
                getAddressFromLatLong(position);
            }
            @Override
            public void onMarkerDrag(Marker marker) {
            }
        });
    }


    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Constants.permission_location) != PackageManager.PERMISSION_GRANTED) {
            RuntimePermissionUtil.requestPermission(this, Constants.permission_location, Constants.REQUEST_LOCATION);
        } else if (mGoogleMap != null) {
            // Access to the location has been granted to the app.
            mGoogleMap.setMyLocationEnabled(true);
        }
    }


    private void getAddressFromLatLong(LatLng latLng) {
        getAddressIntentService(latLng);
    }

    protected void getAddressIntentService(LatLng latLng) {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, new AddressResultReceiver(new Handler()));
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, latLng);
        startService(intent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_home:
                bottomSheet.findViewById(R.id.edit_text_others).setVisibility(View.GONE);
                setToggleChangeColorSelected(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.dark_grey));
                location_tag = getString(R.string.title_home);
                location_bitmap = AndroidUtils.getBitmapFromDrawable(this,R.drawable.ic_home_black_24dp);
                break;
            case R.id.layout_work:
                bottomSheet.findViewById(R.id.edit_text_others).setVisibility(View.GONE);
                setToggleChangeColorSelected(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.dark_grey));
                location_tag = getString(R.string.title_work);
                location_bitmap = AndroidUtils.getBitmapFromDrawable(this,R.drawable.ic_baseline_work_24px);
                break;
            case R.id.layout_other:
                bottomSheet.findViewById(R.id.edit_text_others).setVisibility(View.VISIBLE);
                setToggleChangeColorSelected(getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.dark_grey), getResources().getColor(R.color.colorPrimary));
                location_tag = getString(R.string.title_others);
                location_bitmap = AndroidUtils.getBitmapFromDrawable(this,R.drawable.ic_baseline_pin_drop_24px);
                break;
        }
    }

    private void configureGoogleMap(GoogleMap googleMap) {
        googleMap.setBuildingsEnabled(true);
        googleMap.setIndoorEnabled(true);
        // mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // mGoogleMap.setMyLocationEnabled(true);
        //mGoogleMap.setOnCameraIdleListener(onCameraIdleListener);
        UiSettings mUiSettings = googleMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(false);
        mUiSettings.setCompassEnabled(false);
        mUiSettings.setMyLocationButtonEnabled(false);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setTiltGesturesEnabled(true);
        mUiSettings.setRotateGesturesEnabled(true);
    }

    private void setToggleChangeColorSelected(int homeColor, int workColor, int otherColor) {
        bottomSheet.findViewById(R.id.view_select_home).setBackgroundColor(homeColor);
        ((AppCompatTextView) bottomSheet.findViewById(R.id.text_tag_home)).setTextColor(homeColor);
        ((AppCompatImageView) bottomSheet.findViewById(R.id.image_tag_home)).setColorFilter(homeColor);

        bottomSheet.findViewById(R.id.view_select_work).setBackgroundColor(workColor);
        ((AppCompatTextView) bottomSheet.findViewById(R.id.text_tag_work)).setTextColor(workColor);
        ((AppCompatImageView) bottomSheet.findViewById(R.id.image_tag_work)).setColorFilter(workColor);

        bottomSheet.findViewById(R.id.view_select_other).setBackgroundColor(otherColor);
        ((AppCompatTextView) bottomSheet.findViewById(R.id.text_tag_other)).setTextColor(otherColor);
        ((AppCompatImageView) bottomSheet.findViewById(R.id.image_tag_others)).setColorFilter(otherColor);
    }


    public static class FetchAddressIntentService extends IntentService {
        ResultReceiver myReceiver;
        public FetchAddressIntentService() {
            super("Fetching address");
        }
        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            if (intent == null) {
                return;
            }
            //assert intent != null;
            LatLng latLng = intent.getParcelableExtra(Constants.LOCATION_DATA_EXTRA);
            myReceiver = intent.getParcelableExtra(Constants.RECEIVER);
            List<Address> address = null;
            try {
                address = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            if (address == null || address.size() == 0) {
                Toast.makeText(this, "No address found", Toast.LENGTH_LONG).show();
                deliverResultToReceiver(Constants.FAILURE_RESULT, "No address Found");
            } else {
                Address currentAddress = address.get(0);
                ArrayList<String> addressFragment = new ArrayList<String>();
                for (int i = 0; i <= currentAddress.getMaxAddressLineIndex(); i++) {
                    addressFragment.add(currentAddress.getAddressLine(i));
                }

                String addressString = TextUtils.join(System.getProperty("line.saparator"), addressFragment);

                MyAddress sendAddress = new MyAddress(addressString, currentAddress.getPostalCode(),
                        currentAddress.getLocality(), currentAddress.getSubLocality(), "" + currentAddress.getLatitude(), "" + currentAddress.getLongitude(),
                        currentAddress.getCountryName(), currentAddress.getAdminArea(), currentAddress.getSubAdminArea(), currentAddress.getFeatureName(),
                        currentAddress.getThoroughfare());

                deliverResultToReceiver(Constants.SUCCESS_RESULT, sendAddress.toString());
            }
        }

        private void deliverResultToReceiver(int resultCode, String data) {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.RESULT_DATA_KEY, data);
            myReceiver.send(resultCode, bundle);
        }
    }

    public class AddressResultReceiver extends ResultReceiver {
        AddressResultReceiver(Handler handler) {
            super(handler);
        }
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData == null) {
                return;
            }
            String mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);
            if (resultCode == Constants.SUCCESS_RESULT) {
                if (mAddressOutput != null) {
                    myAddress = new Gson().fromJson(mAddressOutput, MyAddress.class);
                    setAddressLocation(myAddress.getAddress());
                }
            }
        }
        private void setAddressLocation(String address) {
            ((AppCompatEditText) bottomSheet.findViewById(R.id.input_location)).setText(address);
        }
    }
}