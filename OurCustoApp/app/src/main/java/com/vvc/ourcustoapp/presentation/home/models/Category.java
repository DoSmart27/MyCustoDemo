package com.vvc.ourcustoapp.presentation.home.models;

import com.vvc.ourcustoapp.custom.expandableRecycler.ParentListItem;

import java.util.List;

public class Category implements ParentListItem {

    private String mName;
    private List<SubCategory> mSubCategories;

    public Category(String mName, List<SubCategory> mSubCategories) {
        this.mName = mName;
        this.mSubCategories = mSubCategories;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    @Override
    public List<?> getChildItemList() {
        return mSubCategories;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
