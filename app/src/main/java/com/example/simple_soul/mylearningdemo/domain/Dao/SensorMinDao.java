package com.example.simple_soul.mylearningdemo.domain.Dao;

import android.content.Context;

import com.example.simple_soul.mylearningdemo.domain.SensorTableMin;
import com.example.simple_soul.mylearningdemo.utils.OrmDBOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hp on 2017/6/4.
 */

public class SensorMinDao {

    private Dao dao = null;

    public SensorMinDao(Context context) {
        try {
            dao = OrmDBOpenHelper.getInstance(context).getDao(SensorTableMin.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(SensorTableMin SensorTableMin)
    {
        try {
            dao.create(SensorTableMin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SensorTableMin> getData()
    {
        List<SensorTableMin> list = null;
        try {
            list = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
