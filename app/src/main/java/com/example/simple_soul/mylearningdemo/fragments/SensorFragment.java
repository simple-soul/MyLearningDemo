package com.example.simple_soul.mylearningdemo.fragments;

import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.domain.Common;
import com.example.simple_soul.mylearningdemo.domain.Dao.SensorDao;
import com.example.simple_soul.mylearningdemo.domain.SensorTable;
import com.example.simple_soul.mylearningdemo.fragment.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2017/6/4.
 */

public abstract class SensorFragment extends BaseFragment {

    private LineChart lineChart;
    private int page;
    private List<String> xValues = new ArrayList<>();
    private List<Entry> yValues = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
    private int[] colors;
    private int[] pColors;
    private LineDataSet lineDataSet;
    private LineData lineData;
    private boolean isLoop = true;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (isLoop) {
                setData();

                lineDataSet = new LineDataSet(yValues, "");
                lineDataSet.setCircleColors(pColors);
                lineDataSet.setColors(colors);
                lineData = new LineData(xValues, lineDataSet);
                lineChart.setData(lineData);

                notifyData();

                handler.postDelayed(this, 5000);
            }
        }
    };

    @Override
    public View initView() {
        View view = View.inflate(getContext(), R.layout.item_sensor_chart, null);
        Log.i("main", "initView");
        lineChart = (LineChart) view.findViewById(R.id.chart);
        lineChart.setDescription("");
        lineChart.getLegend().setEnabled(false);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);

        return view;
    }

    @Override
    public void initData() {
        page = getPage();
        isLoop = true;
        handler.post(runnable);
        Log.i("main", "initData");
    }

    public void setData() {
        xValues.clear();
        yValues.clear();

        SensorDao dao = new SensorDao(getContext());
        List<SensorTable> data = dao.getData();
        pColors = new int[data.size()];
        colors = new int[data.size()];
        switch (page) {
            case 0:
                for (int i = 0; i < data.size(); i++) {
                    SensorTable sensor = data.get(i);
                    yValues.add(new Entry(sensor.getTemp(), i));
                    if (sensor.getTemp() > Common.limits[page]) {
                        if (i - 1 >= 0) {
                            colors[i - 1] = Color.RED;
                        }
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.RED;
                    } else {
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.GREEN;
                    }
                }
                break;

            case 1:
                for (int i = 0; i < data.size(); i++) {
                    SensorTable sensor = data.get(i);
                    yValues.add(new Entry(data.get(i).getHum(), i));
                    if (sensor.getHum() > Common.limits[page]) {
                        if (i - 1 >= 0) {
                            colors[i - 1] = Color.RED;
                        }
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.RED;
                    } else {
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.GREEN;
                    }
                }

                break;

            case 2:
                for (int i = 0; i < data.size(); i++) {
                    SensorTable sensor = data.get(i);
                    yValues.add(new Entry(data.get(i).getLight(), i));
                    if (sensor.getLight() > Common.limits[page]) {
                        if (i - 1 >= 0) {
                            colors[i - 1] = Color.RED;
                        }
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.RED;
                    } else {
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.GREEN;
                    }
                }
                break;

            case 3:
                for (int i = 0; i < data.size(); i++) {
                    SensorTable sensor = data.get(i);
                    yValues.add(new Entry(data.get(i).getCo2(), i));
                    if (sensor.getCo2() > Common.limits[page]) {
                        if (i - 1 >= 0) {
                            colors[i - 1] = Color.RED;
                        }
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.RED;
                    } else {
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.GREEN;
                    }
                }
                break;

            case 4:
                for (int i = 0; i < data.size(); i++) {
                    SensorTable sensor = data.get(i);
                    yValues.add(new Entry(data.get(i).getPm25(), i));
                    if (sensor.getPm25() > Common.limits[page]) {
                        if (i - 1 >= 0) {
                            colors[i - 1] = Color.RED;
                        }
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.RED;
                    } else {
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.GREEN;
                    }
                }
                break;

            case 5:
                for (int i = 0; i < data.size(); i++) {
                    SensorTable sensor = data.get(i);
                    yValues.add(new Entry(data.get(i).getStatus(), i));
                    if (sensor.getStatus() > Common.limits[page]) {
                        if (i - 1 >= 0) {
                            colors[i - 1] = Color.RED;
                        }
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.RED;
                    } else {
                        colors[i] = Color.GREEN;
                        pColors[i] = Color.GREEN;
                    }
                }
                break;
        }
        for (int i = 0; i < data.size(); i++) {
            xValues.add(format.format(data.get(i).getTemp()));
        }
        Log.i("info", yValues.get(1).getVal() + "  " + xValues.get(1));
    }

    public void notifyData() {
        lineDataSet.notifyDataSetChanged();
        lineData.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        Log.i("info", "page=" + page);
        Log.i("info", "yCount" + lineDataSet.getEntryCount());
        Log.i("info", "lineData yCount" + lineData.getYValCount());
        Log.i("info", "notifyData");
    }

    public abstract int getPage();

    @Override
    public void onResume() {
        super.onResume();
        Log.i("main", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("main", "onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isLoop = false;
        Log.i("main", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("main", "onDestroy");
    }
}
