package com.vvc.ourcustoapp.presentation.home.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.models.Restaurant;

import java.util.List;

public class AllRestaurantAdapter extends RecyclerView.Adapter<AllRestaurantAdapter.RestaurantViewHolder>  {

    public interface OnItemClickListener {
        void onItemClick(Restaurant item);
    }

    private LayoutInflater mInflater;
    private List<Restaurant> restaurantList;

    private OnItemClickListener listener;
    private Context context;
    public AllRestaurantAdapter(Context context,OnItemClickListener listener) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_restaurant, parent, false);
        return new AllRestaurantAdapter.RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(restaurantList.get(position),listener);
    }


    @Override
    public int getItemCount() {
        if (restaurantList != null)
            return restaurantList.size();
        else
            return 0;
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView restaurant_name, restaurant_sub_names,restaurant_offers,restaurant_rating,restaurant_reviews,
                restaurant_deliver_time,text_closes_soon;
        private AppCompatImageView restaurant_image;

        private RestaurantViewHolder(View itemView) {
            super(itemView);
            restaurant_image = itemView.findViewById(R.id.restaurant_image);
            restaurant_name = itemView.findViewById(R.id.restaurant_name);
            restaurant_sub_names = itemView.findViewById(R.id.restaurant_sub_names);
            restaurant_offers = itemView.findViewById(R.id.restaurant_offers);
            restaurant_rating = itemView.findViewById(R.id.restaurant_rating);
            restaurant_reviews = itemView.findViewById(R.id.restaurant_reviews);
            restaurant_deliver_time = itemView.findViewById(R.id.restaurant_deliver_time);
            text_closes_soon = itemView.findViewById(R.id.text_closes_soon);

        }

        void bind(Restaurant restaurant, OnItemClickListener listener) {
            restaurant_name.setText(restaurant.getName());
            restaurant_sub_names.setText(restaurant.getCuisine_names());
            restaurant_offers.setText(restaurant.getOffers());
            restaurant_rating.setText(restaurant.getRating());
            restaurant_reviews.setText(restaurant.getReviews()+" "+context.getString(R.string.title_reviews));
            restaurant_deliver_time.setText(restaurant.getDeliver_time()+" "+context.getString(R.string.title_minutes));
            restaurant_name.setText(restaurant.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(restaurant);
                }
            });
        }
    }


    public void setRestaurantList(List<Restaurant> restaurants) {
        restaurantList = restaurants;
        notifyDataSetChanged();
    }

    public Restaurant getRestaurantAtPosition(int position) {
        return restaurantList.get(position);
    }


}
