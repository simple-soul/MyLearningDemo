package com.example.simple_soul.mylearningdemo.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.simple_soul.mylearningdemo.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestActivity extends AppCompatActivity
{
    XYMultipleSeriesDataset mDataset;
    // 多个系列的数据集合,即多条线的数据集合
    XYSeries series;
    // 一个系列的数据，即一条线的数据集合

    XYMultipleSeriesRenderer mRenderer;
    // 多个系列的环境渲染，即整个画折线的区域
    XYSeriesRenderer r;
    // 一个系列的环境渲染，即一条线的环境渲染
    GraphicalView view;
    // 整个view
    int i = 5;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ll = (LinearLayout) findViewById(R.id.ll);
        lineView();
    }

    public void lineView()
    {
        // 同样是需要数据dataset和视图渲染器renderer
        mDataset = new XYMultipleSeriesDataset();
        series = new XYSeries("Frist");
        series.add(1, 6);
        series.add(2, 5);
        series.add(3, 7);
        series.add(4, 4);
        series.add(5, 10);
        mDataset.addSeries(series);

        mRenderer = new XYMultipleSeriesRenderer();

        mRenderer.setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);
        // 设置图表的X轴的当前方向
        mRenderer.setXTitle("X轴");// 设置为X轴的标题
        mRenderer.setYTitle("Y轴");// 设置y轴的标题
        mRenderer.setAxisTitleTextSize(40);// 设置轴标题文本大小
        mRenderer.setChartTitle("ChartTest");// 设置图表标题
        mRenderer.setChartTitleTextSize(60);// 设置图表标题文字的大小
        mRenderer.setLabelsTextSize(40);// 设置标签的文字大小
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);//y轴的字显示在左边（反的）
        mRenderer.setYLabelsColor(0, Color.RED);
        mRenderer.setLegendTextSize(40);// 设置图例文本大小
        mRenderer.setLegendHeight(100);

        mRenderer.setPointSize(10f);// 设置点的大小
        mRenderer.setYAxisMin(0);// 设置y轴最小值是0
        mRenderer.setYAxisMax(15);
        mRenderer.setYLabels(3);// 设置Y轴刻度个数（貌似不太准确）
        mRenderer.setXAxisMax(5);

        mRenderer.setShowGrid(true);// 显示网格
        // 将x标签栏目显示如：1,2,3,4替换为显示1月，2月，3月，4月，5月
        mRenderer.addXTextLabel(1, "1月");
        mRenderer.addXTextLabel(2, "2月");
        mRenderer.addXTextLabel(3, "3月");
        mRenderer.addXTextLabel(4, "4月");
        mRenderer.addXTextLabel(5, "5月");
        mRenderer.setXLabels(0);// 设置只显示如1月，2月等替换后的东西，不显示1,2,3等
        mRenderer.setMargins(new int[]{100, 50, 50, 10});// 设置视图位置(上左下右)
        mRenderer.setPanEnabled(true, true);
        // 第一个参数设置X轴是否可滑动，第二个参数设置Y轴是够可滑动
        r = new XYSeriesRenderer();
        r.setColor(Color.BLUE);// 设置颜色
        r.setPointStyle(PointStyle.CIRCLE);// 设置点的样式
        r.setFillPoints(true);// 填充点（显示的点是空心还是实心）
        r.setDisplayChartValues(true);// 将点的值显示出来
        r.setChartValuesSpacing(20);// 显示的点的值与图的距离
        r.setChartValuesTextSize(40);// 点的值的文字大小
//
//         r.setFillBelowLine(true);//是否填充折线图的下方
//         r.setFillBelowLineColor(Color.GREEN);//填充的颜色，如果不设置就默认与线的
        r.addFillOutsideLine(new XYSeriesRenderer.FillOutsideLine(
                XYSeriesRenderer.FillOutsideLine.Type.BELOW));
        r.getFillOutsideLine()[0].setColor(Color.GREEN);

        //颜色一致
        r.setLineWidth(3);// 设置线宽
        mRenderer.addSeriesRenderer(r);

        view = ChartFactory.getLineChartView(this, mDataset, mRenderer);
        ll.addView(view);
        //将画好折线的view添加到xml中的一个布局里
    }

    private String nowTime()
    {
        //求当前系统的时分秒
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());

    }
}
