package com.example.playandroid.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtil {
    public String httpUtil(String Url){
        String jsonString="";
        HttpsURLConnection connection=null;
        BufferedReader reader=null;
        try {
            URL url=new URL(Url);
            connection=(HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            InputStream in=connection.getInputStream();//获取服务器返回的输入流
            reader=new BufferedReader(new InputStreamReader(in));//读取输入流
            StringBuilder response=new StringBuilder();
            String line;
            while ((line=reader.readLine())!=null){
                response.append(line);
            }
            jsonString=response.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        Log.d("atg",jsonString);
        return jsonString;
    }

}
