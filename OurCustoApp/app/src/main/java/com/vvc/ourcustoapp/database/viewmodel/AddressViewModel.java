package com.vvc.ourcustoapp.database.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.vvc.ourcustoapp.database.repositories.AddressRepo;
import com.vvc.ourcustoapp.database.tables.MyAddress;

import java.util.List;

public class AddressViewModel extends AndroidViewModel {

    private AddressRepo addressRepo;
    private LiveData<List<MyAddress>> allAddressList;

    public AddressViewModel(@NonNull Application application) {
        super(application);
        addressRepo = new AddressRepo(application);
        allAddressList = addressRepo.getAllAddressList();
    }

    public LiveData<List<MyAddress>> getAllAddresses() {
        return allAddressList;
    }

    public void insert(MyAddress address) {
        addressRepo.insert(address);
    }

    public void deleteAll() {
        addressRepo.deleteAll();
    }

    public void deleteAddress(MyAddress address) {
        addressRepo.deleteAddress(address);
    }

    public void update(MyAddress address) {
        addressRepo.update(address);
    }
}
