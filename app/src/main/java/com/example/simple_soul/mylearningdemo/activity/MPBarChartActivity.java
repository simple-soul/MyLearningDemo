package com.example.simple_soul.mylearningdemo.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.simple_soul.mylearningdemo.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MPBarChartActivity extends AppCompatActivity
{
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        initView();
        initData();
    }

    private void initView()
    {
        barChart = (BarChart) findViewById(R.id.bar_chart);

        barChart.setDescription("");
    }

    private void initData()
    {
        Random random = new Random();
        List<BarEntry> yValues = new ArrayList<>();
        List<String> xValues = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            yValues.add(new BarEntry(random.nextInt(5000), i));
            xValues.add("第"+i+"条");
        }
        BarDataSet barDataSet = new BarDataSet(yValues, "测试条目");
        barDataSet.setColors(new int[]{Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.DKGRAY});

        BarData barData = new BarData(xValues, barDataSet);

        barChart.setData(barData);
    }
}
