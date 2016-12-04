package com.zyx.baby.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.utils.DataCleanManager;
import com.zyx.baby.widget.CustomDialog;
import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    @BindView(R.id.iv_toggle)
    ImageView iv_toggle;
    @BindView(R.id.re_link)
    RelativeLayout re_link;
    @BindView(R.id.tv_cache)
    TextView tv_cache;

    private Dialog dialog;

    /**
     * 初始化布局
     *
     * @param arg0
     */
    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_settings);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("设置");
        myTitleBar.setLeftImage(R.drawable.icon_back);
        myTitleBar.setLeftImageOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
            }
        });
        getCacheSize();

    }

    @OnClick({R.id.re_account, R.id.re_link, R.id.iv_toggle, R.id.ll_clear, R.id.rl_fankui, R.id.rl_about, R.id.rl_call_mody})
    void onClick(View view){
        switch (view.getId()){
            case R.id.iv_toggle:
                if(iv_toggle.getTag().equals(true)){
                    iv_toggle.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_off));
                    iv_toggle.setTag(false);//记录修改状态
                }else{
                    iv_toggle.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.icon_on));
                    iv_toggle.setTag(true);//记录修改状态
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
        }
    }


    /**
     * 得到缓存的大小
     */
    private void getCacheSize() {
        try {
            String cacheSize = DataCleanManager.getTotalCacheSize(this);
            tv_cache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 清除缓存
     */
    private void clearCache() {
        CustomDialog.Builder builder = new CustomDialog.Builder(mContext);
//        builder.setTitle("提醒");
        builder.setMessage("您确定要清除所有缓存吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                DataCleanManager.clearAllCache(mContext);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}