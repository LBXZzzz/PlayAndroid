package com.example.playandroid.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.playandroid.R;

import java.util.ArrayList;



public class HomeFragmentAdapter extends PagerAdapter {
    private ArrayList<ImageView> imageViews;
    private Context ctx;
    private PagerImgClickListener mListener;

    public HomeFragmentAdapter(Context ctx,ArrayList<ImageView> imageViews, PagerImgClickListener listener) {
        this.ctx = ctx;
        this.imageViews = imageViews;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(imageViews.get(position));
        imageViews.get(position).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.ImgClick(position);
                }
            }
        });
        return imageViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageViews.get(position));
    }
}

