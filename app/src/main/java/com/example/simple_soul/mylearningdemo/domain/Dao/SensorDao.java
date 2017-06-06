package com.example.simple_soul.mylearningdemo.domain.Dao;

import android.content.Context;

import com.example.simple_soul.mylearningdemo.domain.SensorTable;
import com.example.simple_soul.mylearningdemo.utils.OrmDBOpenHelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by hp on 2017/6/4.
 */

public class SensorDao {

    private Dao dao = null;

    public SensorDao(Context context) {
        try {
            dao = OrmDBOpenHelper.getInstance(context).getDao(SensorTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setData(SensorTable sensorTable)
    {
        try {
            dao.create(sensorTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SensorTable> getData()
    {
        List<SensorTable> list = null;
        try {
            list = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<SensorTable> getMinData()
    {
        List<SensorTable> list = null;
        try {
            list = dao.queryBuilder().orderBy("id", false).limit((long) 12).query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
