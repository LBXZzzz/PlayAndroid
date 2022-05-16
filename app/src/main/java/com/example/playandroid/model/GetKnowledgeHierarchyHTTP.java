package com.example.playandroid.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.playandroid.entities.KnowledgeHierarchy;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetKnowledgeHierarchyHTTP implements IGetData{
    private ArrayList<KnowledgeHierarchy> list=new ArrayList<>();
    private Handler handler;
    private void getKnowledgeHierarchyHTTPFirst(){
        new Thread(new Runnable() {//开启子线程
            @Override
            public void run() {
                HttpUtil httpUtil=new HttpUtil();
                try {
                    getKnowledgeHierarchyFirstString(httpUtil.httpUtil("https://www.wanandroid.com/tree/json"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getKnowledgeHierarchyFirstString(String jsonData) throws JSONException {
        String nameFirst;
        String nameSecond;
        String nameSecondSum ="";
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (int firstNumber = 0; firstNumber < jsonArray.length(); firstNumber++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(firstNumber);
            nameFirst = jsonObject1.getString("name");
            JSONArray jsonArray2 = jsonObject1.getJSONArray("children");
            for (int secondNumber = 0; secondNumber < jsonArray2.length(); secondNumber++) {
                JSONObject jsonObject2 = jsonArray2.getJSONObject(secondNumber);
                nameSecond = jsonObject2.getString("name");
                nameSecondSum = nameSecondSum+nameSecond + " ";
            }
            KnowledgeHierarchy knowledgeHierarchy=new KnowledgeHierarchy(nameFirst,nameSecondSum);
            list.add(knowledgeHierarchy);
            nameSecondSum ="";
        }
        Log.d("tag",list.get(1).getNameFirst());
        Message message=new Message();
        message.obj=list;
        handler.sendMessage(message);
    }


    @Override
    public void getData(IGetData.SuccessReturnData successReturnData) {
        getKnowledgeHierarchyHTTPFirst();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                list=(ArrayList<KnowledgeHierarchy>)msg.obj;
                Log.d("sb",list.get(1).getNameFirst());
                successReturnData.Complete(list);
                super.handleMessage(msg);
            }
        };
    }
}
