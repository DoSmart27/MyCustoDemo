package com.vvc.ourcustoapp.presentation.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.presentation.offers.activities.OffersActivity;

public class HomeViewPagerFragment extends Fragment {

    public HomeViewPagerFragment() {
        // Required empty public constructor
    }

    public static HomeViewPagerFragment newInstance() {
        return new HomeViewPagerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        ((AppCompatImageView) view.findViewById(R.id.offers_icon)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OffersActivity.class));
            }
        });
        return view;
    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final int NUM_ITEMS = 2;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return RecommendedFrag.newInstance();
            } else {
                return AllRestaurantsFrag.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return getResources().getString(R.string.title_recommended);
            } else {
                return getResources().getString(R.string.title_all_restaurants);
            }
        }
    }
}
