package com.example.simple_soul.mylearningdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by simple_soul on 2017/5/5.
 */

public class MyPageAdapter extends FragmentPagerAdapter
{
    private List<Fragment> list;

    public MyPageAdapter(FragmentManager fm, List<Fragment> list)
    {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public int getCount()
    {
        return list.size();
    }
}
