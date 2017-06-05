package com.example.simple_soul.mylearningdemo.utils;

import android.content.Context;

/**
 * Created by hp on 2017/6/4.
 */

public class SPUtils {

    public static void putString(Context context, String sp_name, String key, String value) {

        context.getSharedPreferences(sp_name, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .commit();
    }


    public static String getString(Context context, String sp_name, String key, String default_value) {

        return context.getSharedPreferences(sp_name, Context.MODE_PRIVATE).getString(key, default_value);
    }

    public static void putBoolean(Context context, String sp_name, String key, boolean value) {

        context.getSharedPreferences(sp_name, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(key, value)
                .commit();
    }

    public static boolean getBoolean(Context context, String sp_name, String key, boolean default_value) {

        return context.getSharedPreferences(sp_name, Context.MODE_PRIVATE).getBoolean(key, default_value);
    }


}
