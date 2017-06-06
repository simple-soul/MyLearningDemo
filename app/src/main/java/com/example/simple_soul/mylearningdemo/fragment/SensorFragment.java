package com.example.simple_soul.mylearningdemo.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.simple_soul.mylearningdemo.domain.Value;
import com.example.simple_soul.mylearningdemo.utils.DBOpenHelper;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simple_soul on 2017/5/5.
 */

public abstract class SensorFragment extends BaseFragment
{
    private List<Value> values = new ArrayList<>();
    private String name;
    private XYMultipleSeriesDataset dataset;
    private XYSeries series;
    private XYMultipleSeriesRenderer renderer;
    private GraphicalView view;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run()
        {
            getValues();
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public View initView()
    {
        name = getName();
        getValues();
        handler.postDelayed(runnable, 1000);
        view = getLineChart();
        return view;
    }



    @Override
    public void initData()
    {

    }

    @Override
    public void onPause()
    {
        super.onPause();
        values.clear();
    }

    protected abstract String getName();

    private void getValues()
    {
        DBOpenHelper helper = new DBOpenHelper(mActivity, "sensor");
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("select "+name+", time from "+ DBOpenHelper.TABLE_NAME+" order by _id desc limit 7", null);
        if (cursor != null)
        {
            while (cursor.moveToNext())
            {
                Value value = new Value(cursor.getInt(cursor.getColumnIndex(name)), cursor.getString(cursor.getColumnIndex("time")).substring(10));
                Log.i("info", name+cursor.getInt(cursor.getColumnIndex(name)));
                values.add(value);
            }
        }
        if(dataset == null)
        {
            dataset = new XYMultipleSeriesDataset();
            series = new XYSeries(name);
            renderer = new XYMultipleSeriesRenderer();
        }
        else
        {
            dataset.clear();
            series.clear();
        }
        int max = 0;
        for (int i = values.size()-1; i > -1; i--)
        {
            int y = values.get(i).getValue();
            series.add(values.size()-i-1, y);
            if(y > max)
            {
                max = y;
            }
        }
        for (int i = values.size()-1; i > -1; i--)
        {
            renderer.addXTextLabel(values.size()-i-1, values.get(i).getTime());
        }
//        renderer.setYAxisMax(values.size());
        dataset.addSeries(series);
        if(view != null)
        {
            view.repaint();
        }
        values.clear();
    }

    private GraphicalView getLineChart()
    {
        renderer.setXAxisMax(6);
        renderer.setXLabelsColor(Color.RED);
        renderer.setXLabelsAngle(35);
        renderer.setXTitle("时间");
        renderer.setXLabels(0);
        renderer.setXAxisMin(0);
        renderer.setYAxisMax(300);
        renderer.setYAxisMin(0);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setYLabelsColor(0, Color.RED);
        renderer.setYTitle("数值");
        renderer.setZoomButtonsVisible(true);
        renderer.setZoomEnabled(true, true);
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
//        renderer1.setChartValuesTextAlign(Paint.Align.LEFT);
        renderer.addSeriesRenderer(renderer1);

        return ChartFactory.getLineChartView(mActivity, dataset, renderer);
    }
}
