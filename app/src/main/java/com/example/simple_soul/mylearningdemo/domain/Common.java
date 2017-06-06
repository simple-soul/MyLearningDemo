package com.example.simple_soul.mylearningdemo.domain;

import android.content.Context;

import com.example.simple_soul.mylearningdemo.utils.SPUtils;


/**
 * Created by hp on 2017/6/4.
 */

public class Common {

    public static final String[] Menus = new String[]{"道路状态查询", "更改红绿灯时长", "我的座驾", "实时环境动态显示", "实时传感器数据查询与显示", "红绿灯时长统计", "公交车车载人数统计", "创意题"};


    public static final String START_URL = "http://172.22.21.230:8080/transportservice/action/";


    //小车充值 {"CarId":1,"Money":200, "UserName":"Z0004"}
    public static final String SET_CAR_ACCOUNT_RECHARGE = START_URL + "SetCarAccountRecharge.do";

    //查询小车余额 {"CarId":2, "UserName":"Z0004"}

    public static final String GET_CAR_ACCOUNT_BALANCE = START_URL + "GetCarAccountBalance.do";

    //查询公交车容量 {"BusId":1,"UserName":"Z0004"}
    public static final String GET_BUS_CAPACITY = START_URL + "GetBusCapacity.do";

    //查询道路状态:{"RoadId":1,"UserName":"Z0004"}
    public static final String GET_ROAD_STATUS = START_URL + "GetRoadStatus.do";

    //查询红绿灯配置信息
    public static final String GET_TRAFFIC_LIGHT_CONFIG_ACTION = START_URL + "GetTrafficLightConfigAction.do";

    public static final String SET_TRAFFIC_LIGHT_CONFIG = START_URL + "SetTrafficLightConfig.do";

    //查询所有传感器状态:{"UserName":"Z0004"}
    public static final String GET_ALL_SENSE = START_URL + "GetAllSense.do";

    public static final int[] limits = new int[]{30, 60, 1000, 5500, 170, 2};

    public static String getUserName(Context mContext) {
        return SPUtils.getString(mContext, "config", "username", "");
    }


}
