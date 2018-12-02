package com.vvc.ourcustoapp.presentation.home.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.database.tables.MyAddress;
import com.vvc.ourcustoapp.utils.AndroidUtils;

import java.util.List;

public class ManageAddressAdapter extends RecyclerView.Adapter<ManageAddressAdapter.ViewHolder>{


    public interface OnManageItemClickListener {
        void onUpdateClick(MyAddress item,int position);
        void onDeleteClick(MyAddress item,int position);
    }

    private LayoutInflater mInflater;
    private List<MyAddress> mAddresses;
    private OnManageItemClickListener listener;
    private Context context;

    public ManageAddressAdapter(Context context, OnManageItemClickListener listener) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ManageAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_address_manage, parent, false);
        return new ManageAddressAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageAddressAdapter.ViewHolder holder, int position) {
        holder.bind(mAddresses.get(position),listener);
    }

    @Override
    public int getItemCount() {
        if (mAddresses != null)
            return mAddresses.size();
        else
            return 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView tag_image;
        private AppCompatTextView title, sub_title,address_update,address_delete;

        private ViewHolder(View itemView) {
            super(itemView);
            tag_image = itemView.findViewById(R.id.address_image_tag);
            title = itemView.findViewById(R.id.address_tag_title);
            sub_title = itemView.findViewById(R.id.address_tag_subtitle);
            address_update = itemView.findViewById(R.id.address_update);
            address_delete = itemView.findViewById(R.id.address_delete);
        }

        void bind(MyAddress myAddress, OnManageItemClickListener listener) {
            Bitmap bitmap = AndroidUtils.convertByteToBitmap(myAddress.getImage());
            tag_image.setImageBitmap(bitmap!=null?bitmap:BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_baseline_my_location_24px));
            title.setText(myAddress.getTag());
            sub_title.setText(myAddress.getAddress());

            address_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onUpdateClick(myAddress,getAdapterPosition());
                }
            });
            address_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDeleteClick(myAddress,getAdapterPosition());
                }
            });
        }
    }


    public void setAddresses(List<MyAddress> addresses) {
        mAddresses = addresses;
        notifyDataSetChanged();
    }

    public MyAddress getAddressAtPosition(int position) {
        return mAddresses.get(position);
    }
}
