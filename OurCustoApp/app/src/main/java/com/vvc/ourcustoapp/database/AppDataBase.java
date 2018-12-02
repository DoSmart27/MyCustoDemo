package com.vvc.ourcustoapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.vvc.ourcustoapp.database.dao.AddressDao;
import com.vvc.ourcustoapp.database.tables.MyAddress;

@Database( version = 1, entities = {MyAddress.class}, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    abstract public AddressDao getAddressDao();

}
