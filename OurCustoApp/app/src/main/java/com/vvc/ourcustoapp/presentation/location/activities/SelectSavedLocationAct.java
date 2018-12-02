package com.vvc.ourcustoapp.presentation.location.activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.BaseActivity;
import com.vvc.ourcustoapp.custom.textviews.CustomTextView;
import com.vvc.ourcustoapp.database.tables.MyAddress;
import com.vvc.ourcustoapp.database.viewmodel.AddressViewModel;
import com.vvc.ourcustoapp.presentation.home.activities.HomeActivity;
import com.vvc.ourcustoapp.presentation.location.adapters.AddressListAdapter;
import com.vvc.ourcustoapp.utils.AndroidUtils;
import com.vvc.ourcustoapp.utils.Constants;
import com.vvc.ourcustoapp.utils.GlobalMethods;

import java.util.List;

public class SelectSavedLocationAct extends BaseActivity {
    private boolean isGpsEnabled = false;
    private GoogleApiClient googleApiClient;
    private AddressViewModel addressViewModel;

    private AppCompatEditText search_edit_text;
    @Override
    public int setLayoutResource() {
        return R.layout.activity_select_saved_address;
    }

    @Override
    public void initGUI() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        search_edit_text = (AppCompatEditText) findViewById(R.id.search_edit_text);
        search_edit_text.setHint(getString(R.string.search_location));

        AddressListAdapter addressListAdapter = new AddressListAdapter(this, new AddressListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyAddress address) {
                // put this address in sp and get the address from it.

                Toast.makeText(SelectSavedLocationAct.this, "" +address.getTag(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent( SelectSavedLocationAct.this, HomeActivity.class);
                startActivity(intent);

            }
        });
        recyclerView.setAdapter(addressListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addressViewModel = ViewModelProviders.of(this).get(AddressViewModel.class);
        addressViewModel.getAllAddresses().observe(this, new Observer<List<MyAddress>>() {
            @Override
            public void onChanged(@Nullable final List<MyAddress> addressesList) {
                addressListAdapter.setAddresses(addressesList);
            }
        });

        toggleGpsEnable(GlobalMethods.isGPSEnabled(this));


        ((LinearLayout) findViewById(R.id.select_location)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGpsEnabled)
                    GlobalMethods.enableGpsLocation(SelectSavedLocationAct.this, Constants.REQUEST_LOCATION);
                else
                    callNextActivity();
            }
        });

        ((AppCompatImageButton)findViewById(R.id.search_voice_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AndroidUtils.promptSpeechToText(SelectSavedLocationAct.this);

            }
        });


    }

    @Override
    public void initData() {

    }

    /*@Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUEST_LOCATION:
                getLocationResult(resultCode);
                break;
            case Constants.REQUEST_CODE_SET_LOCATION:
                break;

            case Constants.REQUEST_SEARCH_MIC:
                if (resultCode == RESULT_OK) {
                    List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String spokenText = results.get(0);
                    search_edit_text.setText(spokenText);
                }
                break;
        }
    }

    private void getLocationResult(int resultCode) {
        switch (resultCode) {
            case Activity.RESULT_OK: {
                toggleGpsEnable(true);
                break;
            }
            case Activity.RESULT_CANCELED: {
                toggleGpsEnable(false);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void toggleGpsEnable(boolean status) {
        if (status) {
            ((CustomTextView) findViewById(R.id.tag_sub_title)).setText(getString(R.string.using_gps));
            isGpsEnabled = true;
            callNextActivity();
        } else {
            ((CustomTextView) findViewById(R.id.tag_sub_title)).setText(getString(R.string.enable_location));
            isGpsEnabled = false;
        }
    }

    private void callNextActivity()
    {
        startActivityForResult(new Intent(SelectSavedLocationAct.this, SaveLocationActivity.class),Constants.REQUEST_CODE_SET_LOCATION);
    }



}
