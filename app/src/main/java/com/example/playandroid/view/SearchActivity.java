package com.example.playandroid.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.playandroid.R;
import com.example.playandroid.entities.SearchHotWord;
import com.example.playandroid.entities.SearchResult;
import com.example.playandroid.presenter.Presenter;
import com.example.playandroid.presenter.Presenter1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements IView,IView2,IView3{
    private Toolbar mToolbar;
    private EditText mEditText;
    private ArrayList<SearchHotWord> mList=new ArrayList<>();
    private ArrayList<SearchResult> mSearchResultList=new ArrayList<>();
    private ArrayList<SearchResult> mTotalSearchResultList=new ArrayList<>();
    private LinearLayout mLinearLayout;
    private LinearLayout mLinearLayout2;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private SearchRecyclerViewAdapter searchRecyclerViewAdapter;
    private String param;
    Presenter presenter;
    Presenter1 presenter1;
    int page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        presenter=new Presenter(this,this);
        presenter1=new Presenter1(this);
        presenter.fetchGetSearchHotWod();
        mToolbar=findViewById(R.id.search_activity_too_bar);
        mEditText=findViewById(R.id.search_activity_edit_view);
        mLinearLayout = findViewById(R.id.search_linearlayout);
        mLinearLayout2=findViewById(R.id.search_linearlayout2);
        mRecyclerView=findViewById(R.id.search_activity_recyclerview);
        mProgressBar=findViewById(R.id.search_progressbar);
        mProgressBar.setVisibility(View.GONE);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                mProgressBar.setVisibility(View.VISIBLE);
                //这里写事件，返回为true，即为搜索键的事件
                Log.d("search",mEditText.getText().toString());
                param=mEditText.getText().toString();
                presenter1.fetchGetSearchReturnResult(param,page);
                //点击回车后自动收起键盘
                InputMethodManager manager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (manager != null)
                    manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return true;
            }
        });
        initView();
    }

    private void initView() {

    }


    @Override
    public void showData(ArrayList<?> list) {
        mList=(ArrayList<SearchHotWord>) list;
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < list.size(); i++) {
            Button bt = new Button(this);
            bt.setText(mList.get(i).getName());
            mLinearLayout.addView(bt);
        }
    }

    @Override
    public void showData2(ArrayList<?> list) {

    }

    @Override
    public void showData3(ArrayList<?> list) {
        mProgressBar.setVisibility(View.GONE);
        mSearchResultList=(ArrayList<SearchResult>) list;
        mTotalSearchResultList.addAll(mSearchResultList);
        Log.d("search",mSearchResultList.get(0).getTitle());
        if(page==0){
            mLinearLayout.setVisibility(View.GONE);
            searchRecyclerViewAdapter=new SearchRecyclerViewAdapter(mSearchResultList);
            mRecyclerView.setAdapter(searchRecyclerViewAdapter);
        }else {
            searchRecyclerViewAdapter.updateData(mSearchResultList);
        }
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();
                    // 判断是否滑动到了最后一个item，并且是向上滑动
                    if (lastItemPosition == (itemCount - 1) ) {
                        //加载更多
                        page+=1;
                        presenter1.fetchGetSearchReturnResult(param,page);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
       searchRecyclerViewAdapter.setOnItemClickListener(new HomeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String data= mTotalSearchResultList.get(position).getLink();
                Intent intent=new Intent(SearchActivity.this, WebViewClick.class);//给后面开启的活动传值
                intent.putExtra("link",data);
                startActivity(intent);
            }
        });
        mLinearLayout2.setVisibility(View.VISIBLE);
    }

}