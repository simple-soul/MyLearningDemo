package com.example.simple_soul.mylearningdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.adapter.MyFragmentListAdapter;
import com.example.simple_soul.mylearningdemo.fragments.Co2;
import com.example.simple_soul.mylearningdemo.fragments.Hum;
import com.example.simple_soul.mylearningdemo.fragments.Light;
import com.example.simple_soul.mylearningdemo.fragments.Pm25;
import com.example.simple_soul.mylearningdemo.fragments.State;
import com.example.simple_soul.mylearningdemo.fragments.Temp;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity2 extends AppCompatActivity {

    private ViewPager viewPager;
    private Button back;
    private TextView title;

    private List<Fragment> fragmentList = new ArrayList<>();
    private MyFragmentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);

        initView();
        initData();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.pager_viewpager);
        back = (Button) findViewById(R.id.pager_btn);
        title = (TextView) findViewById(R.id.pager_tv_title);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initData() {

        fragmentList.add(new Temp());
        fragmentList.add(new Hum());
        fragmentList.add(new Light());
        fragmentList.add(new Co2());
        fragmentList.add(new Pm25());
        fragmentList.add(new State());

        adapter = new MyFragmentListAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);

        int page = getIntent().getIntExtra("page", 0);
        setMyTitle(page);
        viewPager.setCurrentItem(page);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.i("main", "viewpager滑动了");
                setMyTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setMyTitle(int position) {
        switch (position) {
            case 0:
                title.setText("空气温度");
                break;
            case 1:
                title.setText("空气湿度");
                break;
            case 2:
                title.setText("光照强度");
                break;
            case 3:
                title.setText("CO2");
                break;
            case 4:
                title.setText("PM2.5");
                break;
            case 5:
                title.setText("道路状况");
                break;
        }
    }
}



