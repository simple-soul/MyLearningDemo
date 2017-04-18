package com.example.simple_soul.mylearningdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.simple_soul.mylearningdemo.utils.HttpUtils;

public class RxJavaActivity extends AppCompatActivity
{
    private static String urlStr = "http://172.22.21.230:8080/transportservice/type/jason/action/GetCarSpeed.do";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);


    }

    //网络请求操作
    private void Http()
    {
        String json = "{'CarId':1}";
        new HttpUtils()
        {
            @Override
            public void getResult(String s)
            {
                Log.i("main", s);
            }
        }.testHttpConnection(urlStr, json);
    }

}
