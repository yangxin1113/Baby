package com.zyx.baby.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

import java.util.Set;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class BluetoothUtils {

    private final int REQUEST_ENABLE = 1;
    private Context mContext;
    public BluetoothAdapter mBluetoothAdapter = null;

    /**
     * 初始化一个蓝牙适配器
     * @param context
     */
    public BluetoothUtils(Context context){
        mContext = context;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 返回已配对的蓝牙设备
     * @return
     */
    public Set<BluetoothDevice> getPairedDevices(){
        return mBluetoothAdapter.getBondedDevices();
    }

    /**
     * 开始发现蓝牙设备
     * @return
     */
    public  Boolean startDiscoverBluetooth(){
        return mBluetoothAdapter.startDiscovery();
    }

    /**
     * 停止蓝牙搜索
     */
    public void endDiscoverBluetooth(){
        mBluetoothAdapter.cancelDiscovery();
    }
}
