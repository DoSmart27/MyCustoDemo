package com.vvc.ourcustoapp.presentation.home.viewholders;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.custom.expandableRecycler.ChildViewHolder;
import com.vvc.ourcustoapp.presentation.home.models.SubCategory;

public class SubCategoryViewHolder extends ChildViewHolder {
    private AppCompatTextView subTitle;
    private AppCompatImageView imageView;
    private LinearLayout layout_child_item;

    OnItemClickListener listener;

    public SubCategoryViewHolder(View itemView,  OnItemClickListener listener) {
        super(itemView);
        this.listener=listener;
        subTitle = (AppCompatTextView) itemView.findViewById(R.id.sub_title_item);
        imageView = (AppCompatImageView) itemView.findViewById(R.id.sub_image_drawable);
        layout_child_item =(LinearLayout)itemView.findViewById(R.id.layout_child_item);
    }

    public void bind(SubCategory category) {
        imageView.setImageDrawable(category.getDrawable());
        subTitle.setText(category.getSub_title());
        layout_child_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(category.getSub_title());
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(String subTitle);
    }
}
