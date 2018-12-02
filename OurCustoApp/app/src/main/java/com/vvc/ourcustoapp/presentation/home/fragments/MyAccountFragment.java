package com.vvc.ourcustoapp.presentation.home.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.activities.SplashScreenActivity;
import com.vvc.ourcustoapp.presentation.home.activities.FavouriteActivity;
import com.vvc.ourcustoapp.presentation.home.activities.ManageAddresses;
import com.vvc.ourcustoapp.presentation.home.adapters.CategoryAdapter;
import com.vvc.ourcustoapp.presentation.home.models.Category;
import com.vvc.ourcustoapp.presentation.home.models.SubCategory;
import com.vvc.ourcustoapp.presentation.home.viewholders.SubCategoryViewHolder;
import com.vvc.ourcustoapp.presentation.offers.activities.OffersActivity;
import com.vvc.ourcustoapp.utils.AndroidUtils;
import com.vvc.ourcustoapp.utils.GlobalMethods;
import com.vvc.ourcustoapp.utils.SharedUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MyAccountFragment extends Fragment {

    private RecyclerView recyclerView;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        initGui(view);
        return view;
    }

    private void initGui(View view) {

        if (SharedUtils.isLoggedIn(getActivity()))
            toggleScreens(view,1);
        else
            toggleScreens(view,0);
        ((AppCompatImageView) view.findViewById(R.id.back_arrow)).setVisibility(View.GONE);
        ((AppCompatTextView) view.findViewById(R.id.title_toolbar)).setText(getString(R.string.title_account));
        String version = getString(R.string.title_app_version) + " " + AndroidUtils.getBuildVersionName();
        ((AppCompatTextView) view.findViewById(R.id.app_version)).setText(version);
        recyclerView = view.findViewById(R.id.recycler_my_profile);
        buildListItems();

        ((CardView) view.findViewById(R.id.card_logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Call Logout", Toast.LENGTH_SHORT).show();

                SharedUtils.setLogin(getContext(), false);

                toggleScreens(view, 0);
            }
        });


        view.findViewById(R.id.edit_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet("edit_profile");
            }
        });

        view.findViewById(R.id.layout_offers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOffers();
            }
        });
        view.findViewById(R.id.layout_feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();

            }
        });
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.showLoginBottomSheetDialog(getActivity(),true);
            }
        });


    }

    private void toggleScreens(View view, int status) {
        switch (status) {
            case 0:
                view.findViewById(R.id.layout_logged_in).setVisibility(View.GONE);
                view.findViewById(R.id.layout_log_out).setVisibility(View.VISIBLE);
                break;
            case 1:
                view.findViewById(R.id.layout_logged_in).setVisibility(View.VISIBLE);
                view.findViewById(R.id.layout_log_out).setVisibility(View.GONE);
                break;
        }

    }

    private void buildListItems() {
        SubCategory manageAddress = new SubCategory(getResources().getDrawable(R.drawable.ic_home_black_24dp), getString(R.string.manage_address));
        SubCategory favourite = new SubCategory(getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24px), getString(R.string.title_favourite));
        SubCategory offers = new SubCategory(getResources().getDrawable(R.drawable.ic_baseline_local_offer_24px), getString(R.string.title_offers));
        SubCategory referral = new SubCategory(getResources().getDrawable(R.drawable.ic_baseline_mobile_screen_share_24px), getString(R.string.title_referral));
        SubCategory feedBack = new SubCategory(getResources().getDrawable(R.drawable.ic_baseline_feedback_24px), getString(R.string.title_feedback));
        SubCategory change = new SubCategory(getResources().getDrawable(R.drawable.ic_baseline_vpn_key_24px), getString(R.string.title_change_pwd));

        Category category = new Category(getString(R.string.title_my_details), Arrays.asList(manageAddress, favourite, offers, referral, feedBack, change));

        List<Category> categoryList = Collections.singletonList(category); // or  Arrays.asList();
        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), categoryList, new SubCategoryViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(String subTitle) {
                doOperations(subTitle);
            }
        });
        categoryAdapter.expandParent(0);
        //categoryAdapter.expandAllParents();
        categoryAdapter.expandParent(category);
        /*categoryAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onListItemExpanded(int position) {

               // Category category1 =categoryList.get(position);
               // Toast.makeText(getActivity(), "Exp:  "+category1.getName()+"pos: "+position, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onListItemCollapsed(int position) {

             //   Category category1 =categoryList.get(position);
              //  Toast.makeText(getActivity(), "Collapsed:  "+category1.getName(), Toast.LENGTH_SHORT).show();

            }
        });*/

        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void doOperations(String subTitle) {
        if (subTitle.equalsIgnoreCase(getString(R.string.manage_address))) {
            openManageAddress();
        } else if (subTitle.equalsIgnoreCase(getString(R.string.title_favourite))) {
            showFavourite();
        } else if (subTitle.equalsIgnoreCase(getString(R.string.title_offers))) {
            openOffers();
        } else if (subTitle.equalsIgnoreCase(getString(R.string.title_referral))) {
            showReferral();
        } else if (subTitle.equalsIgnoreCase(getString(R.string.title_feedback))) {
            sendFeedback();
        } else if (subTitle.equalsIgnoreCase(getString(R.string.title_change_pwd))) {
            changePassword();
        }
    }

    private void showFavourite() {
        startActivity(new Intent(getActivity(), FavouriteActivity.class));
    }

    private void openOffers() {
        startActivity(new Intent(getActivity(), OffersActivity.class));
    }

    private void showReferral() {
        showBottomSheet("referral");
    }

    private void openManageAddress() {
        startActivity(new Intent(getActivity(), ManageAddresses.class));
    }

    private void sendFeedback() {
        String user_name = getUserName();
        String body = "\n\n\n\n\nOurApp Android: " + AndroidUtils.getBuildVersionCode() + " (" + AndroidUtils.getBuildVersionName() + ")\n"
                + "Device: " + AndroidUtils.getModelName() + "\n Android Version: " + AndroidUtils.getOSVersion() +
                "\n User: " + user_name;

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "app-feedback@abc.in", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, user_name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(emailIntent, "Send Email..."));


     /* emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");*/
    }

    private void changePassword() {
        showBottomSheet("change_password");
    }

    private String getUserName() {
        return getString(R.string.send_feedback_default);
    }

   /* @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        categoryAdapter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        categoryAdapter.onRestoreInstanceState(savedInstanceState);
    }*/

    private BottomSheetDialog mBottomSheetDialog;
    private View bottomSheetLayout;

    private void showBottomSheet(String source) {
        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        switch (source) {
            case "referral":
                bottomSheetLayout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_referral, null);
                bottomSheetLayout.findViewById(R.id.refer_earn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String shareMessage = "Get Register on Swiggy with YASCP and earn Rs. 10.\nDownload an app from PlayStore\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName();
                        Uri shareUri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/drawable/ic_launcher_background.xml");
                        AndroidUtils.inviteWithLink(getActivity(), "App Name", shareMessage, shareUri);
                    }
                });
                break;
            case "change_password":
                bottomSheetLayout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_change_pwd, null);
                bottomSheetLayout.findViewById(R.id.change_submit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Change Submit", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            case "edit_profile":
                bottomSheetLayout = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_sheet_edit_profile, null);
                ((AppCompatEditText) bottomSheetLayout.findViewById(R.id.input_mobile)).setText("");
                ((AppCompatEditText) bottomSheetLayout.findViewById(R.id.input_email)).setText("");
                ((AppCompatEditText) bottomSheetLayout.findViewById(R.id.input_name)).setText("");
                bottomSheetLayout.findViewById(R.id.change_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Change Update", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
        ((AppCompatImageButton) bottomSheetLayout.findViewById(R.id.button_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.show();
    }

   /* private void onInviteClicked() {
        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invitation_title))
                .setMessage(getString(R.string.invitation_message))
                .setDeepLink(Uri.parse(getString(R.string.invitation_deep_link)))
                .setCustomImage(Uri.parse(getString(R.string.invitation_custom_image)))
                .setCallToActionText(getString(R.string.invitation_cta))
                .build();
        startActivityForResult(intent, 1215);
    }*/
}
