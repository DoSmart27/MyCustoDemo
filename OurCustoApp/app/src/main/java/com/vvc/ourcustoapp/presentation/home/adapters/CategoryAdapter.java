package com.vvc.ourcustoapp.presentation.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.custom.expandableRecycler.ExpandableRecyclerAdapter;
import com.vvc.ourcustoapp.custom.expandableRecycler.ParentListItem;
import com.vvc.ourcustoapp.presentation.home.models.Category;
import com.vvc.ourcustoapp.presentation.home.models.SubCategory;
import com.vvc.ourcustoapp.presentation.home.viewholders.CategoryViewHolder;
import com.vvc.ourcustoapp.presentation.home.viewholders.SubCategoryViewHolder;

import java.util.List;

public class CategoryAdapter extends ExpandableRecyclerAdapter<CategoryViewHolder, SubCategoryViewHolder> {

    private LayoutInflater inflater;

    private SubCategoryViewHolder.OnItemClickListener onItemClickListener;

    public CategoryAdapter(Context context, List<? extends ParentListItem> parentItemList, SubCategoryViewHolder.OnItemClickListener onItemClickListener) {
        super(parentItemList);
        this.onItemClickListener=onItemClickListener;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public CategoryViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View mainCategoryView = inflater.inflate(R.layout.layout_my_profile_main, parentViewGroup, false);
        return new CategoryViewHolder(mainCategoryView);
    }

    @Override
    public SubCategoryViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View subView = inflater.inflate(R.layout.layout_my_profile_sub, childViewGroup, false);
        return new SubCategoryViewHolder(subView,onItemClickListener);
    }

    @Override
    public void onBindParentViewHolder(CategoryViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Category category = (Category) parentListItem;
        parentViewHolder.bind(category);
    }

    @Override
    public void onBindChildViewHolder(SubCategoryViewHolder childViewHolder, int position, Object childListItem) {
        SubCategory subCategory = (SubCategory) childListItem;
        childViewHolder.bind(subCategory);
    }
}
