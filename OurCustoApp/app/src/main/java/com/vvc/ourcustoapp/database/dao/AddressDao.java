package com.vvc.ourcustoapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.vvc.ourcustoapp.database.tables.MyAddress;

import java.util.List;

@Dao
public interface AddressDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MyAddress address);

    @Query("DELETE FROM address_table")
    void deleteAll();


    @Query("SELECT * from address_table LIMIT 1")
    MyAddress[] getAnyAddress();

    @Query("SELECT * from address_table ORDER BY id ASC")
    LiveData<List<MyAddress>> getAllAddressesByAscending();

    @Update
    void updateAddresses(MyAddress... addresses);


    @Query("SELECT * FROM address_table")
    LiveData<List<MyAddress>> getAllAddresses();

    @Insert
    void insertAll(MyAddress... addresses);

    @Query("SELECT COUNT(*) FROM address_table")
    int getAllAddressesCount();

    @Delete
    void deleteAddresses(MyAddress... addresses);

    @Delete
    void deleteAddress(MyAddress address);

    @Update
    void update(MyAddress... address);

    ////////////////////////

/*
    @Query("select * from saved_address")
    LiveData<List<MyAddress>> getAllAddressLive();*/

   /* @Query("select * from MyAddress where id = id")
    MyAddress getAddressById(int id);*/



}
