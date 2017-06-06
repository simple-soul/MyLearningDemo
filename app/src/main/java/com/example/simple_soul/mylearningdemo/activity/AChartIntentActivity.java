package com.example.simple_soul.mylearningdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.simple_soul.mylearningdemo.R;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class AChartIntentActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achart_intent);

        btn1 = (Button) findViewById(R.id.chart_btn1);
        btn2 = (Button) findViewById(R.id.chart_btn2);
        btn3 = (Button) findViewById(R.id.chart_btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        Intent intent = null;
        switch (v.getId())
        {
            case R.id.chart_btn1:
                intent = initBar();
                break;

            case R.id.chart_btn2:
                intent = initPie();
                break;

            case R.id.chart_btn3:
//                intent = initLine();
                break;
        }
        startActivity(intent);
    }

    private Intent initLine()
    {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series = new XYSeries("一号线");
        series.add(1, 5);
        series.add(2, 3);
        series.add(3, 6);
        series.add(4, 12);
        series.add(5, 9);
        dataset.addSeries(series);
        XYSeries series2 = new XYSeries("二号线");
        series2.add(1, 9);
        series2.add(2, 15);
        series2.add(3, 7);
        series2.add(4, 3);
        series2.add(5, 6);

        dataset.addSeries(series2);
        return null;
    }

    private Intent initBar()
    {
        //首先要在Manifest中注册一个activity
        //<activity android:name="org.achartengine.GraphicalActivity"/>
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYSeries series = new XYSeries("一号线");
        series.add(1, 5);
        series.add(2, 3);
        series.add(3, 6);
        series.add(4, 12);
        series.add(5, 9);
        dataset.addSeries(series);

        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setXAxisMax(6);
        renderer.setXAxisMin(0);
        renderer.setXLabelsColor(Color.WHITE);
        renderer.setXTitle("X轴");
        renderer.setYAxisMax(15);
        renderer.setYAxisMin(0);
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setYLabelsColor(0, Color.WHITE);
        renderer.setYTitle("Y轴");
        renderer.setLabelsTextSize(30);
        renderer.setLegendTextSize(30);
        renderer.setChartTitle("条形图");
        renderer.setAxisTitleTextSize(30);
        renderer.setChartTitleTextSize(50);
        renderer.setMargins(new int[]{80, 70, 70, 10});
        renderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        renderer.setBarWidth(80);
        renderer.setBarSpacing(30);

        XYSeriesRenderer renderer1 = new XYSeriesRenderer();
        renderer1.setColor(Color.RED);
        renderer1.setDisplayChartValues(true);
        renderer1.setChartValuesTextSize(30);
        renderer1.setChartValuesSpacing(20);
        renderer1.setChartValuesTextAlign(Paint.Align.RIGHT);
        renderer.addSeriesRenderer(renderer1);

        return ChartFactory.getBarChartIntent(this, dataset, renderer, BarChart.Type.DEFAULT);
    }

    private Intent initPie()
    {
        //首先要在Manifest中注册一个activity
        //<activity android:name="org.achartengine.GraphicalActivity"/>
        CategorySeries series = new CategorySeries("饼图");
        series.add("一月", 300);
        series.add("二月", 200);
        series.add("三月", 500);
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setChartTitle("饼图");
        renderer.setLabelsTextSize(40);
        renderer.setChartTitleTextSize(60);
        renderer.setLegendTextSize(30);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setMargins(new int[]{50, 30, 10, 30});
        SimpleSeriesRenderer renderer1 = new SimpleSeriesRenderer();
        renderer1.setColor(Color.RED);
        renderer.addSeriesRenderer(renderer1);
        SimpleSeriesRenderer renderer2 = new SimpleSeriesRenderer();
        renderer2.setColor(Color.BLUE);
        renderer.addSeriesRenderer(renderer2);
        SimpleSeriesRenderer renderer3 = new SimpleSeriesRenderer();
        renderer3.setColor(Color.GREEN);
        renderer.addSeriesRenderer(renderer3);
        return ChartFactory.getPieChartIntent(this, series, renderer, "PieActivity");
    }
}
