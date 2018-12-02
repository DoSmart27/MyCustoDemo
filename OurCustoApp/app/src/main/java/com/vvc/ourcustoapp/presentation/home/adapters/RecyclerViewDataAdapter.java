package com.vvc.ourcustoapp.presentation.home.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.presentation.home.models.SectionDataModel;

import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ItemRowHolder> {

    private ArrayList<SectionDataModel> dataList;
    private Context mContext;

    public RecyclerViewDataAdapter(Context context, ArrayList<SectionDataModel> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, null);
        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRowHolder itemRowHolder, int position) {
        itemRowHolder.itemTitle.setText(dataList.get(position).getHeaderTitle());

        SectionListDataAdapter itemListDataAdapter = new SectionListDataAdapter(mContext, dataList.get(position).getAllItemsInSection());

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.recycler_view_list.setAdapter(itemListDataAdapter);

        itemRowHolder.text_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(), "click event on more, " , Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView itemTitle,text_view_all;

        private RecyclerView recycler_view_list;

        private AppCompatButton btnMore;

        private ItemRowHolder(View view) {
            super(view);
            this.itemTitle = (AppCompatTextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
            this.text_view_all= (AppCompatTextView) view.findViewById(R.id.text_view_all);
        }

    }

}