package com.example.simple_soul.mylearningdemo.domain;

/**
 * Created by simple_soul on 2017/4/26.
 */

public class Sensor
{
    private String name;
    private int value;

    public Sensor(String name, int value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
    }
}
