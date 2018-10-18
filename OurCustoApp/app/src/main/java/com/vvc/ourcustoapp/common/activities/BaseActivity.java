package com.vvc.ourcustoapp.common.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        setContentView(setLayoutResource());


        initGUI();
        initData();
    }

    public abstract int setLayoutResource();

    public abstract void initGUI();

    public abstract void initData();
}
