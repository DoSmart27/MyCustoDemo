package com.vvc.ourcustoapp.presentation.home.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.BaseActivity;
import com.vvc.ourcustoapp.database.tables.MyAddress;
import com.vvc.ourcustoapp.database.viewmodel.AddressViewModel;
import com.vvc.ourcustoapp.presentation.home.adapters.ManageAddressAdapter;
import com.vvc.ourcustoapp.presentation.location.activities.SaveLocationActivity;

import java.util.List;

public class ManageAddresses extends BaseActivity {

    private AddressViewModel addressViewModel;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_manage_addresses;
    }

    @Override
    public void initGUI() {

        ((AppCompatTextView)findViewById(R.id.title_toolbar)).setText(getString(R.string.manage_address));
        findViewById(R.id.back_arrow).setOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ManageAddressAdapter manageAddressAdapter = new ManageAddressAdapter(ManageAddresses.this, new ManageAddressAdapter.OnManageItemClickListener() {
            @Override
            public void onUpdateClick(MyAddress item, int position) {
                Toast.makeText(ManageAddresses.this, "Update: "+item.getTag(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onDeleteClick(MyAddress item, int position) {
                Toast.makeText(ManageAddresses.this, "Delete: "+item.getTag(), Toast.LENGTH_SHORT).show();

            }
        });

        recyclerView.setAdapter(manageAddressAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addressViewModel = ViewModelProviders.of(this).get(AddressViewModel.class);
        addressViewModel.getAllAddresses().observe(this, new Observer<List<MyAddress>>() {
            @Override
            public void onChanged(@Nullable final List<MyAddress> addressesList) {
                manageAddressAdapter.setAddresses(addressesList);
            }
        });

        findViewById(R.id.button_add_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageAddresses.this, SaveLocationActivity.class));
            }
        });

    }

    @Override
    public void initData() {

    }
}
