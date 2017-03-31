package com.example.simple_soul.mylearningdemo.activity;

import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.simple_soul.mylearningdemo.R;

/**
 * Created by simple_soul on 2017/3/27.
 */

public class VibratorActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener
{
    private RadioGroup group;
    private Button start, stop;
    private Vibrator vibrator;

    private boolean isDefault = true;

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_vibrator);
//    }

    @Override
    public View initView()
    {
        View view = View.inflate(this, R.layout.activity_vibrator, null);

        group = (RadioGroup) view.findViewById(R.id.vibrator_rg);
        start = (Button) view.findViewById(R.id.vibrator_btn_start);
        stop = (Button) view.findViewById(R.id.vibrator_btn_stop);

        group.setOnCheckedChangeListener(this);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);

        return view;
    }

    @Override
    public void initData()
    {

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.vibrator_btn_start:
                if(isDefault)
                {
                    vibrator.vibrate(5000);
                    Log.i("main", "vibrator");
                }
                else
                {
                    /*
                    数组参数意义：第一个参数为等待指定时间后开始震动，震动时间为第二个参数。
                        后边的参数依次为等待震动和震动的时间
                        第二个参数为重复次数，-1为不重复，0为一直震动
                     */
                    vibrator.vibrate(new long[]{1000,1000,1000,5000}, 0);
                }
                break;

            case  R.id.vibrator_btn_stop:
                vibrator.cancel();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        if (checkedId == R.id.rb1)
        {
            isDefault = true;
        }
        else
        {
            isDefault = false;
        }
    }
}
