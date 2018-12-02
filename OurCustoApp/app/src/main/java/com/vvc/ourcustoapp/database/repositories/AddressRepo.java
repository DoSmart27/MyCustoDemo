package com.vvc.ourcustoapp.database.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.vvc.ourcustoapp.CustomerApplication;
import com.vvc.ourcustoapp.database.dao.AddressDao;
import com.vvc.ourcustoapp.database.tables.MyAddress;

import java.util.List;

public class AddressRepo {

    private AddressDao addressDao;
    private LiveData<List<MyAddress>> allAddressList;

    public AddressRepo(Application application)
    {
        addressDao = CustomerApplication.getApplicationInstance().getDatabase().getAddressDao();
        allAddressList =addressDao.getAllAddressesByAscending();
    }


    public LiveData<List<MyAddress>> getAllAddressList() {
        return  addressDao.getAllAddresses();
    }

    LiveData<List<MyAddress>> getAllAddressByAscending() {
        return allAddressList;
    }


    public void insert(MyAddress address) {
        new insertAsyncTask(addressDao).execute(address);
    }

    public void update(MyAddress address)  {
        new updateAddressAsyncTask(addressDao).execute(address);
    }

    public void deleteAll()  {
        new deleteAllAddressesAsyncTask(addressDao).execute();
    }

    // Must run off main thread
    public void deleteAddress(MyAddress address) {
        new deleteAddressAsyncTask(addressDao).execute(address);
    }

    private static class insertAsyncTask extends AsyncTask<MyAddress, Void, Void> {

        private AddressDao mAsyncTaskDao;

        insertAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MyAddress... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAllAddressesAsyncTask extends AsyncTask<Void, Void, Void> {
        private AddressDao mAsyncTaskDao;
        deleteAllAddressesAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class deleteAddressAsyncTask extends AsyncTask<MyAddress, Void, Void> {
        private AddressDao mAsyncTaskDao;

        deleteAddressAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MyAddress... params) {
            mAsyncTaskDao.deleteAddress(params[0]);
            return null;
        }
    }

    private static class updateAddressAsyncTask extends AsyncTask<MyAddress, Void, Void> {
        private AddressDao mAsyncTaskDao;

        updateAddressAsyncTask(AddressDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MyAddress... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }
}
