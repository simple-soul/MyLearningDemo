package com.example.simple_soul.mylearningdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.simple_soul.mylearningdemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MPDynamicActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button add, remove;
    private LineChart lineChart;

    private LineDataSet lineDataSet;
    private LineData lineData;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpdynamic);

        initView();
        initData();
    }

    private void initView()
    {
        add = (Button) findViewById(R.id.dynamic_btn_add);
        remove = (Button) findViewById(R.id.dynamic_btn_remove);
        lineChart = (LineChart) findViewById(R.id.dynamic_chart);

        add.setOnClickListener(this);
        remove.setOnClickListener(this);
        lineChart.setDescription("");
        lineChart.setTouchEnabled(true);
    }

    private void initData()
    {
        List<Entry> yValues = new ArrayList<>();
        List<String> xValues = new ArrayList<>();
        lineDataSet = new LineDataSet(yValues, "测试");
        lineData = new LineData(xValues, lineDataSet);
        lineChart.setData(lineData);
    }

    private void addValue()
    {
        int entryCount = lineDataSet.getEntryCount();
        int dataSetCount = lineData.getDataSetCount();
        Log.i("main", "entryCount="+entryCount);
        Log.i("main", "dataSetCount="+dataSetCount);
//        lineDataSet.addEntry();
        lineData.addEntry(new Entry(random.nextInt(3000), entryCount), dataSetCount-1);
        lineData.addXValue("第"+entryCount+"条");
        notifyData();
    }

    private void removeValue()
    {
        int entryCount = lineDataSet.getEntryCount();
        int dataSetCount = lineData.getDataSetCount();
        if(entryCount > 0)
        {
            lineData.removeEntry(lineDataSet.getEntryForXIndex(entryCount - 1), dataSetCount - 1);
            lineData.removeXValue(entryCount - 1);
            notifyData();
        }
    }

    private void notifyData()
    {
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.dynamic_btn_add:
                addValue();
                break;

            case R.id.dynamic_btn_remove:
                removeValue();
                break;
        }
    }
}
