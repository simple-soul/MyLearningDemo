package com.example.simple_soul.mylearningdemo.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.adapter.GridViewAdapter;
import com.example.simple_soul.mylearningdemo.domain.Sensor;
import com.example.simple_soul.mylearningdemo.domain.Value;
import com.example.simple_soul.mylearningdemo.utils.DBOpenHelper;
import com.example.simple_soul.mylearningdemo.utils.HttpUtils;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SQLiteActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    private GridView gridView;

    private String url = "http://172.22.21.230:8080/transportservice/action/GetAllSense.do";
    private List<Sensor> data;
    private Handler handler = new Handler();
    private GridViewAdapter adapter;
    private SQLiteDatabase writableDatabase;
    private ContentValues contentValues;
    private DBOpenHelper helper;
    private SQLiteDatabase readableDatabase;
    private List<Value> values;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        initView();
        initData();

    }

    private void initView()
    {
        gridView = (GridView) findViewById(R.id.sqlite_grid);

        gridView.setOnItemClickListener(this);
    }

    private void initData()
    {
        helper = new DBOpenHelper(SQLiteActivity.this, "sensor");
        writableDatabase = helper.getWritableDatabase();
        isLoop = true;
        handler.postDelayed(runnable, 1);
    }

    private boolean isLoop = true;
    private Runnable runnable = new Runnable()
    {
        @Override
        public void run()
        {
            Log.i("main", "循环");
            if (isLoop)
            {
                handler.postDelayed(runnable, 1000);
            }
            new HttpUtils(url, "{\"UserName\":\"Z0004\"}")
            {
                @Override
                public void getResult(String s)
                {
                    try
                    {
                        if (data == null)
                        {
                            data = new ArrayList<>();
                        }
                        else
                        {
                            data.clear();
                        }
                        Log.i("main", s);
                        JSONObject jsonObject = new JSONObject(s);
                        Sensor pm25 = new Sensor("pm2.5", jsonObject.getInt("pm2.5"));
                        Sensor co2 = new Sensor("co2", jsonObject.getInt("co2"));
                        Sensor LightIntensity = new Sensor("LightIntensity",
                                jsonObject.getInt("LightIntensity"));
                        Sensor humidity = new Sensor("humidity", jsonObject.getInt("humidity"));
                        Sensor temperature = new Sensor("temperature",
                                jsonObject.getInt("temperature"));


                        data.add(pm25);
                        data.add(co2);
                        data.add(LightIntensity);
                        data.add(humidity);
                        data.add(temperature);


                        if (contentValues == null)
                        {
                            contentValues = new ContentValues();
                        }
                        contentValues.put("pm25", pm25.getValue());
                        contentValues.put("co2", co2.getValue());
                        contentValues.put("LightIntensity", LightIntensity.getValue());
                        contentValues.put("humidity", humidity.getValue());
                        contentValues.put("temperature", temperature.getValue());
                        writableDatabase.insert(DBOpenHelper.TABLE_NAME, null, contentValues);
                        contentValues.clear();

                        if (adapter == null)
                        {
                            adapter = new GridViewAdapter(SQLiteActivity.this, data);
                            gridView.setAdapter(adapter);
                        }
                        else
                        {
                            adapter.notifyDataSetChanged();
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            };

        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        if (readableDatabase == null)
        {
            readableDatabase = helper.getReadableDatabase();
        }
        if(values == null)
        {
            values = new ArrayList<>();
        }
        Intent intent = new Intent(SQLiteActivity.this, ViewPagerActivity.class);
        switch (position)
        {
            case 0:
//                getValues("pm25");
                break;
            case 1:
//                getValues("co2");
                break;
            case 2:
//                getValues("LightIntensity");
                break;
            case 3:
//                getValues("humidity");
                break;
            case 4:
//                getValues("temperature");
                break;
        }
        intent.putExtra("page", position);
        startActivity(intent);
        values.clear();
    }

    private void getValues(String name)
    {
        Cursor cursor = readableDatabase.rawQuery("select "+name+", time from "+DBOpenHelper.TABLE_NAME , null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                Value value = new Value(cursor.getInt(cursor.getColumnIndex(name)), cursor.getString(cursor.getColumnIndex("time")).substring(10));
                values.add(value);
            }
        }
        Intent lineChart = getLineChart(values, name);
        startActivity(lineChart);
    }

    private Intent getLineChart(List<Value> values, String name)
    {
        Log.i("main", values.size()+""+values.get(2).getValue());
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series = new XYSeries(name);
        int max = 0;
        for (int i = 0; i < values.size(); i++)
        {
            int y = values.get(i).getValue();
            series.add(i, y);
            if(y > max)
            {
                max = y;
            }
        }
        dataset.addSeries(series);

        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setXAxisMax(6);
        renderer.setXLabelsColor(Color.RED);
        for (int i = 0; i < values.size(); i++)
        {
            renderer.addXTextLabel(i, values.get(i).getTime());
        }
        renderer.setXLabelsAngle(35);
        renderer.setXTitle("时间");
        renderer.setXLabels(0);
        renderer.setXAxisMin(0);
        renderer.setYAxisMax(values.size());
        renderer.setYAxisMax(max);
        renderer.setYAxisMin(0);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setYLabelsColor(0, Color.RED);
        renderer.setYTitle("数值");
        renderer.setLabelsTextSize(30);
        renderer.setLegendTextSize(30);
        renderer.setChartTitle(name);
        renderer.setAxisTitleTextSize(30);
        renderer.setChartTitleTextSize(50);
        renderer.setMargins(new int[]{80, 70, 70, 10});
        renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        renderer.setShowGrid(true);// 显示网格
        renderer.setBackgroundColor(Color.BLACK);

        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        renderer1.setPointStyle(PointStyle.CIRCLE);
        renderer1.setFillPoints(true);// 填充点（显示的点是空心还是实心）
        renderer1.setChartValuesSpacing(20);// 显示的点的值与图的距离
        renderer1.setColor(Color.RED);
        renderer1.setDisplayChartValues(true);
        renderer1.setChartValuesTextSize(30);
        renderer1.setLineWidth(3);// 设置线宽
//        renderer1.setChartValuesTextAlign(Paint.Align.RIGHT);
        renderer.addSeriesRenderer(renderer1);

        return ChartFactory.getLineChartIntent(this, dataset, renderer, "LineChartActivity");
    }

    @Override
    protected void onDestroy()
    {
//        isLoop = false;
        super.onDestroy();
    }
}
