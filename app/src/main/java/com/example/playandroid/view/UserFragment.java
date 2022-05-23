package com.example.playandroid.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.playandroid.R;
import com.example.playandroid.entities.HomeTextItem;
import com.example.playandroid.entities.ProjectList;
import com.example.playandroid.presenter.Presenter;

import java.util.ArrayList;

public class UserFragment extends Fragment implements IView{

    private static final String ARG_TEXT = "param1";
    View rootView;
    private String mText;


    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(String param1) {  //这个函数专门用于专门构造一个Fragment
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootView==null){
            rootView =inflater.inflate(R.layout.fragment_user, container, false);
        }
        initView();

        return rootView;
    }

    private void initView() {

    }


    @Override
    public void showData(ArrayList<?> list) {

    }
}