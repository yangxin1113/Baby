package com.zyx.baby.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivityNew;
import com.zyx.baby.databinding.ActivitySettingsBinding;
import com.zyx.baby.databinding.ActivityToolBinding;
import com.zyx.baby.utils.ConfigUtils;
import com.zyx.baby.utils.DataCleanManager;
import com.zyx.baby.widget.CustomDialog;


/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class ToolActivity extends BaseActivityNew<ActivityToolBinding> implements View.OnClickListener {

    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, ToolActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);
        setTitle("工具箱");
        showContentView();
        initData();


    }

    private void initData() {
        bindingView.rlStore.setOnClickListener(this);
        bindingView.rlHos.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_store:

                break;
            case R.id.rl_hos:

                break;
        }
    }

}

