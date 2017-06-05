package com.example.simple_soul.mylearningdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.domain.Common;
import com.example.simple_soul.mylearningdemo.domain.Value2;

import java.util.List;

/**
 * Created by hp on 2017/6/4.
 */

public class MyGridAdapter extends BaseAdapter {

    private Context context;
    private List<Value2> data;

    public MyGridAdapter(Context context, List<Value2> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(context, R.layout.item_grid_sensor, null);
        TextView name = (TextView) convertView.findViewById(R.id.item_sensor_tv_name);
        TextView num = (TextView) convertView.findViewById(R.id.item_sensor_tv_num);
        RelativeLayout rl = (RelativeLayout) convertView.findViewById(R.id.item_sensor_rl);
        Value2 value = data.get(position);
        if(value.getValue() >= Common.limits[position])
        {
            rl.setBackgroundColor(Color.RED);
        }
        else
        {
            rl.setBackgroundColor(Color.GREEN);
        }
        name.setText(value.getName());
        num.setText(value.getValue()+"");
        return convertView;
    }
}
