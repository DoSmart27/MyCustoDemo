package com.vvc.ourcustoapp.presentation.offers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;

public class OtherOffersFrag extends Fragment {

    public OtherOffersFrag() {
        // Required empty public constructor
    }

    public static OtherOffersFrag newInstance() {
        return  new OtherOffersFrag();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers_other, container, false);
        initGui(view);
        return view;
    }
    private void initGui(View view) {

    }

}
