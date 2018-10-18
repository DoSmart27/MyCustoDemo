package com.vvc.ourcustoapp.custom.textviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomTextViewMedium extends AppCompatTextView {


    public CustomTextViewMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewMedium(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Medium.ttf");
        setTypeface(tf);
    }

}