package com.zyx.baby.service.booth;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by ${$USER} on 2017/2/19.
 */

public class NotificationService extends Service{
    private static final String TAG = "NotificationService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService: onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService: onDestroy()");
    }


    class StartListenner extends Binder {

        public void startListenner(){

        }
    }


}
