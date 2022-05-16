package com.example.playandroid.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Json {
   /* public ArrayList<Bitmap> sendRequestWithHttpURLConnection(URL url){
        ArrayList<Bitmap> bitmaps=new ArrayList<>();
        new Thread(new Runnable() {//开启子线程
            @Override
            public void run() {
                HttpsURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    connection=(HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//GET表示希望从服务器获取数据，POST则是希望提交数据给服务器
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();//获取服务器返回的输入流
                    reader=new BufferedReader(new InputStreamReader(in));//读取输入流
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    parseJSONWithJSONObject(response.toString(),bitmaps);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                } finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();//把HTTP连接关掉
                    }
                }
            }
        }).start();
        return bitmaps;
    }

    public void parseJSONWithJSONObject(String jsonData,ArrayList<Bitmap> bitmaps) throws JSONException {
        JSONObject jsonObject1 = new JSONObject(jsonData);
        JSONArray jsonArray=jsonObject1.getJSONArray("data");
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);//一个大括号item
            String id=jsonObject.getString("id");
            String imagePath=jsonObject.getString("imagePath");
            getUrlPhoto(imagePath,bitmaps);
        }
    }


    public void getUrlPhoto(String path,ArrayList<Bitmap> bitmaps){
        new Thread() {
            private HttpURLConnection conn;
            private Bitmap bitmap;
            public void run() {
                // 连接服务器 get 请求 获取图片
                try {
                    //创建URL对象
                    URL url = new URL(path);
                    // 根据url 发送 http的请求
                    conn = (HttpURLConnection) url.openConnection();
                    // 设置请求的方式
                    conn.setRequestMethod("GET");
                    //设置超时时间
                    conn.setConnectTimeout(5000);
                    // 得到服务器返回的响应码
                    int code = conn.getResponseCode();
                    //请求网络成功后返回码是200
                    if (code == 200) {
                        //获取输入流
                        InputStream is = conn.getInputStream();
                        //将流转换成Bitmap对象
                        bitmap = BitmapFactory.decodeStream(is);
                        bitmaps.add(bitmap);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //关闭连接
                conn.disconnect();
            }
        }.start();
    }


    /////////
    public ArrayList<String> sendRequestWithHttpURLConnection(URL url){
        ArrayList<Bitmap> bitmaps=new ArrayList<>();
        new Thread(new Runnable() {//开启子线程
            @Override
            public void run() {
                HttpsURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    connection=(HttpsURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");//GET表示希望从服务器获取数据，POST则是希望提交数据给服务器
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in=connection.getInputStream();//获取服务器返回的输入流
                    reader=new BufferedReader(new InputStreamReader(in));//读取输入流
                    StringBuilder response=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    parseJSONWithJSONObject(response.toString(),bitmaps);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                } finally {
                    if(reader!=null){
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null){
                        connection.disconnect();//把HTTP连接关掉
                    }
                }
            }
        }).start();
        return bitmaps;
    }*/
}
