package com.vvc.ourcustoapp.custom.textviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomTextViewSemiBold extends AppCompatTextView {


    public CustomTextViewSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewSemiBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-SemiBold.ttf");
        setTypeface(tf);
    }

}