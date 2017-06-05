package com.example.simple_soul.mylearningdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simple_soul.mylearningdemo.R;

public class MPAndroidChartActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn1, btn2, btn3, btn4, btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);

        initView();
        initData();
    }

    private void initView()
    {
        btn1 = (Button) findViewById(R.id.mpchart_btn_1);
        btn2 = (Button) findViewById(R.id.mpchart_btn_2);
        btn3 = (Button) findViewById(R.id.mpchart_btn_3);
        btn4 = (Button) findViewById(R.id.mpchart_btn_4);
        btn5 = (Button) findViewById(R.id.mpchart_btn_5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    private void initData()
    {

    }

    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent();
        switch (v.getId())
        {
            case R.id.mpchart_btn_1:
                intent.setClass(MPAndroidChartActivity.this, MPPieChartActivity.class);
                break;

            case R.id.mpchart_btn_2:
                intent.setClass(MPAndroidChartActivity.this, MPBarChartActivity.class);
                break;

            case R.id.mpchart_btn_3:
                intent.setClass(MPAndroidChartActivity.this, MPLineChartActivity.class);
                break;

            case R.id.mpchart_btn_4:
                intent.setClass(MPAndroidChartActivity.this, MPDynamicActivity.class);
                break;
            case R.id.mpchart_btn_5:
                intent.setClass(MPAndroidChartActivity.this, MPDynamicDataActivity.class);
                break;
        }
        startActivity(intent);
    }
}
