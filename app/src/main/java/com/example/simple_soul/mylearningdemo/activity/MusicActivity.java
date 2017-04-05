package com.example.simple_soul.mylearningdemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;

import com.example.simple_soul.mylearningdemo.R;
import com.example.simple_soul.mylearningdemo.service.MusicService;

/**
 * Created by simple-soul on 17-4-2.
 */

public class MusicActivity extends BaseActivity implements ServiceConnection
{
    private Messenger messengerFormService;

    @Override
    public View initView()
    {
        View view = View.inflate(this, R.layout.activity_music, null);



        return view;
    }

    @Override
    public void initData()
    {
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder)
    {
        messengerFormService = new Messenger(binder);
        Messenger messenger = new Messenger(mHandler);
        Message message = Message.obtain();
        message.what = MusicService.BIND;
        message.replyTo = messenger;
        try
        {
            messengerFormService.send(message);
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {}

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what)
            {
                case MusicService.START:

                    break;

                case MusicService.STOP:

                    break;
            }
        }
    };
}
