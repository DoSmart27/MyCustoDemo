package com.vvc.ourcustoapp.custom.textviews;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class CustomTextView extends AppCompatTextView {


    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context) {
        super(context);
        init();
    }


    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"Montserrat-Regular.ttf");
        setTypeface(tf);
    }

}