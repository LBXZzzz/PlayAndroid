package com.example.playandroid.presenter;

import android.util.Log;

import com.example.playandroid.model.GetHomeDataImpl;
import com.example.playandroid.view.IView;

import java.util.ArrayList;

public class Presenter {
    GetHomeDataImpl getHomeData;
    IView mView;
    public Presenter(IView iView){
        this.mView=iView;
        getHomeData=new GetHomeDataImpl();
    }
    public void fetch(){
        this.getHomeData.getData(new GetHomeDataImpl.SuccessReturnData(){

            @Override
            public void Complete(ArrayList<?> dataList) {

                mView.showData(dataList);

            }
        });
    }
}
