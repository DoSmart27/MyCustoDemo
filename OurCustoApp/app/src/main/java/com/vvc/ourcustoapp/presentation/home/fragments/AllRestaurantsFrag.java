package com.vvc.ourcustoapp.presentation.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.models.Restaurant;
import com.vvc.ourcustoapp.presentation.home.adapters.AllRestaurantAdapter;
import com.vvc.ourcustoapp.presentation.restaurant.activities.RestaurantMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class AllRestaurantsFrag extends Fragment {

    public AllRestaurantsFrag() {
        // Required empty public constructor
    }
    public static AllRestaurantsFrag newInstance() {
        return new AllRestaurantsFrag();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_restaurants, container, false);
        initGui(view);
        return view;
    }

    private void initGui(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.restaurant_recycler);

        AllRestaurantAdapter restaurantAdapter = new AllRestaurantAdapter(getContext(), new AllRestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Restaurant item) {
                Intent intent = new Intent(getContext(),RestaurantMenuActivity.class);
                intent.putExtra("data",item.toString());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));

        restaurantAdapter.setRestaurantList(restaurants);
        recyclerView.setAdapter(restaurantAdapter);

        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0 ||dy<0 )
                {
                   // bottomBar.setVisibility(View.GONE);

                    ((HomeActivity) getActivity()).findViewById(R.id.bottom_navigation).setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    ((HomeActivity) getActivity()).findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });*/
    }
}
