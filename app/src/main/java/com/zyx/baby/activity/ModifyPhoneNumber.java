package com.zyx.baby.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.utils.CountDownTimerUtils;
import com.zyx.baby.utils.LSUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 已有手机号  修改手机号
 * Created by zys on 2016/11/27.
 */

public class ModifyPhoneNumber extends BaseActivity {

    @BindView(R.id.bt_send_key)
    Button btSendKey;
    private Intent intent;
    private String phone;
    private String msgone;

    @BindView(R.id.et_register_phone)
    EditText et_register_phone;
    @BindView(R.id.tv_phonenumber)
    TextView tv_phonenumber;
    @BindView(R.id.bt_restPwd)
    Button bt_restPwd;


    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.bt_send_key)
    public void sendKey() {
        toSendKey();
    }

    //发送验证码
    public void toSendKey() {
        CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btSendKey, 60000, 1000);
        mCountDownTimerUtils.start();
       /* HttpMethod.sendSmsCode("3", phone, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e("dfasdfasdf", s);
                Gson gson = new Gson();
               *//* Type userType = new TypeToken<Bean>(){}.getType();
                Bean bean = gson.fromJson(s,userType);*//*
                ModifuPhoneNumberBean bean = gson.fromJson(s, ModifuPhoneNumberBean.class);
                msgone = bean.data.msg;
             *//*   ModifyPhoneSmsEvent event = new ModifyPhoneSmsEvent();
                event.setObj(bean);
                EventBus.getDefault().post(event);*//*
            }
        });*/
    }

    //验证码验证
    @OnClick(R.id.bt_restPwd)
    public void next() {
        String msg = et_register_phone.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            LSUtils.showToast(ModifyPhoneNumber.this, "验证码不能为空!");
        } else {
            if (TextUtils.equals(msg, msgone)) {
                startActivity(new Intent(ModifyPhoneNumber.this, ReSetPhoneActivity.class));
            }
        }
    }

    @OnClick(R.id.iv_back)
    public void onBack () {
        finish();
    }

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void init (Bundle arg0){
        setContentView(R.layout.activity_modifyphonebefore);
    }

    @Override
    protected void setInitData () {
        intent = getIntent();
        phone = intent.getStringExtra("phone");
        tv_phonenumber.setText(phone);
    }
}
