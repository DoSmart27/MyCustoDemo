package com.vvc.ourcustoapp.presentation.restaurant.activities;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.BaseActivity;
import com.vvc.ourcustoapp.models.Restaurant;

public class RestaurantMenuActivity extends BaseActivity {

    private AppCompatCheckBox check_fav;
    private AppCompatImageView back_arrow;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_restaurant_menu;
    }

    @Override
    public void initGUI() {

        String restaurantData = getIntent().getExtras().getString("data");
        Restaurant restaurant = new Gson().fromJson(restaurantData, Restaurant.class);
        initCollapsingToolbar(restaurant.getName());
        Toast.makeText(this, " In " + restaurant.getName(), Toast.LENGTH_SHORT).show();

        back_arrow = findViewById(R.id.back_arrow);
        back_arrow.setColorFilter(Color.WHITE);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((SwitchCompat)findViewById(R.id.toggleButton)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    //sort & show veg items
                    Toast.makeText(RestaurantMenuActivity.this, "Sort it", Toast.LENGTH_SHORT).show();
                }else
                {
                    //revert the changes.
                    Toast.makeText(RestaurantMenuActivity.this, "revert it.", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    @Override
    public void initData() {
        check_fav = findViewById(R.id.check_fav);
        check_fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Toast.makeText(RestaurantMenuActivity.this, "tag to fav.", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(RestaurantMenuActivity.this, "Untag.", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void initCollapsingToolbar(String name) {
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setContentScrimColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.setExpanded(true);
        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    // Results results = intent.getParcelableExtra(Constant.PARCELABLE_MOVIE_TITLE);
                    collapsingToolbarLayout.setTitle(name);
                    changeColor(Color.BLACK);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    changeColor(Color.WHITE);
                    isShow = false;
                }
            }
        });
    }

    private void changeColor(int color) {
        back_arrow.setColorFilter(color);
    }

    /*private void setToggleChangeColorSelected(int homeColor, int searchColor, int pastOrderColor, int accountColor) {
        check_fav.setba(homeColor);
        back_arrow.setColorFilter(homeColor);

        findViewById(R.id.back_arrow)
        search_text.setTextColor(searchColor);
        search_image.setColorFilter(searchColor);

        past_order_text.setTextColor(pastOrderColor);
        past_order_image.setColorFilter(pastOrderColor);

        account_text.setTextColor(accountColor);
        account_image.setColorFilter(accountColor);
    }*/
}
