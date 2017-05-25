package com.example.simple_soul.mylearningdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.utils.HttpUtils;

public class RxJavaActivity extends AppCompatActivity
{
    private static String urlStr = "http://172.22.21.230:8080/transportservice/type/jason/action/SetCarMove.do";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        HttpFromMyself();
    }

    //自制网络请求操作
    private void HttpFromMyself()
    {
        String json = "{\"CarId\":2, \"CarAction\":\"Stop\"}";
        new HttpUtils(urlStr, json)
        {
            @Override
            public void getResult(String s)
            {
                Log.i("main", "回传的数据："+s);
            }
        };
    }

    private void HttpFromRetrofit()
    {

    }

}
