package com.zyx.baby.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2017/1/3.
 */
public class AssistService extends Service {
    private static final String TAG = "wxx";
    public class LocalBinder extends Binder {
        public AssistService getService() {
            return AssistService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "AssistService: onBind()");
        return new LocalBinder();
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        	super.onDestroy();
        Log.d(TAG, "AssistService: onDestroy()");
    }
}
