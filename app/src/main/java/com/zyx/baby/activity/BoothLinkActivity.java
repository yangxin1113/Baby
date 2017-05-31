package com.zyx.baby.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivityNew;
import com.zyx.baby.bean.LoginBean;
import com.zyx.baby.databinding.ActivityLinkBinding;
import com.zyx.baby.event.BoothEvent;
import com.zyx.baby.event.LoginEvent;
import com.zyx.baby.service.NotificationService;
import com.zyx.baby.service.booth.BluetoothChatService;
import com.zyx.baby.utils.AppUtils;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.utils.UserInfoUtils;
import com.zyx.baby.widget.CustomRotateAnim;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhengYangxin
 * @time 2017/4/15
 */

public class BoothLinkActivity extends BaseActivityNew<ActivityLinkBinding> {

    private static String TAG = "BoothLinkActivity";
    private BluetoothAdapter mBluetoothAdapter = null;

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    List<String> devices = new ArrayList<>();

    private BluetoothChatService mChatService = null;

    private NotificationService.BlueBinder blueBinder;

    private CustomRotateAnim rotateAnim;

    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, BoothLinkActivity .class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);
        EventBus.getDefault().register(this);
        setTitle("链接设备");
        showContentView();
        initView();
        initData();
    }


    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            blueBinder = (NotificationService.BlueBinder) service;
            blueBinder.startConnect();
        }
    };


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void blueEventBus(BoothEvent event) {
        if (TextUtils.equals(event.getTag(),"fail")){
            bindingView.ivLink.clearAnimation();
            bindingView.tvResult.setText(R.string.txt_scan_fail);

        }
        if (TextUtils.equals(event.getTag(),"success")){
            bindingView.ivLink.clearAnimation();
            bindingView.tvResult.setText(R.string.txt_scan_success);

        }
    }

    private void initData() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        /*// Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(mReceiver, filter);

        // Register for broadcasts when discovery has finished
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);*/

    }

    private void initView() {
        bindingView.ivLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnimation();
                //doDiscovery();
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                        PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(BoothLinkActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }else {
                    scanDevice();
                }



            }
        });

    }

    private void scanDevice() {
        Intent serverIntent = new Intent(BoothLinkActivity.this, DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE);
        bindingView.tvResult.setText(R.string.txt_scaning);
    }

    /**
     * 设置动画
     */
    private void showAnimation() {
        // 获取自定义动画实例
        rotateAnim = CustomRotateAnim.getCustomRotateAnim();
        // 一次动画执行1秒
        rotateAnim.setDuration(300);
        // 设置为循环播放
        rotateAnim.setRepeatCount(-1);
        // 设置为匀速
        rotateAnim.setInterpolator(new LinearInterpolator());
        // 开始播放动画
        bindingView.ivLink.startAnimation(rotateAnim);
    }

    /**
     * 设备可被发现
     */
    private void ensureDiscoverable() {
        if (mBluetoothAdapter.getScanMode() !=
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(discoverableIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }else {
            if(!AppUtils.isServiceExisted(getApplicationContext(),"NotificationService")){
                ensureDiscoverable();
            }

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mBluetoothAdapter.isEnabled()){
            //启动后台服务
            Intent intent = new Intent(BoothLinkActivity.this, NotificationService.class);
            startService(intent);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Make sure we're not doing discovery anymore
        /*if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }*/

        if (alertDialog != null){
            alertDialog = null;
        }

        if (rotateAnim != null){
            rotateAnim = null;
        }
        // Unregister broadcast listeners
//        this.unregisterReceiver(mReceiver);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 300){
            Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
        }else if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
        }else {
            switch (requestCode){
                case REQUEST_ENABLE_BT:
                    if (resultCode == Activity.RESULT_OK) {
                        // Bluetooth is now enabled, so set up a chat session
                    } else {
                        // User did not enable Bluetooth or an error occured
                                          Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;

                case REQUEST_CONNECT_DEVICE:
                    // When DeviceListActivity returns with a device to connect
                    if (resultCode == Activity.RESULT_OK) {
                        // Get the device MAC address
                        String address = data.getExtras()
                                .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                        //启动绑定，数据传输开始
                        Intent bindIntent = new Intent(BoothLinkActivity.this, NotificationService.class);
                        bindIntent.putExtra("address", address);
                        bindService(bindIntent, connection, BIND_AUTO_CREATE);
                    }else {
                    }
                    break;
                case RESULT_CANCELED:
                    // When DeviceListActivity returns with a device to connect
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                case 300:
                    // When DeviceListActivity returns with a device to connect
                    Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    scanDevice();
                }else {
                    Toast.makeText(getApplicationContext(), "请同意获取存储权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    /***
     * 搜索设备
     */
    private void doDiscovery() {

        // If we're already discovering, stop it
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }

        // Request discover from BluetoothAdapter
        mBluetoothAdapter.startDiscovery();
        bindingView.tvResult.setText(R.string.txt_scaning);
    }


    // The BroadcastReceiver that listens for discovered devices and
    // changes the title when discovery is finished



    // 信息列表提示框
    private AlertDialog alertDialog;
    public void showListAlertDialog(){
        final String[] items = (String[])devices.toArray(new String[devices.size()]);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("搜索结果列表");
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int index) {
                Toast.makeText(BoothLinkActivity.this, items[index], Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
                mBluetoothAdapter.cancelDiscovery();
                String address = items[index].substring(items[index].length() - 17);
//                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                //启动绑定，数据传输开始
                Intent bindIntent = new Intent(BoothLinkActivity.this, NotificationService.class);
                bindIntent.putExtra("address", address);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                devices.clear();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }
}
