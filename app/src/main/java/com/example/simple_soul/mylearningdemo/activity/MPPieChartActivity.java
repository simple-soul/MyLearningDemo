package com.example.simple_soul.mylearningdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.simple_soul.mylearningdemo.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;
import java.util.Random;

public class MPPieChartActivity extends AppCompatActivity
{
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mppie_chart);

        initView();
        initData();
    }

    private void initView()
    {
        pieChart = (PieChart) findViewById(R.id.pie_chart);

        pieChart.setDescription("");
//        pieChart.setCenterTextSizePixels(30);
        pieChart.setCenterText("饼图");
//        pieChart.setHoleRadius(30f);
//        pieChart.setTransparentCircleRadius(0);
        pieChart.setCenterTextSize(30);
//        pieChart.setUsePercentValues(true);
//        pieChart.setDrawHoleEnabled(false);
//        pieChart.setTouchEnabled(false);
    }

    private void initData()
    {
        ArrayList yVals = new ArrayList();
        ArrayList xVals = new ArrayList();
        Random random = new Random();
        for (int i = 0; i < 3; i++)
        {
            yVals.add(new Entry(random.nextFloat(), i));
            xVals.add("x轴:" + i);
        }
        PieDataSet set1 = new PieDataSet(yVals, "Content");
        set1.setColors(new int[]{Color.RED, Color.GREEN, Color.BLUE});
        set1.setValueTextSize(20);
//        ArrayList dataSets = new ArrayList();
//        dataSets.add(set1);
        PieData data = new PieData(xVals, set1);
        pieChart.setData(data);
    }
}
