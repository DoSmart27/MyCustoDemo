package com.vvc.ourcustoapp.presentation.home.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;

public class CurrentOrdersFrag extends Fragment {

    public CurrentOrdersFrag() {
        // Required empty public constructor
    }

    public static CurrentOrdersFrag newInstance() {
        return  new CurrentOrdersFrag();
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_orders, container, false);
        initGui(view);
        return view;
    }
    private void initGui(View view) {

    }

}
