package com.vvc.ourcustoapp.presentation.offers.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.BaseActivity;
import com.vvc.ourcustoapp.presentation.adapters.ViewPagerAdapter;
import com.vvc.ourcustoapp.presentation.offers.fragments.OtherOffersFrag;
import com.vvc.ourcustoapp.presentation.offers.fragments.RestaurantOffersFrag;

public class OffersActivity extends BaseActivity {
    @Override
    public int setLayoutResource() {
        return R.layout.activity_offers;
    }

    @Override
    public void initGUI() {
        ((AppCompatTextView)findViewById(R.id.title_toolbar)).setText(getString(R.string.title_offers));
        findViewById(R.id.back_arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewPager viewPager = findViewById(R.id.viewpager_offers);
        setupViewPager(viewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.layout_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new RestaurantOffersFrag(), getString(R.string.title_restaurant_offers));
        adapter.addFrag(new OtherOffersFrag(), getString(R.string.title_other_offers));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }
}
