package com.vvc.ourcustoapp.presentation.home.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.models.Restaurant;
import com.vvc.ourcustoapp.models.SingleItemModel;
import com.vvc.ourcustoapp.presentation.home.adapters.AllRestaurantAdapter;
import com.vvc.ourcustoapp.presentation.home.adapters.CustomPagerAdapter;
import com.vvc.ourcustoapp.presentation.home.adapters.RecyclerViewDataAdapter;
import com.vvc.ourcustoapp.presentation.home.models.SectionDataModel;
import com.vvc.ourcustoapp.presentation.restaurant.activities.RestaurantMenuActivity;

import java.util.ArrayList;
import java.util.List;

public class RecommendedFrag extends Fragment {

    ArrayList<SectionDataModel> allSampleData;

    public RecommendedFrag() {
        // Required empty public constructor
    }

    public static RecommendedFrag newInstance() {
        return new RecommendedFrag();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        initGui(view);
        return view;
    }

    private void initGui(View view) {

        ViewPager viewPager = view.findViewById(R.id.pager_slider);
        CustomPagerAdapter customPagerAdapter  = new CustomPagerAdapter(getContext());
        viewPager.setAdapter(customPagerAdapter);

        allSampleData = new ArrayList<SectionDataModel>();
        createDummyData();

        RecyclerView my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        my_recycler_view.setHasFixedSize(true);
        RecyclerViewDataAdapter adapter = new RecyclerViewDataAdapter(getContext(), allSampleData);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        my_recycler_view.setAdapter(adapter);

        RecyclerView recycler_favourites = (RecyclerView) view.findViewById(R.id.recycler_favourites);
        getFavouriteListFromDB(recycler_favourites);
    }


    public void createDummyData() {

            SectionDataModel dm = new SectionDataModel();

            dm.setHeaderTitle(getString(R.string.title_browse_by_cuisine));

            ArrayList<SingleItemModel> singleItem = new ArrayList<SingleItemModel>();
            for (int j = 0; j < 5; j++) {
                singleItem.add(new SingleItemModel("Ice Cream " + j, "URL " + j));
            }

            dm.setAllItemsInSection(singleItem);

            allSampleData.add(dm);

    }


    private void getFavouriteListFromDB(RecyclerView recycler_favourites)
    {
        AllRestaurantAdapter restaurantAdapter = new AllRestaurantAdapter(getContext(), new AllRestaurantAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Restaurant item) {
                Intent intent = new Intent(getContext(),RestaurantMenuActivity.class);
                intent.putExtra("data",item.toString());
                startActivity(intent);
            }
        });
        recycler_favourites.setLayoutManager(new LinearLayoutManager(getContext()));


        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));
        restaurants.add(new Restaurant("Pista House","Biryani, North Indian, Tandoor","","25% off on select categories","2.7","500","30"));

        restaurantAdapter.setRestaurantList(restaurants);
        recycler_favourites.setAdapter(restaurantAdapter);
    }
}
