package com.vvc.ourcustoapp.models;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

public class Restaurant {

    private String name, cuisine_names, image_url;
    private String offers, rating, reviews, deliver_time;

    public Restaurant(String name, String cuisine_names, String image_url, String offers, String rating, String reviews, String deliver_time) {
        this.name = name;
        this.cuisine_names = cuisine_names;
        this.image_url = image_url;
        this.offers = offers;
        this.rating = rating;
        this.reviews = reviews;
        this.deliver_time = deliver_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine_names() {
        return cuisine_names;
    }

    public void setCuisine_names(String cuisine_names) {
        this.cuisine_names = cuisine_names;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(String deliver_time) {
        this.deliver_time = deliver_time;
    }


    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
