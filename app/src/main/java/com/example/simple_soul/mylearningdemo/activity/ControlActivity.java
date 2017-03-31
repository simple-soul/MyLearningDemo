package com.example.simple_soul.mylearningdemo.activity;

import android.os.Handler;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.simple_soul.mylearningdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simple_soul on 2017/3/27.
 */

public class ControlActivity extends BaseActivity implements ViewSwitcher.ViewFactory
{
    private TextSwitcher switcher;

    private int i=1;
    private List<String> data = new ArrayList<>();
    private Handler mHandler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run()
        {
            switcher.setText(data.get(i));
            mHandler.postDelayed(this, 3000);
            if(i==2)
            {
                i=0;
            }
            else
            {
                i++;
            }
        }
    };

    @Override
    public View initView()
    {
        View view = View.inflate(this, R.layout.activity_control, null);

        switcher = (TextSwitcher) view.findViewById(R.id.control_ts);

        switcher.setFactory(this);
        switcher.setOutAnimation(this, R.anim.translate_out);
        switcher.setInAnimation(this, R.anim.translate_in);

        return view;
    }

    @Override
    public void initData()
    {
        data.add("一闪一闪亮晶晶");
        data.add("满天都是小星星");
        data.add("挂在天空闪光明");
        switcher.setText(data.get(0));
        mHandler.postDelayed(runnable, 3000);
    }

    @Override
    public View makeView()
    {
        TextView textView = new TextView(this);
        textView.setTextSize(36);
        return textView;
    }
}
