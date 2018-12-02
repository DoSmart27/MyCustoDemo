package com.vvc.ourcustoapp.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.AnyRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.vvc.ourcustoapp.BuildConfig;
import com.vvc.ourcustoapp.CustomerApplication;
import com.vvc.ourcustoapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidUtils {

    public static String getBuildVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static int getBuildVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    public static String getModelName() {
        return Build.MODEL;
    }

    public static int getOSVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static Calendar getDate(String date, String timeFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(date)) {
            calendar.setTime(simpleDateFormat.parse(date));
        }
        return calendar;
    }

    public static String getDateInStringFormat(Calendar calendar, String timeFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat, Locale.getDefault());
        return simpleDateFormat.format(calendar.getTime());
    }

    public static void hideKeyBoard(Context context) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public static void showMessage(View view, String message, String actionButtonText, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.setAction(actionButtonText, onClickListener);
        snackbar.setActionTextColor(view.getContext().getResources().getColor(R.color.bg_yellow_light));
        snackbar.show();
    }

    public static void showMessage(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        view = snackbar.getView();
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setMaxLines(3);
        snackbar.show();
    }

    public static void showToastForIndefinite(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    public static void showToastLong(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static int convertPixelsToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int convertDpToPixel(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static String encodeToBase64(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.NO_WRAP);
    }


    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) CustomerApplication.getApplicationInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static boolean isValidEmailAddress(String email) {
        Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        return emailPattern.matcher(email).matches();
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        Pattern mobilePattern = Pattern.compile("^[0-9]{10}$");
        return mobilePattern.matcher(mobileNumber).matches();
    }

    public static boolean isValidPassword(String password) {
        return password.length() > 5;
    }

    public static byte[] convertBitmapToByte(Bitmap bitmap) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //bitmap to byte[] stream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] x = stream.toByteArray();
            //close stream to save memory
            stream.close();
            return x;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap convertByteToBitmap(byte[] imageData) {
       /* if (imageData != null)
            return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
        else
            return null;*/
        return imageData != null ? BitmapFactory.decodeByteArray(imageData, 0, imageData.length) : null;
    }

    public static Bitmap convertVectorBitmapToBitmap(Drawable drawable) {
        try {
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Handle the error
            return null;
        }
    }

    public static Bitmap getBitmapFromDrawable(Context context, @DrawableRes int drawableId) {
        Drawable drawable = AppCompatResources.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawableCompat || drawable instanceof VectorDrawable) {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    /*public static void forwardFragmentReplace(Context activity, Fragment fragment, Bundle arguments, String tag)
    {
        FragmentTransaction ft = ((FullScreenActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragment.setArguments(arguments);
        ft.replace(R.id.fragment_container, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }*/

    public static void dialogFragmentShowRight(Context context, Fragment newFragment) {
        FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }


    /*public static void forwardFragmentReplace(Context activity, Fragment fragment, Bundle arguments, String tag)
    {
        FragmentTransaction ft = ((FullScreenActivity)activity).getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        fragment.setArguments(arguments);
        ft.replace(R.id.fragment_container, fragment, tag);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }
*/

    public static void promptSpeechToText(Activity activity) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        //intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));
        try {
            activity.startActivityForResult(intent, Constants.REQUEST_SEARCH_MIC);
        } catch (ActivityNotFoundException a) {
            //showMessage();
            //Toast.makeText(getApplicationContext(), getString(R.string.speech_not_supported), Toast.LENGTH_SHORT).show();
        }
    }


    public static Uri getUriToDrawable(@NonNull Context context, @AnyRes int drawableId) {
        Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + context.getResources().getResourcePackageName(drawableId)
                + '/' + context.getResources().getResourceTypeName(drawableId)
                + '/' + context.getResources().getResourceEntryName(drawableId));
        return imageUri;
    }

    public static void inviteWithLink(Context context, String appName, String sharedMessage, Uri uri) {
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, appName);
            // String strShareMessage = "\nLet me recommend you this application\n\n";
            // strShareMessage = strShareMessage + "https://play.google.com/store/apps/details?id=" + getPackageName();

            if (uri != null) {
                //Uri screenshotUri = Uri.parse("android.resource://packagename/drawable/image_name");
                // i.setType("image/png");
                i.putExtra(Intent.EXTRA_STREAM, uri);
            }

            i.putExtra(Intent.EXTRA_TEXT, sharedMessage);
            context.startActivity(Intent.createChooser(i, context.getString(R.string.title_invite_friends)));
        } catch (Exception e) {
            //e.toString();
        }
    }


}
