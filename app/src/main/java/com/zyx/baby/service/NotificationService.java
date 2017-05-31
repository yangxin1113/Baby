package com.zyx.baby.service;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.zyx.baby.R;
import com.zyx.baby.activity.IndexActivity;
import com.zyx.baby.event.BoothEvent;
import com.zyx.baby.service.booth.BluetoothChatService;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.utils.NotificationUtil;

import com.zyx.baby.utils.TipHelper;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Field;


/**
 * Created by ${$USER} on 2017/2/19.
 */

public class NotificationService extends Service{

    // Debugging
    private static final boolean D = true;

    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";
    private String mConnectedDeviceName = null;


    private static final String TAG = "NotificationService";

    private BluetoothChatService mChatService = null;

    private BluetoothDevice device;

    private BlueBinder blueBinder = new BlueBinder();

    private BluetoothAdapter mBluetoothAdapter = null;

    private MediaPlayer mplay;
    private TipHelper tipHelper;
    private long[] pattern={100,400,100,400};

    @Override
    public IBinder onBind(Intent intent) {
        device = mBluetoothAdapter.getRemoteDevice(intent.getStringExtra("address"));
        return blueBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        tipHelper = new TipHelper(getApplicationContext());
        Log.d(TAG, "NotificationService: onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "NotificationService: onStartCommand()");
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mChatService = new BluetoothChatService(this, mHandler);
        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "NotificationService: onDestroy()");
    }


    public class BlueBinder extends Binder {

        public void startConnect() {
            mChatService.connect(device);
        }
    }


    // The Handler that gets information back from the BluetoothChatService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_STATE_CHANGE:
                    if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            LSUtils.showToast(getApplication(),"已连接:"+mConnectedDeviceName);
                            BoothEvent boothEvent = new BoothEvent();
                            boothEvent.setTag("success");
                            EventBus.getDefault().post(boothEvent);
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            LSUtils.showToast(getApplication(),"连接中");
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                            break;
                        case BluetoothChatService.STATE_NONE:
                            LSUtils.showToast(getApplication(),"未连接");
                            break;
                    }
                    break;
                case MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    String writeMessage = new String(writeBuf);
                    break;
                case MESSAGE_READ:
                    String readMessage = (String) msg.obj;
                    // construct a string from the valid bytes in the buffer
                    LSUtils.i("data",readMessage);
                    checkRang(readMessage);

                    break;
                case MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                    Toast.makeText(getApplicationContext(), "已连接 "
                            + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    createNotification();

                    break;
                case MESSAGE_TOAST:
                    Toast.makeText(getApplicationContext(), "连接失败",
                            Toast.LENGTH_SHORT).show();
                    BoothEvent boothEvent = new BoothEvent();
                    boothEvent.setTag("fail");
                    EventBus.getDefault().post(boothEvent);

                    break;
            }
        }
    };

    private void checkRang(String readMessage) {
        BoothEvent boothEvent = new BoothEvent();
        boothEvent.setTag("data");
        boothEvent.setObj(readMessage);
        EventBus.getDefault().post(boothEvent);
        String[] data = readMessage.split("#");




        String uri = "android.resource://" + getApplicationContext().getPackageName() + "/"+R.raw.rain_in_march;
        Uri no=Uri.parse(uri);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
                no);

        if(Double.valueOf(data[0])>=38 || Double.valueOf(data[1])>=60){
            tipHelper.Vibrate(pattern, true);
            tipHelper.playvoid(1);

        }else {
            tipHelper.destroy();
            /*if(r.isPlaying()){
                r.stop();
            }*/
        }
    }

    private void setRingtoneRepeat(Ringtone ringtone){
        Class<Ringtone> clazz = Ringtone.class;
        try {
            Field audio = clazz.getDeclaredField("mAudio");
            audio.setAccessible(true);
            MediaPlayer target = (MediaPlayer) audio.get(ringtone);
            target.setLooping(true);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createNotification(){

        Intent intent = new Intent(this, IndexActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new android.support.v4.app.NotificationCompat.Builder(this)
                .setContentTitle("老人助手")
                .setContentText("正在为您检测中")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setContentIntent(pi)
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                //        .setVibrate(new long[]{0, 1000, 1000, 1000})
                //        .setLights(Color.GREEN, 1000, 1000)
                .setDefaults(android.support.v4.app.NotificationCompat.DEFAULT_ALL)
                //        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
//                .setStyle(new android.support.v4.app.NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
                .setPriority(android.support.v4.app.NotificationCompat.PRIORITY_MAX)
                .build();
        startForeground(1, notification);

        /*Intent intent = new Intent(this, IndexActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                *//**设置通知左边的大图标**//*
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo))
                *//**设置通知右边的小图标**//*
                .setSmallIcon(R.drawable.logo)
                *//**通知首次出现在通知栏，带上升动画效果的**//*
                .setTicker("检测开启")
                *//**设置通知的标题**//*
                .setContentTitle("老人助手")
                *//**设置通知的内容**//*
                .setContentText("正在为您检测中")
                *//**通知产生的时间，会在通知信息里显示**//*
                .setWhen(System.currentTimeMillis())
                *//**设置该通知优先级**//*
                .setPriority(Notification.PRIORITY_DEFAULT)
                *//**设置这个标志当用户单击面板就可以让通知将自动取消**//**//*
                .setAutoCancel(true)*//*
                *//**设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)**//*
                //.setOngoing(false)
                *//**向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：**//*
                .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
//                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setDefaults(android.support.v4.app.NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pi)


                .build();

        startForeground(1, notification);*/
        /*NotificationManager notificationManager = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        *//**发起通知**//*
        notificationManager.notify(0, notification);*/

    }

}
