package com.vvc.ourcustoapp.presentation.login.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.vvc.ourcustoapp.CustomerApplication;
import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.common.webservices.AppWebService;
import com.vvc.ourcustoapp.custom.edittext.PrefixEditText;
import com.vvc.ourcustoapp.models.User;
import com.vvc.ourcustoapp.utils.AndroidUtils;

@SuppressLint("ValidFragment")
public class SignUpDialogFragment extends DialogFragment {

    private Context context;
    private String mobile;
    private PrefixEditText mobile_number;
    private AppWebService mAppWebService;


    private AppCompatEditText input_name, input_email;
    EditText input_new_pwd, input_conform_pwd;

    public SignUpDialogFragment(Context context, String mobile) {
        this.context = context;
        this.mobile = mobile;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_sign_up, container, false);
        iniView(view);
        return view;
    }

    private void iniView(View view) {
        mAppWebService = CustomerApplication.getApplicationInstance().getAppWebService();
        input_name = (AppCompatEditText) view.findViewById(R.id.input_name);
        input_email = (AppCompatEditText) view.findViewById(R.id.input_email);
        input_new_pwd = (EditText) view.findViewById(R.id.input_new_password);
        input_conform_pwd = (EditText) view.findViewById(R.id.input_conform_password);

        mobile_number = (PrefixEditText) view.findViewById(R.id.input_mobile);
        if (mobile != null)
            mobile_number.setText(mobile);
        else
            mobile_number.setText("");


        ((AppCompatButton) view.findViewById(R.id.submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input_name.getText().toString().trim();
                String email = input_email.getText().toString().trim();
                String mobile = mobile_number.getText().toString().trim();
                String new_pwd = input_new_pwd.getText().toString().trim();
                String conform_pwd = input_conform_pwd.getText().toString().trim();
                validateSignUp(name,email,mobile,new_pwd,conform_pwd);
            }
        });

    }

    private void validateSignUp(String name, String email, String mobile, String new_pwd, String conform_pwd) {

        if (TextUtils.isEmpty(name)) {
            AndroidUtils.showMessage(input_name, getString(R.string.enter_name));
        } else {
            if (TextUtils.isEmpty(mobile)) {
                AndroidUtils.showMessage(mobile_number, getString(R.string.enter_mobile));
            } else {
                if (!AndroidUtils.isValidMobileNumber(mobile) || mobile.length() < 10)
                    AndroidUtils.showMessage(input_email, getString(R.string.enter_valid_mobile));
                else {
                    if (TextUtils.isEmpty(email)) {
                        AndroidUtils.showMessage(input_email, getString(R.string.enter_email));
                    } else {
                        if (!AndroidUtils.isValidEmailAddress(email)) {
                            AndroidUtils.showMessage(input_email, getString(R.string.enter_valid_email));
                        } else {
                            if (TextUtils.isEmpty(new_pwd)) {
                                AndroidUtils.showMessage(input_new_pwd, getString(R.string.enter_new_pwd));
                            } else {
                                if (new_pwd.length() < 5) {
                                    AndroidUtils.showMessage(input_new_pwd, getString(R.string.password_must_greater));
                                } else {
                                    if (TextUtils.isEmpty(conform_pwd)) {
                                        AndroidUtils.showMessage(input_conform_pwd, getString(R.string.enter_confirm_pwd));
                                    } else {
                                        if (conform_pwd.length()< 5) {
                                            AndroidUtils.showMessage(input_conform_pwd, getString(R.string.password_must_greater));
                                        } else {
                                            if (!new_pwd.equalsIgnoreCase(conform_pwd)) {
                                                AndroidUtils.showMessage(input_conform_pwd, getString(R.string.password_must_same));
                                            } else {
                                                // callServiceSignUp(name, email, mobile, conform_pwd);
                                                Toast.makeText(context, " Call Sign up ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void callServiceSignUp(String name, String email, String mobile, String conform_pwd) {
        User user = new User(name,mobile,email,conform_pwd);
        mAppWebService.doSignUp(user);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
