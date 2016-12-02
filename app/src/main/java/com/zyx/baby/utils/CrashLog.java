package com.zyx.baby.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

import java.io.*;
import java.lang.reflect.Field;


public class CrashLog implements Thread.UncaughtExceptionHandler {

    private static final String TAG = "CrashLog";
    private static final  String FOLDER = "/AppBaby";

    private static CrashLog _Instance;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private Context mContext;

    private String mDeviceID = "log";

    public static CrashLog getInstance() {

        if (_Instance == null) {
            _Instance = new CrashLog();
        }

        return _Instance;
    }

    public void initCrashHandler(Context context) {
        this.mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 捕获crash的回调函数
     * <p>
     * 因为是生成固定格式报告发送回服务器，硬代码会被嵌入到代码中。
     */
    @SuppressWarnings("static-access")
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        boolean handleException = handleException(ex);
        if (!handleException && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO: handle exception
            }
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义的处理Exception函数
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        new Thread() {

            @Override
            public void run() {
                // Looper.prepare();
                // Toast.makeText(mContext,
                // mContext.getString(R.string.crash_handler_toast_info),
                // Toast.LENGTH_LONG).show();
                // Looper.loop();
            }
        }.start();
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * 保存错误信息到指定文件夹
     */
    private void saveCrashInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        sb.append(getDeviceInfo() + "\n");
        Log.e(
                TAG,
                ex.getLocalizedMessage() == null ? "" : ex
                        .getLocalizedMessage());
        Writer info = new StringWriter();
        PrintWriter mPW = new PrintWriter(info);
        ex.printStackTrace(mPW);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(mPW);
            cause = cause.getCause();
        }
        Log.e(TAG, info.toString());
        mPW.close();
        sb.append(info.toString() + "\n");
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                String tmp;
                field.setAccessible(true);
                tmp = field.getName() + ":" + field.get(null).toString() + "\n";
                Log.e(TAG, tmp);
                sb.append(tmp);
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info:" + e.toString());
            }
        }

        try {
            String fileName = "crash_" + mDeviceID + "_" + DateFormat.format("yyyy_MM_dd_hh_mm_ss", System.currentTimeMillis()) + ".log";
            String path = Environment.getExternalStorageDirectory() + FOLDER;
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + "/"
                        + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            } else {

            }
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file:" + e.toString());
        }
    }

    /**
     * 获取设备信息
     */
    private String getDeviceInfo() {
        String info = "";
        info += "crash time:"
                + DateFormat.format("yyyy-MM-dd hh:mm:ss",
                System.currentTimeMillis());
        info += "\n";

        Log.e(TAG, info);
        return info;
    }
}
