package com.vvc.ourcustoapp.presentation.home.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;

public class OrderViewPagerFragment extends Fragment {

    public OrderViewPagerFragment() {
        // Required empty public constructor
    }

    public static OrderViewPagerFragment newInstance() {
        return new OrderViewPagerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


    public  class ViewPagerAdapter extends FragmentStatePagerAdapter {
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
            if(position == 0){
                return CurrentOrdersFrag.newInstance();
            }
            else {
                return PastOrdersFrag.newInstance();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0){
                return getResources().getString(R.string.title_current_order);
            }
            else {
                return getResources().getString(R.string.title_past_order);
            }
        }
    }
}
