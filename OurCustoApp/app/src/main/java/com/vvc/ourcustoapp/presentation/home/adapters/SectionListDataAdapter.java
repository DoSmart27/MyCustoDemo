package com.vvc.ourcustoapp.presentation.home.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.models.SingleItemModel;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private ArrayList<SingleItemModel> itemsList;
    private Context mContext;

    public SectionListDataAdapter(Context context, ArrayList<SingleItemModel> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public SingleItemRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, null);
        return new SingleItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleItemRowHolder holder, int position) {
        SingleItemModel singleItem = itemsList.get(position);
        holder.tvTitle.setText(singleItem.getName());

       /* Glide.with(mContext)
                .load(feedItem.getImageURL())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.bg)
                .into(feedListRowHolder.thumbView);*/
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView tvTitle;

        private AppCompatImageView itemImage;


        public SingleItemRowHolder(View view) {
            super(view);

            this.tvTitle = (AppCompatTextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (AppCompatImageView) view.findViewById(R.id.itemImage);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Toast.makeText(v.getContext(), tvTitle.getText(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

}