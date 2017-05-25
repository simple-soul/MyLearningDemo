package com.example.simple_soul.mylearningdemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simple_soul.mylearningdemo.R;

public class AChartEngineActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achart_engine);

        initView();
        initData();
    }

    private void initView()
    {
        btn1 = (Button) findViewById(R.id.achart_btn_1);
        btn2 = (Button) findViewById(R.id.achart_btn_2);
        btn3 = (Button) findViewById(R.id.achart_btn_3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
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
            case R.id.achart_btn_1:
                intent.setClass(this, TestActivity.class);
                break;

            case R.id.achart_btn_2:
                intent.setClass(this, AChartIntentActivity.class);
                break;

            case R.id.achart_btn_3:
                intent.setClass(this, SQLiteActivity.class);
                break;
        }
        startActivity(intent);
    }
}
