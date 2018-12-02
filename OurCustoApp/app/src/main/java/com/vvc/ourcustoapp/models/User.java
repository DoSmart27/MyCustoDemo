package com.vvc.ourcustoapp.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    private String name;
    @SerializedName("mobileNo")
    private String mobileNo;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public User(String name, String mobileNo, String email, String password) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        this.password = password;
    }

}

