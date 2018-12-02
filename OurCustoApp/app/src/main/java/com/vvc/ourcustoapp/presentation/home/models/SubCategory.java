package com.vvc.ourcustoapp.presentation.home.models;

import android.graphics.drawable.Drawable;

import com.vvc.ourcustoapp.presentation.home.viewholders.SubCategoryViewHolder;

public class SubCategory {

    private Drawable drawable;
    private String sub_title;

    public SubCategory(Drawable drawable, String sub_title) {
        this.drawable = drawable;
        this.sub_title = sub_title;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }
}
