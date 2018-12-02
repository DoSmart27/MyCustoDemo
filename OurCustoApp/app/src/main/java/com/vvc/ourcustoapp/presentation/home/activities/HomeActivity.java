package com.vvc.ourcustoapp.presentation.home.activities;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.BaseActivity;

import com.vvc.ourcustoapp.presentation.home.fragments.HomeViewPagerFragment;
import com.vvc.ourcustoapp.presentation.home.fragments.MyAccountFragment;
import com.vvc.ourcustoapp.presentation.home.fragments.OrderViewPagerFragment;
import com.vvc.ourcustoapp.presentation.home.fragments.SearchFragment;


public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private boolean doubleBackToExitPressedOnce = false;
    private BottomNavigationView bottomNavigationView;


    @Override
    public int setLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void initGUI() {
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        removeNavigationShiftMode(bottomNavigationView);
        fragmentManager.beginTransaction().add(R.id.fragment_container, HomeViewPagerFragment.newInstance()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        // setupViewPager(viewPager);
        // tabLayout = (TabLayout) findViewById(R.id.tabs);
        // tabLayout.setupWithViewPager(viewPager);
        // tabLayout.setSelectedTabIndicatorHeight(0);
        // tabLayout.setSelectedTabIndicator(0);
        // setUpTabIcons();
        // TabLayout.Tab tab = tabLayout.getTabAt(0);
        //  assert tab != null;
        // tab.select();
    }


    @Override
    public void initData() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                replaceFragment(HomeViewPagerFragment.newInstance());
                return true;
            case R.id.navigation_search:
                replaceFragment(SearchFragment.newInstance());
                return true;
            case R.id.navigation_orders:
                replaceFragment(OrderViewPagerFragment.newInstance());
                return true;
            case R.id.navigation_account:
                replaceFragment(MyAccountFragment.newInstance());
                return true;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.navigation_home) {
           /* super.onBackPressed();
            finish();*/
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                finish();
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, getString(R.string.press_back), Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @SuppressLint("RestrictedApi")
    public static void removeNavigationShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        menuView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        menuView.buildMenuView();
    }

    /* private void setupViewPager(ViewPager viewPager) {
        Fragment homeFragment = HomeFragment.newInstance();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(homeFragment, getString(R.string.title_home));
        adapter.addFrag(new SearchFragment(), getString(R.string.title_search));
        adapter.addFrag(new OrdersFragment(), getString(R.string.title_order));
        adapter.addFrag(new MyAccountFragment(), getString(R.string.title_account));
        viewPager.setAdapter(adapter);
    }

    private void setUpTabIcons() {
        setUpTab(0, getString(R.string.title_home), R.drawable.ic_home_black_24dp);
        setUpTab(1, getString(R.string.title_search), R.drawable.ic_baseline_search_24px);
        setUpTab(2, getString(R.string.title_order), R.drawable.ic_baseline_receipt_24px);
        setUpTab(3, getString(R.string.title_account), R.drawable.ic_baseline_account_box_24px);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setFragment(0);
                        setToggleChangeColorSelected(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.tab_unselected));
                        break;
                    case 1:
                        setFragment(1);
                        setToggleChangeColorSelected(getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.tab_unselected));
                        break;
                    case 2:
                        setFragment(2);
                        setToggleChangeColorSelected(getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.tab_unselected));
                        break;
                    case 3:
                        setFragment(3);
                        setToggleChangeColorSelected(getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.tab_unselected), getResources().getColor(R.color.colorPrimary));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpTab(int index, String tabName, int drawable) {
        @SuppressLint("InflateParams")
        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_home_bottom, null);
        switch (index) {
            case 0:
                home_text = (CustomTextView) tabOne.findViewById(R.id.tab);
                home_image = (AppCompatImageView) tabOne.findViewById(R.id.home_button_img);
                home_text.setText(tabName);
                home_image.setImageResource(drawable);
                // tabLayout.getTabAt(index).setCustomView(tabOne);
                break;

            case 1:
                search_text = (CustomTextView) tabOne.findViewById(R.id.tab);
                search_image = (AppCompatImageView) tabOne.findViewById(R.id.home_button_img);
                search_text.setText(tabName);
                search_image.setImageResource(drawable);
                // tabLayout.getTabAt(index).setCustomView(tabOne);
                break;

            case 2:
                past_order_text = (CustomTextView) tabOne.findViewById(R.id.tab);
                past_order_image = (AppCompatImageView) tabOne.findViewById(R.id.home_button_img);
                past_order_text.setText(tabName);
                past_order_image.setImageResource(drawable);
                // tabLayout.getTabAt(index).setCustomView(tabOne);
                break;

            case 3:
                account_text = (CustomTextView) tabOne.findViewById(R.id.tab);
                account_image = (AppCompatImageView) tabOne.findViewById(R.id.home_button_img);
                account_text.setText(tabName);
                account_image.setImageResource(drawable);
                // tabLayout.getTabAt(index).setCustomView(tabOne);
                break;
        }
    }

    private void setToggleChangeColorSelected(int homeColor, int searchColor, int pastOrderColor, int accountColor) {
        home_text.setTextColor(homeColor);
        home_image.setColorFilter(homeColor);

        search_text.setTextColor(searchColor);
        search_image.setColorFilter(searchColor);

        past_order_text.setTextColor(pastOrderColor);
        past_order_image.setColorFilter(pastOrderColor);

        account_text.setTextColor(accountColor);
        account_image.setColorFilter(accountColor);
    }*/

    /*private void setFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = HomeFragment.newInstance();
                break;

            case 1:
                fragment = SearchFragment.newInstance();
                break;

            case 2:
                fragment = OrdersFragment.newInstance();
                break;

            case 3:
                fragment = MyAccountFragment.newInstance();
                break;
        }

        loadFragment(fragment);
    }*/
}
