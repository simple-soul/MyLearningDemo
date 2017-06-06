package com.example.simple_soul.mylearningdemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.simple_soul.mylearningdemo.domain.SensorTable;
import com.example.simple_soul.mylearningdemo.domain.SensorTableMin;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hp on 2017/6/4.
 */

public class OrmDBOpenHelper extends OrmLiteSqliteOpenHelper {

    private static OrmDBOpenHelper DBHelper = null;
    private Map<String, Dao> mapDaos = new HashMap<String, Dao>();
    private static String DB_NAME = "ITS.db";

    private OrmDBOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, SensorTable.class);
            TableUtils.createTable(connectionSource, SensorTableMin.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, SensorTable.class, true);
            TableUtils.dropTable(connectionSource, SensorTableMin.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public synchronized static OrmDBOpenHelper getInstance(Context context) {

        if (DBHelper == null) {


            synchronized (Object.class) {

                if (DBHelper == null) {


                    DBHelper = new OrmDBOpenHelper(context);


                }


            }

        }


        return DBHelper;

    }


    public synchronized Dao getInstanceDao(Class clazz) {


        Dao dao = null;

        String clazzName = clazz.getSimpleName();

        dao = mapDaos.get(clazzName);

        if (dao == null) {

            try {
                dao = super.getDao(clazz);
                mapDaos.put(clazzName, dao);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return dao;
    }

}
