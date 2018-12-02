package com.vvc.ourcustoapp.presentation.location.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vvc.ourcustoapp.R;
import com.vvc.ourcustoapp.database.tables.MyAddress;
import com.vvc.ourcustoapp.utils.AndroidUtils;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.AddressViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(MyAddress item);
    }

    private LayoutInflater mInflater;
    private List<MyAddress> mAddresses; // Cached copy of address

    private OnItemClickListener listener;
    private Context context;
    public AddressListAdapter(Context context,OnItemClickListener listener) {
        this.context=context;
        mInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.layout_save_addresses, parent, false);
        return new AddressListAdapter.AddressViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        holder.bind(mAddresses.get(position),listener);
    }


    @Override
    public int getItemCount() {
        if (mAddresses != null)
            return mAddresses.size();
        else
            return 0;
    }

    class AddressViewHolder extends RecyclerView.ViewHolder {
        private TextView tag_name, sub_tag_name;
        private AppCompatImageView image_tag;

        private AddressViewHolder(View itemView) {
            super(itemView);
            image_tag = itemView.findViewById(R.id.image_tag);
            tag_name = itemView.findViewById(R.id.address_tag_name);
            sub_tag_name = itemView.findViewById(R.id.sub_tag_name);
        }

        void bind(MyAddress myAddress, OnItemClickListener listener) {
            Bitmap bitmap = AndroidUtils.convertByteToBitmap(myAddress.getImage());
            image_tag.setImageBitmap(bitmap!=null?bitmap:BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_baseline_pin_drop_24px));

            tag_name.setText(myAddress.getTag());
            sub_tag_name.setText(myAddress.getAddress());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(myAddress);
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


   /* public void setOnItemClickListener(ClickListener clickListener) {
        AddressListAdapter.clickListener = clickListener;
    }*/


}