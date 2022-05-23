package com.example.playandroid.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.playandroid.R;
import com.example.playandroid.entities.BannerItem;
import com.example.playandroid.entities.HomeTextItem;
import com.example.playandroid.presenter.Presenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements IView,IView2{

    private Context ctx;
    private boolean slidingUpward = false;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    //初始化ViewPager
    private View rootView;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean loading = false;
    private HomeFragmentAdapter homeFragmentAdapter;
    //轮播的内容
    private ArrayList<ImageView> imageViews;
    private LinearLayout linearLayout;
    //轮播控制
    private Handler handler;
    private Timer timer;
    private TimerTask task;
    private int mPosition;

    private int viewPaperClick;
    private ViewPager mViewPager;
    private TextView mTextView;
    private RecyclerView recyclerView;
    private ImageView mImageView;
    private ArrayList<ImageView> mImageViewList=new ArrayList<>();
    private int currentPosition;
    private List<String> mBannerTitle=new ArrayList<>();
    private List<HomeTextItem> mHomeTextItemList =new ArrayList<>();
    private List<BannerItem> mBannerItemList=new ArrayList<>();
    Presenter presenter;

    public HomeFragment() {
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
        mProgressBar=(ProgressBar)rootView.findViewById(R.id.home_pb);
        mProgressBar.setVisibility(View.VISIBLE);
        recyclerView=(RecyclerView) rootView.findViewById(R.id.home_recycler_view);
        //mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE);
        presenter=new Presenter(this,this);
        presenter.fetchGetHomeData();
        presenter.fetchGetBannerData();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        return rootView;
    }


    @Override
    public void showData(ArrayList<?> list) {
        mHomeTextItemList =(ArrayList<HomeTextItem>)list;
        mProgressBar=(ProgressBar)rootView.findViewById(R.id.home_pb);
        homeRecyclerViewAdapter=new HomeRecyclerViewAdapter(mHomeTextItemList);
        recyclerView.setAdapter(homeRecyclerViewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                /*LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();
                    System.out.println(itemCount);
                    System.out.println(lastItemPosition);
                    // 判断是否滑动到了最后一个item，并且是向上滑动
                    if (lastItemPosition == (itemCount - 1) ) {
                        //加载更多
                        //homeRecyclerViewAdapter.updateData();
                    }
                }*/
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        homeRecyclerViewAdapter.setOnItemClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String data= mHomeTextItemList.get(position).getLink();
                Intent intent=new Intent(getActivity(), WebViewClick.class);//给后面开启的活动传值
                intent.putExtra("link",data);
                startActivity(intent);
            }
        });
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showData2(ArrayList<?> list) {
        mBannerItemList=(List<BannerItem>)list;
        mViewPager=(ViewPager) rootView.findViewById(R.id.home_view_paper);
        mTextView=(TextView)rootView.findViewById(R.id.home_banner_text);
        mImageView=new ImageView(getActivity());
        mImageView.setImageBitmap(mBannerItemList.get(2).getBitmap());
        mImageViewList.add(mImageView);
        mBannerTitle.add(mBannerItemList.get(2).getTitle());
        for (int i = 0; i < mBannerItemList.size(); i++) {
            mImageView=new ImageView(getActivity());
            mImageView.setImageBitmap(mBannerItemList.get(i).getBitmap());
            mImageViewList.add(mImageView);
            mBannerTitle.add(mBannerItemList.get(i).getTitle());
        }
        mImageView=new ImageView(getActivity());
        mImageView.setImageBitmap(mBannerItemList.get(0).getBitmap());
        mImageViewList.add(mImageView);
        mBannerTitle.add(mBannerItemList.get(0).getTitle());
        mTextView.setText(mBannerItemList.get(2).getTitle());
        BannerAdapter bannerAdapter=new BannerAdapter(mImageViewList);
        mViewPager.setAdapter(bannerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                //  * 真实数据：      0       1       2
                //  * 实际数据：2     0       1       2       0
                //  最后一个0切换到前面的0页面位置
                if (position == mImageViewList.size() - 1) {
                    currentPosition =1;

                } else if (position == 0) {
                    currentPosition = mImageViewList.size() - 2;
                } else {
                    currentPosition = position;
                }
                System.out.println(position);
                mTextView.setText(mBannerTitle.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 页面滑动静止状态，偷天换日，换位置
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    // smoothScroll: 设置平稳滑动
                    mViewPager.setCurrentItem(currentPosition, false);
                }
            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        viewPaperClick=0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        viewPaperClick=1;
                        break;
                    case MotionEvent.ACTION_UP:
                        if(viewPaperClick==0){
                            int item= mViewPager.getCurrentItem();
                            String data=mBannerItemList.get(item).getUrl();
                            Intent intent=new Intent(getActivity(), WebViewClick.class);//给后面开启的活动传值
                            intent.putExtra("link",data);
                            startActivity(intent);
                        }
                }
                return false;
            }
        });
    }



}