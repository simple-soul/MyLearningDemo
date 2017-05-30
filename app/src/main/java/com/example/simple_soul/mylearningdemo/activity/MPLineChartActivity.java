package com.example.simple_soul.mylearningdemo.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.domain.Value;
import com.example.simple_soul.mylearningdemo.utils.DBOpenHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MPLineChartActivity extends AppCompatActivity
{
    private LineChart lineChart;

    private SQLiteDatabase readableDatabase;
    private List<Value> values = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpline_chart);

        initView();
        initData();
    }

    private void initView()
    {
        lineChart = (LineChart) findViewById(R.id.line_chart);

        lineChart.setDescription("");
    }

    private void initData()
    {
        DBOpenHelper helper = new DBOpenHelper(this, DBOpenHelper.DB_NAME);
        readableDatabase = helper.getReadableDatabase();
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run()
        {
            getValueFromDatabase();
            handler.postDelayed(this, 1000);
        }
    };

    private void getValueFromDatabase()
    {
        values.clear();
        Cursor cursor = readableDatabase.rawQuery(
                "select co2, time from " + DBOpenHelper.TABLE_NAME + " order by _id desc limit 5",
                null);
        while (cursor.moveToNext())
        {
            int co2 = cursor.getInt(cursor.getColumnIndex("co2"));
            String time = cursor.getString(cursor.getColumnIndex("time")).substring(10);
            Value value = new Value(co2, time);
            values.add(value);
        }
        List<Entry> yValues = new ArrayList<>();
        List<String> xValues = new ArrayList<>();
        for (int i = values.size()-1; i >= 0 ; i--)
        {
            yValues.add(new Entry(values.get(i).getValue(), values.size()-1-i));
            xValues.add(values.get(i).getTime());
        }
        LineDataSet lineDataSet = new LineDataSet(yValues, "CO2");
        lineDataSet.setValueTextSize(10);

        LineData lineData = new LineData(xValues, lineDataSet);
        lineChart.setData(lineData);
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }
}
