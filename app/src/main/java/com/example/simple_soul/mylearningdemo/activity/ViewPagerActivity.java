package com.example.simple_soul.mylearningdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.adapter.MyPageAdapter;
import com.example.simple_soul.mylearningdemo.fragment.Co2;
import com.example.simple_soul.mylearningdemo.fragment.Humidity;
import com.example.simple_soul.mylearningdemo.fragment.LightIntensity;
import com.example.simple_soul.mylearningdemo.fragment.Pm25;
import com.example.simple_soul.mylearningdemo.fragment.Temperature;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends FragmentActivity
{
    private ViewPager viewPager;
    private List<Fragment> list;
    private MyPageAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        initView();
        initData();
    }

    private void initView()
    {
        viewPager = (ViewPager) findViewById(R.id.pager_vp);
    }

    private void initData()
    {
        list = new ArrayList<>();
        list.add(new Pm25());
        list.add(new Co2());
        list.add(new LightIntensity());
        list.add(new Humidity());
        list.add(new Temperature());

        pagerAdapter = new MyPageAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(pagerAdapter);

        int page = getIntent().getIntExtra("page", 0);
        viewPager.setCurrentItem(page);
    }
}
