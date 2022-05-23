package com.example.playandroid.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProjectAdapter extends FragmentPagerAdapter  {
    List<Fragment> fragmentList=new ArrayList<>();
    List<String> tabs=new ArrayList<>();
    public ProjectAdapter(@NonNull FragmentManager fragmentManager, List<Fragment> fragments, List<String> tabs){
        super(fragmentManager);
            fragmentList=fragments;
            this.tabs=tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position);
    }

}
