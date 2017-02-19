package com.zyx.baby.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.zyx.baby.R;
import com.zyx.baby.activity.IndexActivity;
import com.zyx.baby.utils.NotificationUtil;

/**
 * Created by ${$USER} on 2017/2/19.
 */

public class NotificationService extends Service{
    private static final String TAG = "NotificationService";
    private final int PID = android.os.Process.myPid();
    private AssistServiceConnection mConnection;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.d(TAG, "MyService: onCreate()");
        Notification notification = shwoServiceNotify();
//        Notification notification =  NotificationUtil.shwoServiceNotify(this);
//        NotificationManager manager =  (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
//        manager.notify(1,notification);
        NotificationService.this.startForeground(PID, notification);
//        CreateInform();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.d(TAG, "MyService: onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d(TAG, "MyService: onDestroy()");
    }


    public void setForeground() {
        //直播时间
//        mVideoTimer = new Timer(true);
//        mVideoTimerTask = new VideoTimerTask();
//        mVideoTimer.schedule(mVideoTimerTask, 1000, 1000);
        // sdk < 18 , 直接调用startForeground即可,不会在通知栏创建通知
        if (Build.VERSION.SDK_INT < 18) {
            this.startForeground(PID, getNotification());
            return;
        }
        NotificationService.this.startForeground(PID, getNotification());
        if (null == mConnection) {
            mConnection = new AssistServiceConnection();
        }
        this.bindService(new Intent(this, AssistService.class), mConnection,
                Service.BIND_AUTO_CREATE);
    }

    private class AssistServiceConnection implements ServiceConnection {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "MyService: onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            Log.d(TAG, "MyService: onServiceConnected");
            // sdk >=18			// 的，会在通知栏显示service正在运行，这里不要让用户感知，所以这里的实现方式是利用2个同进程的service，利用相同的notificationID，
            // 2个service分别startForeground，然后只在1个service里stopForeground，这样即可去掉通知栏的显示
            try {
                Service assistService = ((AssistService.LocalBinder) binder).getService();
                NotificationService.this.startForeground(PID, getNotification());
                assistService.startForeground(PID, getNotification());
                assistService.stopForeground(true);
                NotificationService.this.unbindService(mConnection);
                mConnection = null;
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private Notification getNotification() {
        // 定义一个notification
        if (builder == null) {
            builder = new Notification.Builder(this);
            builder.setContentText("正在为你检测中");
            builder.setContentTitle("宝贝");
            builder.setSmallIcon(R.drawable.logo);
            builder.setTicker("检测");
            builder.setAutoCancel(true);
            builder.setWhen(System.currentTimeMillis());
            Intent intent = new Intent(this, IndexActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(pendingIntent);
        }
        Notification notification = builder.build();
        return notification;
    }
    Notification.Builder builder = null;
    Context context;


    public  Notification shwoServiceNotify() {
        //先设定RemoteViews
        RemoteViews view_custom = new RemoteViews(this.getPackageName(), R.layout.view_custom);
        //设置对应IMAGEVIEW的ID的资源图片
        view_custom.setImageViewResource(R.id.custom_icon, R.drawable.logo);
        view_custom.setTextViewText(R.id.tv_custom_title, "宝贝尿了");
        view_custom.setTextColor(R.id.tv_custom_title, Color.BLACK);
        view_custom.setTextViewText(R.id.tv_custom_content, "蓝牙设备已开启，正在为您持续检测中");
        view_custom.setTextColor(R.id.tv_custom_content, Color.BLACK);
        view_custom.setTextViewText(R.id.tv_custom_temp, "15度");
        view_custom.setTextColor(R.id.tv_custom_temp, Color.BLACK);
        view_custom.setTextViewText(R.id.tv_custom_dum, "15%");
        view_custom.setTextColor(R.id.tv_custom_dum, Color.BLACK);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContent(view_custom)
                .setContentIntent(PendingIntent.getActivity(this, 4, new Intent(this, IndexActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("有新资讯")
                .setPriority(Notification.PRIORITY_HIGH)// 设置该通知优先级
                .setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
                .setSmallIcon(R.drawable.logo);
        Notification notify = mBuilder.build();
        notify.flags |= Notification.FLAG_NO_CLEAR;
        return notify;
    }

}
