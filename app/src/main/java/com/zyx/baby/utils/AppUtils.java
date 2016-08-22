package com.zyx.baby.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2016/8/13 0013.
 */
public class AppUtils {

    private AppUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /***
     * 获取应用名
     * @param context
     * @return
     */
    public static String getAppName(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 获取应用程序版本名称信息
     * @param context
     * @return 当前版本名称
     */
    public static String getVersionName(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
