package com.zyx.baby.utils;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;

import java.io.File;
import java.security.MessageDigest;

/**
 * Created by Administrator on 2016/8/8 0008.
 */
public class MyUtils {

    public static MyUtils utils;

    private MyUtils() {

    }

    public static MyUtils getInstance() {
        if (utils == null)
            utils = new MyUtils();
        return utils;
    }

    /**
     * 判断网络是否连接，返回false为没有任何连接
     *
     * @param context
     *            上下文
     * @return boolean
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager
                    .getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public String getIP(Context context) {
        // 获取wifi服务
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        // 判断wifi是否开启
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        String ip = intToIp(ipAddress);
        return ip;
    }

    private String intToIp(int i) {

        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }

    /**
     * make true current connect service is wifi
     *
     * @param mContext
     * @return
     */
    public static boolean isWifiNetwork(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    private boolean isGpsEnable(Context mContext) {
        LocationManager locationManager = ((LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE));
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * MD5加密，32位
     *
     * @param url
     *            需加密的字符串
     * @return 加密后的字符串
     */
    public String MD5(String url) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            // e.printStackTrace();
            return getFile(url);
        }
        char[] charArray = url.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 可逆的加密算法
     */
    public String encryptmd5(String str) {
        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 'l');
        }

        return new String(a);
    }

    /**
     * 将URL转成能够识别的目录
     */
    public String getFile(String url) {
        String path = url;
        if (path.contains("?")) {
            path = path.replace("?", "_");
        }
        if (path.contains("/")) {
            path = path.replace("/", "_");
        }
        if (path.contains(".")) {
            path = path.replace(".", "_");
        }
        return path;
    }

    /**
     * 获取缓存目录中的自定义文件路径
     *
     * @param context
     *            上下文
     * @param path
     *            二级目录，三级目录中间用"/"分隔，前后不需要加"/"
     * @param fileName
     *            文件名，自动转成md5的名字
     * @param isMD5
     *            文件名是否加密
     * @return File
     */
    public File getCache(Context context, String path, String fileName,
                         boolean isMD5) {
        File file = null;
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            file = new File(context.getExternalCacheDir().getPath() + "/"
                    + path);
        } else {
            file = new File(context.getCacheDir() + "/" + path);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (isMD5)
            file = new File(file.getPath() + "/" + MD5(fileName));
        else
            file = new File(file.getPath() + "/" + fileName);
        return file;
    }

}
