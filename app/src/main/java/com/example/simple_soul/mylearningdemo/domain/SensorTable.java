package com.example.simple_soul.mylearningdemo.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by hp on 2017/6/4.
 */

@DatabaseTable(tableName = "sensor")
public class SensorTable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "pm25")
    private int pm25;
    @DatabaseField(columnName = "co2")
    private int co2;
    @DatabaseField(columnName = "temp")
    private int temp;
    @DatabaseField(columnName = "light")
    private int light;
    @DatabaseField(columnName = "hum")
    private int hum;
    @DatabaseField(columnName = "status")
    private int status;
    @DatabaseField(columnName = "time", canBeNull = false)
    private Date time = new Date();

    public SensorTable() {
    }

    public SensorTable(int pm25, int co2, int temp, int light, int hum, int status) {
        this.pm25 = pm25;
        this.co2 = co2;
        this.temp = temp;
        this.light = light;
        this.hum = hum;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getLight() {
        return light;
    }

    public void setLight(int light) {
        this.light = light;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
