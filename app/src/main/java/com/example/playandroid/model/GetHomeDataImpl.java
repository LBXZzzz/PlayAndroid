package com.example.playandroid.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.playandroid.entities.HomeTextItem;
import com.example.playandroid.view.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class GetHomeDataImpl implements IGetData{
    private final static String Url="https://www.wanandroid.com/article/list/0/json";
    private ArrayList<HomeTextItem> list=new ArrayList<>();
    private Handler handler;

    public void getHomeDataFirst(){
        new Thread(new Runnable() {//开启子线程
            @Override
            public void run() {
                HttpUtil httpUtil=new HttpUtil();
                try {
                    getKnowledgeHierarchyFirstString(httpUtil.httpUtil(Url));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public void getKnowledgeHierarchyFirstString(String jsonData) throws JSONException {
        String superChapterName;
        String chapterName;
        String niceDate;
        String title;
        String link;
        String shareUser;
        JSONObject jsonObject = new JSONObject(jsonData);
        JSONObject jsonObject1=jsonObject.getJSONObject("data");
        JSONArray jsonArray = jsonObject1.getJSONArray("datas");
        for (int firstNumber = 0; firstNumber < jsonArray.length(); firstNumber++) {
            JSONObject jsonObject2 = jsonArray.getJSONObject(firstNumber);
            superChapterName=jsonObject2.getString("superChapterName");
            chapterName=jsonObject2.getString("chapterName");
            niceDate=jsonObject2.getString("niceDate");
            title=jsonObject2.getString("title");
            link=jsonObject2.getString("link");
            shareUser=jsonObject2.getString("shareUser");
            HomeTextItem homeTextItem=new HomeTextItem(superChapterName,chapterName,niceDate,title,link,shareUser);
            list.add(homeTextItem);
        }
        Message message=new Message();
        message.obj=list;
        handler.sendMessage(message);
    }
    @Override
    public void getData(SuccessReturnData successReturnData) {
        getHomeDataFirst();
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                list=(ArrayList<HomeTextItem>)msg.obj;
                successReturnData.Complete(list);
                super.handleMessage(msg);
            }
        };

    }

}