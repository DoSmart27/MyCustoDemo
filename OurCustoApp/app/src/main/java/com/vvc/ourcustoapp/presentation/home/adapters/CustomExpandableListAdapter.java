package com.vvc.ourcustoapp.presentation.home.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.presentation.home.models.SubCategory;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listTitle;
    private HashMap<String, List<SubCategory>> expandableListDetail;

    public CustomExpandableListAdapter(Context context, List<String> listTitle, HashMap<String, List<SubCategory>> expandableListDetail) {
        this.context = context;
        this.listTitle = listTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public int getGroupCount() {
        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableListDetail.get(this.listTitle.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return expandableListDetail.get(listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_my_profile_main, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.tv_category);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubCategory category = (SubCategory) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_my_profile_sub, null);
        }
       // TextView expandedListTextView = (TextView) convertView.findViewById(R.id.sub_title_item);
       // expandedListTextView.setText(expandedListText);

        AppCompatTextView subTitle = (AppCompatTextView) convertView.findViewById(R.id.sub_title_item);
        AppCompatImageView imageView = (AppCompatImageView) convertView.findViewById(R.id.sub_image_drawable);
        LinearLayout layout_child_item =(LinearLayout)convertView.findViewById(R.id.layout_child_item);

        imageView.setImageDrawable(category.getDrawable());
        subTitle.setText(category.getSub_title());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
