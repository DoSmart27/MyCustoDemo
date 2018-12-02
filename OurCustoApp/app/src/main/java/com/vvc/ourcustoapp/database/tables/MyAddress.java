package com.vvc.ourcustoapp.database.tables;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

@Entity(tableName = "address_table",indices = {@Index(value = "tag", unique = true)})
public class MyAddress {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "tag")
    public String tag;

    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    private byte[] image;

    @ColumnInfo(name = "address")
    public String address;  // is nothing but location with fully string

    @ColumnInfo(name = "admin_area")
    public String admin_area;

    @ColumnInfo(name = "sub_admin_area")
    public String sub_admin_area;

    @ColumnInfo(name = "pin_code")
    private String pinCode; //509001

    @ColumnInfo(name = "house_no")
    private String house_no;

    @ColumnInfo(name = "user_area")
    private String user_area;

    @ColumnInfo(name = "landmark")
    public String landmark;

    @ColumnInfo(name = "address_latitude")
    private String address_latitude;

    @ColumnInfo(name = "address_longitude")
    private String address_longitude;

    @ColumnInfo(name = "locality")
    private String locality; // Hyderabad, MBNR, Bhothpur

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "sub_locality")
    private String sub_locality;

    @ColumnInfo(name = "feature_name")
    private String feature_name;

    @ColumnInfo(name = "thorough_fare")
    private String thorough_fare;


    @Ignore //will ignore while building
    public MyAddress(String tag, byte[] image ,String address, String admin_area, String sub_admin_area, String pinCode, String house_no, String user_area, String landmark, String address_latitude, String address_longitude, String locality, String country, String sub_locality, String feature_name, String thorough_fare) {
        this.tag = tag;
        this.image = image;
        this.address = address;
        this.admin_area = admin_area;
        this.sub_admin_area = sub_admin_area;
        this.pinCode = pinCode;
        this.house_no = house_no;
        this.user_area = user_area;
        this.landmark = landmark;
        this.address_latitude = address_latitude;
        this.address_longitude = address_longitude;
        this.locality = locality;
        this.country = country;
        this.sub_locality = sub_locality;
        this.feature_name = feature_name;
        this.thorough_fare = thorough_fare;
    }

    public MyAddress(String address, String pinCode, String locality, String sub_locality, String address_latitude, String address_longitude, String country, String admin_area, String sub_admin_area, String feature_name, String thorough_fare)
    {
        this.address=address;
        this.pinCode=pinCode;
        this.locality=locality;
        this.sub_locality=sub_locality;
        this.address_latitude=address_latitude;
        this.address_longitude=address_longitude;
        this.country=country;
        this.admin_area=admin_area;
        this.sub_admin_area=sub_admin_area;
        this.feature_name=feature_name;
        this.thorough_fare=thorough_fare;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdmin_area() {
        return admin_area;
    }

    public void setAdmin_area(String admin_area) {
        this.admin_area = admin_area;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getHouse_no() {
        return house_no;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public String getAddress_latitude() {
        return address_latitude;
    }

    public void setAddress_latitude(String address_latitude) {
        this.address_latitude = address_latitude;
    }

    public String getAddress_longitude() {
        return address_longitude;
    }

    public void setAddress_longitude(String address_longitude) {
        this.address_longitude = address_longitude;
    }

    public String getSub_admin_area() {
        return sub_admin_area;
    }

    public void setSub_admin_area(String sub_admin_area) {
        this.sub_admin_area = sub_admin_area;
    }

    public String getUser_area() {
        return user_area;
    }

    public void setUser_area(String user_area) {
        this.user_area = user_area;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSub_locality() {
        return sub_locality;
    }

    public void setSub_locality(String sub_locality) {
        this.sub_locality = sub_locality;
    }

    public String getFeature_name() {
        return feature_name;
    }

    public void setFeature_name(String feature_name) {
        this.feature_name = feature_name;
    }

    public String getThorough_fare() {
        return thorough_fare;
    }

    public void setThorough_fare(String thorough_fare) {
        this.thorough_fare = thorough_fare;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
