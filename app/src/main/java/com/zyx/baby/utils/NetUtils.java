package com.zyx.baby.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class NetUtils {

    private NetUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否链接
     * @param context
     * @return
     */
    public static boolean isConnected(Context context){
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if(info != null && info.isConnected()){
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否链接了wifi
     * @param context
     * @return
     */
    public static boolean isWifi(Context context){
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm == null)
            return false;
        return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 打开网络设置界面
     * @param activity
     */
    public static void openSetting(Activity activity) {
        Intent intent = null;

        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivityForResult(intent, 0);
    }

}
