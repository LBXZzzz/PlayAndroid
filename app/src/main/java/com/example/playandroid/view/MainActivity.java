package com.example.playandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.playandroid.R;
import com.example.playandroid.model.HttpUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager2 viewPager;
    private LinearLayout llHome,llKnowledgeHierarchy,llUser,llProject;
    private ImageView ivHome,ivKnowledgeHierarchy,ivUser,ivProject,ivCurrent;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPaper();
        initTabView();
    }

    private void initTabView() {
        llHome=findViewById(R.id.home);
        llHome.setOnClickListener(this);
        llKnowledgeHierarchy=findViewById(R.id.knowledge_hierarchy);
        llKnowledgeHierarchy.setOnClickListener(this);
        llUser=findViewById(R.id.user);
        llUser.setOnClickListener(this);
        llProject=findViewById(R.id.project);
        llProject.setOnClickListener(this);
        ivHome=findViewById(R.id.home_photo);
        ivKnowledgeHierarchy=findViewById(R.id.knowledge_hierarchy_photo);
        ivUser=findViewById(R.id.user_photo);
        ivProject=findViewById(R.id.project_photo);
        ivHome.setSelected(true);
        ivCurrent=ivHome;
    }

    private void initPaper() {
        viewPager=findViewById(R.id.view_paper_main);
        ArrayList<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(UserFragment.newInstance("知识"));
        fragmentList.add(UserFragment.newInstance("项目"));
        fragmentList.add(UserFragment.newInstance("用户"));
        MainFragmentAdapter mainFragmentAdapter=new MainFragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragmentList);
        viewPager.setAdapter(mainFragmentAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {//滚动的动画
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {//页面选择了之后，实现响应事件
                super.onPageSelected(position);
                changeTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void changeTab(int position) {
        ivCurrent.setSelected(false);
        switch (position){
            case R.id.home:
                viewPager.setCurrentItem(0);
            case 0:
                ivHome.setSelected(true);
                ivCurrent=ivHome;
                break;
            case R.id.knowledge_hierarchy:
                viewPager.setCurrentItem(1);
            case 1:
                ivCurrent=ivKnowledgeHierarchy;
                ivKnowledgeHierarchy.setSelected(true);
                break;
            case R.id.project:
                viewPager.setCurrentItem(2);
            case 2:
                ivCurrent=ivProject;
                ivProject.setSelected(true);
                break;
            case R.id.user:
                viewPager.setCurrentItem(3);
            case 3:
                ivCurrent=ivUser;
                ivUser.setSelected(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        changeTab(view.getId());
    }
}