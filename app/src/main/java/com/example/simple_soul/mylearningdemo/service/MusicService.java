package com.example.simple_soul.mylearningdemo.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;

public class MusicService extends Service
{
    private Messenger messengerFormActivity;

    public static final int BIND = 0x01;
    public static final int START = 0x02;
    public static final int STOP = 0x03;
    public static final int PREVIOUS = 0x04;
    public static final int NEXT = 0x05;
    private MediaPlayer player;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Messenger messenger = new Messenger(mHandler);
        return messenger.getBinder();
    }

    private void startMusic()
    {
        MediaPlayer player = new MediaPlayer();

    }

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case BIND:
                    messengerFormActivity = msg.replyTo;
                    break;
            }
        }
    };
}
