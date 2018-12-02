package com.vvc.ourcustoapp.presentation.offers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;

public class RestaurantOffersFrag extends Fragment {

    public RestaurantOffersFrag() {
        // Required empty public constructor
    }

    public static RestaurantOffersFrag newInstance() {
        return  new RestaurantOffersFrag();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers_restaurant, container, false);
        initGui(view);
        return view;
    }
    private void initGui(View view) {

    }


}
