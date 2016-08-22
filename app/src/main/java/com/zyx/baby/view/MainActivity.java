package com.zyx.baby.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.R;
import com.zyx.baby.http.OKManager;
import com.zyx.baby.utils.PreferencesUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.bt_click1) Button bt_click1;

    private OKManager manager;
    private String json_path = "http://api2.hichao.com/stars?category=%E5%85%A8%E9%83%A8&pin=&ga=%2Fstars&flag=&gv=63&access_token=&gi=862949022047018&gos=5.2.3&p=2013022&gc=xiaomi&gn=mxyc_adr&gs=720x1280&gf=android&page=2";

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_http);
        manager = OKManager.getInstance();
    }

    @Override
    protected void setInitData() {

    }

    @Override
    protected void initEvent() {
        bt_click1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_click1:
/*                manager.asyncJsonStringByURL(json_path, new OKManager.Func1() {
                    @Override
                    public void onResponse(String result) {
                        Log.i("ZZZ", result);//获取json字符串
                    }
                });
                //String appName = AppUtils.getVersionName(Main.this);
                //Toast.makeText(Main.this,appName+"zzz",Toast.LENGTH_LONG).show();

/*
                if(NetUtils.isConnected(Main.this)){
                    Toast.makeText(Main.this,"有网zzz",Toast.LENGTH_LONG).show();
                    if(NetUtils.isWifi(Main.this)){
                        Toast.makeText(Main.this,"wifi链接"+"zzz",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Main.this,"数据链接"+"zzz",Toast.LENGTH_LONG).show();

                    }

                }else {
                    Toast.makeText(Main.this,"无网络链接"+"zzz",Toast.LENGTH_LONG).show();
                    NetUtils.openSetting(Main.this);

                }
*/

                //PreferencesUtils.putString(getApplicationContext(),"name","zyx111");
                Toast.makeText(MainActivity.this,PreferencesUtils.getString(MainActivity.this,"name"),Toast.LENGTH_LONG).show();

                break;
        }
    }
}
