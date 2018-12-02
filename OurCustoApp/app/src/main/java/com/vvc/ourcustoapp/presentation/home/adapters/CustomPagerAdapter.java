package com.vvc.ourcustoapp.presentation.home.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vvc.ourcustoapp.R;

public class CustomPagerAdapter extends PagerAdapter {


    private int[] mResources = {
            R.drawable.ic_default_icon,
            R.drawable.ic_default_icon,
            R.drawable.ic_default_icon
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        AppCompatImageView imageView = (AppCompatImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

   /* public Object instantiateItem(View collection, int position) {
        LinearLayour images = new LinearLayout(collection.getContext());
        for (int i = 0; i < 3; i++) {
            ImageView image = new ImageView(collection.getContext());
            image.setPadding(20, 0, 20, 0);
            images.addView(image);
            int res = someResource depending on i and position.
                    image.setImageResource(res);
        }

        ((ViewPager) collection).addView(images, 0);
        return images;
    }*/

}
