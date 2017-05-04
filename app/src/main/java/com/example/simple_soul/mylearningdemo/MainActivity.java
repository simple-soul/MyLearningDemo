package com.example.simple_soul.mylearningdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.simple_soul.mylearningdemo.activity.AChartIntentActivity;
import com.example.simple_soul.mylearningdemo.activity.AnimActivity;
import com.example.simple_soul.mylearningdemo.activity.ControlActivity;
import com.example.simple_soul.mylearningdemo.activity.MoveActivity;
import com.example.simple_soul.mylearningdemo.activity.MusicActivity;
import com.example.simple_soul.mylearningdemo.activity.RxJavaActivity;
import com.example.simple_soul.mylearningdemo.activity.SQLiteActivity;
import com.example.simple_soul.mylearningdemo.activity.TestActivity;
import com.example.simple_soul.mylearningdemo.activity.ThreadActivity;
import com.example.simple_soul.mylearningdemo.activity.VibratorActivity;
import com.example.simple_soul.mylearningdemo.activity.WallpaperActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn10 = (Button) findViewById(R.id.btn10);
        btn11 = (Button) findViewById(R.id.btn11);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn10.setOnClickListener(this);
        btn11.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent;
        switch (v.getId())
        {
            case R.id.btn1:
                intent = new Intent(this, VibratorActivity.class);
                startActivity(intent);
                break;

            case R.id.btn2:
                intent = new Intent(this, ControlActivity.class);
                startActivity(intent);
                break;

            case R.id.btn3:
                intent = new Intent(this, MoveActivity.class);
                startActivity(intent);
                break;

            case R.id.btn4:
                intent = new Intent(this, WallpaperActivity.class);
                startActivity(intent);
                break;

            case R.id.btn5:
                intent = new Intent(this, MusicActivity.class);
                startActivity(intent);
                break;

            case R.id.btn6:
                intent = new Intent(this, AnimActivity.class);
                startActivity(intent);
                break;

            case R.id.btn7:
                intent = new Intent(this, RxJavaActivity.class);
                startActivity(intent);
                break;

            case R.id.btn8:
                intent = new Intent(this, ThreadActivity.class);
                startActivity(intent);
                break;

            case R.id.btn9:
                intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;

            case R.id.btn10:
                intent = new Intent(this, AChartIntentActivity.class);
                startActivity(intent);
                break;

            case R.id.btn11:
                intent = new Intent(this, SQLiteActivity.class);
                startActivity(intent);
                break;
        }
        overridePendingTransition(R.anim.translate_in, R.anim.translate_out);
    }
}
