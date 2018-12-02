package com.vvc.ourcustoapp.presentation.home.activities;

import android.support.v7.widget.AppCompatTextView;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.BaseActivity;

public class FavouriteActivity extends BaseActivity {
    @Override
    public int setLayoutResource() {
        return R.layout.activity_favourite;
    }

    @Override
    public void initGUI() {
        ((AppCompatTextView)findViewById(R.id.title_toolbar)).setText(getString(R.string.title_favourite));
        findViewById(R.id.back_arrow).setOnClickListener(v -> finish());

    }

    @Override
    public void initData() {

    }
}
