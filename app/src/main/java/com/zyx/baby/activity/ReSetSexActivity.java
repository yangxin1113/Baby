package com.zyx.baby.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改性别
 * Created by zys on 2016/11/26.
 */

public class ReSetSexActivity extends BaseActivity {
    private static final String TAG = "ReSetSexActivity";

    private Intent intent;
    private String sex;
    @BindView(R.id.iv_man)
    ImageView iv_man;
    @BindView(R.id.iv_woman)
    ImageView iv_woman;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_resetsex);
    }

    @Override
    protected void setInitData() {
        intent = getIntent();
        sex = intent.getStringExtra("sex");
        if (TextUtils.isEmpty(sex) || sex.equals("1")) {
            iv_woman.setVisibility(ImageView.GONE);
        } else {
            iv_man.setVisibility(ImageView.GONE);
        }
    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.iv_left)
    public void onBack() {
        finish();
    }

    @OnClick(R.id.tv_man)
    public void onMan() {
        iv_man.setVisibility(ImageView.VISIBLE);
        iv_woman.setVisibility(ImageView.GONE);
        finish();

    }

    @OnClick(R.id.tv_woman)
    public void onWoman() {
        iv_woman.setVisibility(ImageView.VISIBLE);
        iv_man.setVisibility(ImageView.GONE);
        finish();
        /*HttpMethod.modifyInfomation("sex", "0", new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, "onSuccess: 修改性别" + s);
                finish();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                Log.e(TAG, "onError: 修改性别" + e);
            }
        });*/
    }
}
