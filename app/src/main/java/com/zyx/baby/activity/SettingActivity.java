package com.zyx.baby.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivityNew;
import com.zyx.baby.databinding.ActivitySettingsBinding;
import com.zyx.baby.utils.ConfigUtils;
import com.zyx.baby.utils.DataCleanManager;
import com.zyx.baby.widget.CustomDialog;
import com.zyx.baby.widget.MyTitleBar;


/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SettingActivity extends BaseActivityNew<ActivitySettingsBinding> implements View.OnClickListener {

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("设置");
        showContentView();
        initData();


    }

    private void initData() {
        boolean isShow = ConfigUtils.getBoolean(getApplicationContext(), "introduce",true);
        if(isShow){
            bindingView.ivToggle.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_off));
        }else {
            bindingView.ivToggle.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_off));
        }
        bindingView.ivToggle.setTag(isShow);
        getCacheSize();
        bindingView.reLink.setOnClickListener(this);
        bindingView.rlCallMody.setOnClickListener(this);
        bindingView.rlAbout.setOnClickListener(this);
        bindingView.rlFankui.setOnClickListener(this);
        bindingView.ivToggle.setOnClickListener(this);
        bindingView.llClear.setOnClickListener(this);
    }

    /**
     * 得到缓存的大小
     */
    private void getCacheSize() {
        try {
            String cacheSize = DataCleanManager.getTotalCacheSize(this);
            bindingView.tvCache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_toggle:
                if (bindingView.ivToggle.getTag().equals(true)) {
                    bindingView.ivToggle.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_off));
                    ConfigUtils.putBoolean(getApplicationContext(), "introduce", false);
                    bindingView.ivToggle.setTag(false);//记录修改状态
                } else {
                    bindingView.ivToggle.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon_on));
                    ConfigUtils.putBoolean(getApplicationContext(), "introduce", true);
                    bindingView.ivToggle.setTag(true);//记录修改状态
                }
                break;
            case R.id.re_account:
                Intent intent = new Intent(SettingActivity.this, ModifyLoginPasswordActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.ll_clear:
                clearCache();
                break;
            case R.id.re_link:
                Intent intent1 = new Intent(SettingActivity.this, BluetoothChat.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
        }
    }

    private void clearCache() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("您确定要清除所有缓存吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                DataCleanManager.clearAllCache(SettingActivity.this);
                getCacheSize();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();

    }
}

