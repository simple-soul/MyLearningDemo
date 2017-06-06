package com.example.simple_soul.mylearningdemo.activity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.adapter.MyGridAdapter;
import com.example.simple_soul.mylearningdemo.domain.Common;
import com.example.simple_soul.mylearningdemo.domain.Dao.SensorDao;
import com.example.simple_soul.mylearningdemo.domain.Dao.SensorMinDao;
import com.example.simple_soul.mylearningdemo.domain.RoadStatus;
import com.example.simple_soul.mylearningdemo.domain.Sensor2;
import com.example.simple_soul.mylearningdemo.domain.SensorTable;
import com.example.simple_soul.mylearningdemo.domain.SensorTableMin;
import com.example.simple_soul.mylearningdemo.domain.Value2;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.android.volley.Request.Method.POST;

/**
 * A simple {@link Fragment} subclass.
 */
public class MPDynamicDataActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private GridView gridView;

    private Timer timer = new Timer();
    private RequestQueue requestQueue;
    private Gson gson = new Gson();
    private int t=1;
    private List<Value2> data = new ArrayList<>();
    private MyGridAdapter gridAdapter;



    @Override
    public View initView() {
        View view = View.inflate(this, R.layout.fragment_fragment4, null);

        gridView = (GridView) view.findViewById(R.id.f4_grid);
        gridView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void initData() {
        requestQueue = Volley.newRequestQueue(this);

        gridAdapter = new MyGridAdapter(this, data);
        gridView.setAdapter(gridAdapter);

        timer.schedule(timerTask, 0, 5000);
    }

    private TimerTask timerTask = new TimerTask() {
        private Sensor2 sensor = null;
        private RoadStatus roadStatus = null;

        @Override
        public void run() {
            JsonObjectRequest request = new JsonObjectRequest(POST, Common.GET_ALL_SENSE, "{'UserName':'" + Common.getUserName(MPDynamicDataActivity.this) + "'}", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.i("main", jsonObject.toString());
                    sensor = gson.fromJson(jsonObject.toString(), Sensor2.class);
                    setData();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("main", volleyError.getMessage(), volleyError);
                }
            });
            requestQueue.add(request);

            JsonObjectRequest request1 = new JsonObjectRequest(POST, Common.GET_ROAD_STATUS, "{'RoadId':1,'UserName':'" + Common.getUserName(MPDynamicDataActivity.this) + "'}", new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {
                    Log.i("main", jsonObject.toString());
                    roadStatus = gson.fromJson(jsonObject.toString(), RoadStatus.class);
                    setData();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Log.e("main", volleyError.getMessage(), volleyError);
                }
            });
            requestQueue.add(request1);
        }

        private void setData()
        {
            if(sensor != null && roadStatus != null)
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        data.add(new Value2("空气温度", sensor.getTemperature()));
                        data.add(new Value2("空气湿度", sensor.getHumidity()));
                        data.add(new Value2("光照", sensor.getLightIntensity()));
                        data.add(new Value2("CO2", sensor.getCo2()));
                        data.add(new Value2("PM2.5", sensor.get_$Pm25252()));
                        data.add(new Value2("道路状况", roadStatus.getStatus()));
                        gridAdapter.notifyDataSetChanged();
                    }
                });
                Log.i("main", "开始存入数据库");
                SensorTable sensorTable = new SensorTable(sensor.get_$Pm25252(), sensor.getCo2(), sensor.getTemperature(), sensor.getLightIntensity(), sensor.getHumidity(), roadStatus.getStatus());
                SensorDao dao = new SensorDao(MPDynamicDataActivity.this);
                dao.setData(sensorTable);
                t++;
                if(t==12)
                {
                    int pm25 = 0;
                    int co2 = 0;
                    int temp = 0;
                    int light = 0;
                    int hum = 0;
                    int status = 0;
                    List<SensorTable> minData = dao.getMinData();
                    for (int i = 0; i < minData.size(); i++) {
                        SensorTable table = minData.get(i);
                        pm25+=table.getPm25();
                        co2+=table.getCo2();
                        temp+=table.getTemp();
                        light+=table.getLight();
                        hum+=table.getHum();
                        status+=table.getStatus();
                    }
                    SensorTableMin sensorTableMin = new SensorTableMin(pm25/12, co2/12, temp/12, light/12, hum/12, status/12);
                    SensorMinDao sensorMinDao = new SensorMinDao(MPDynamicDataActivity.this);
                    sensorMinDao.setData(sensorTableMin);
                    Log.i("main", "已存入min数据库");
                    t=1;
                }
                sensor = null;
                roadStatus = null;
            }
        }

    };



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewPagerActivity2.class);
        intent.putExtra("page", position);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
