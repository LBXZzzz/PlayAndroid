package com.example.playandroid.presenter;

import android.util.Log;

import com.example.playandroid.model.GetBannerImpl;
import com.example.playandroid.model.GetHomeDataImpl;
import com.example.playandroid.model.GetKnowledgeHierarchyImpl;
import com.example.playandroid.model.GetProjectDataImpl;
import com.example.playandroid.model.IGetData;
import com.example.playandroid.view.IView;
import com.example.playandroid.view.IView2;

import java.util.ArrayList;

public class Presenter {
    GetHomeDataImpl getHomeData;
    GetKnowledgeHierarchyImpl getKnowledgeHierarchy;
    GetProjectDataImpl getProjectData;
    GetBannerImpl getBanner;
    IView mView;
    IView2 mView2;
    public Presenter(IView iView,IView2 iView2){
        this.mView=iView;
        this.mView2=iView2;
        getHomeData=new GetHomeDataImpl();
        getKnowledgeHierarchy=new GetKnowledgeHierarchyImpl();
        getProjectData=new GetProjectDataImpl();
        getBanner=new GetBannerImpl();
    }
    public void fetchGetHomeData(){
        this.getHomeData.getData(new GetHomeDataImpl.SuccessReturnData(){

            @Override
            public void Complete(ArrayList<?> dataList) {

                mView.showData(dataList);

            }
        });
    }
    public void fetchGetKnowledgeHierarchyData(){
        this.getKnowledgeHierarchy.getData(new GetHomeDataImpl.SuccessReturnData(){

            @Override
            public void Complete(ArrayList<?> dataList) {

                mView.showData(dataList);

            }
        });
    }

    public void fetchGetProjectData(){
        this.getProjectData.getData(new IGetData.SuccessReturnData(){

            @Override
            public void Complete(ArrayList<?>dataList) {
                mView.showData(dataList);
            }
        });
    }

    public void fetchGetBannerData(){

        this.getBanner.getData(new IGetData.SuccessReturnData(){
            @Override
            public void Complete(ArrayList<?> dataList) {
                mView2.showData2(dataList);
            }
        });
    }
}
