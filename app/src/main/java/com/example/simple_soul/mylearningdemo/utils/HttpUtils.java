package com.example.simple_soul.mylearningdemo.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by simple_soul on 2017/4/17.
 */

public abstract class HttpUtils
{
    public HttpUtils(String urlStr, String json)
    {
        testHttpConnection(urlStr, json);
    }

    private void testHttpConnection(String urlStr, String json)
    {
        if (json == null)
        {
            json = "";
        }
        Observable.just(urlStr, json)
                .toList()
                .map(new Function<List<String>, String>()
                {
                    @Override
                    public String apply(List<String> strings) throws Exception
                    {
                        return startConnection(strings);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>()
                {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onSuccess(String s)
                    {
                        getResult(s);
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        Log.e("main", e.toString());
                    }
                });


    }

    public abstract void getResult(String s);


    private String startConnection(List<String> strings)
    {
        URL url = null;
        try
        {
            url = new URL(strings.get(0));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(strings.get(1).getBytes(Charset.forName("UTF-8")));
            Log.i("main", strings.get(1));
            outputStream.flush();
            outputStream.close();
            int code = connection.getResponseCode();
            if (code == 200)
            {
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String s = "";
                StringBuffer buffer = new StringBuffer();
                while ((s = reader.readLine()) != null)
                {
                    buffer.append(s);
                }
                return buffer.toString();
            }
            else
            {
                System.out.println("code=" + code);
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
