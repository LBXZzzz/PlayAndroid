package com.example.playandroid.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentAdapter extends FragmentStateAdapter {
    List<Fragment> fragmentList=new ArrayList<>();
    /*public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    private HomeRecyclerViewAdapter.OnItemClickListener mOnItemClickListener;
    private HomeRecyclerViewAdapter.OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemClickListener(HomeRecyclerViewAdapter.OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(HomeRecyclerViewAdapter.OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }*/
    public MainFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        fragmentList=fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}