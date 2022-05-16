package com.example.playandroid.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.playandroid.R;
import com.example.playandroid.entities.HomeTextItem;
import com.example.playandroid.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements IView{

    private Context ctx;
    //初始化ViewPager
    private View rootView;
    private ViewPager viewPager;
    private HomeFragmentAdapter homeFragmentAdapter;
    //轮播的内容
    private ArrayList<ImageView> imageViews;
    private LinearLayout linearLayout;
    //轮播控制
    private Handler handler;
    private Timer timer;
    private TimerTask task;
    private int mPosition;


    private RecyclerView recyclerView;
    private List<HomeTextItem> mList=new ArrayList<>();
    Presenter presenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null){
            rootView =inflater.inflate(R.layout.fragment_home, container, false);
        }
        recyclerView=(RecyclerView) rootView.findViewById(R.id.home_recycler_view);
        presenter=new Presenter(this);
        presenter.fetch();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return rootView;
    }


    @Override
    public void showData(ArrayList<?> list) {
        mList=(ArrayList<HomeTextItem>)list;
        HomeRecyclerViewAdapter homeRecyclerViewAdapter=new HomeRecyclerViewAdapter(mList);
        Log.d("tag",mList.get(0).getChapterName());
        //Log.d("tag111",list.get(0).getNameFirst());
        recyclerView.setAdapter(homeRecyclerViewAdapter);
        homeRecyclerViewAdapter.setOnItemClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String data=mList.get(position).getLink();
                Intent intent=new Intent(getActivity(),HomeFragmentClick.class);//给后面开启的活动传值
                intent.putExtra("link",data);
                startActivity(intent);
            }
        });
    }
}