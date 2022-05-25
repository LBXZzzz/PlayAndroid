package com.example.playandroid.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.playandroid.R;
import com.example.playandroid.entities.SearchHotWord;
import com.example.playandroid.presenter.Presenter;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements IView,IView2{
    private Toolbar mToolbar;
    private ArrayList<SearchHotWord> mList=new ArrayList<>();
    Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        presenter=new Presenter(this,this);
        presenter.fetchGetSearchHotWod();
    }

    private void initView() {
        mToolbar=findViewById(R.id.search_activity_too_bar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void showData(ArrayList<?> list) {
        mList=(ArrayList<SearchHotWord>) list;
        final LinearLayout layout2 = findViewById(R.id.search_linearlayout);
        layout2.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < list.size(); i++) {
            Button bt = new Button(this);
            bt.setText(mList.get(i).getName());
            
            layout2.addView(bt);
        }
    }

    @Override
    public void showData2(ArrayList<?> list) {

    }
}