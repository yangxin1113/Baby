package com.zyx.baby.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.event.LoginEvent;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.utils.UserInfoUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/23.
 */

public class RegisterActivity extends BaseActivity {


    private static final String TAG = "RegisterActivity";
    private Context mContext = RegisterActivity.this;

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.et_register_password2)
    EditText etRegisterPassword2;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.bt_send_key)
    Button btSendKey;
    @BindView(R.id.et_register_key)
    EditText etRegisterKey;
    @BindView(R.id.et_register_username)
    EditText etRegisterUsername;
    @BindView(R.id.iv_check)
    ImageView ivCheck;
    private String phone;
    private String username;
    private String password1;
    private String password2;
    private boolean isExit;
    private String currentKey = "";
    private Button btRegister;
    private static final int USER_LOGIN = 0;


    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void setInitData() {

    }

    //获取验证码
    @OnClick(R.id.bt_send_key) void getCode(){
        sendKey();
    }


    /**
     * 请求网络进行注册
     */
    private void requestForRegister() {
        //String targetUrl = ConfigUtils.url(getContext(), "UserSignup");
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", password1);
        params.put("again", password2);
        params.put("code", currentKey);
        params.put("from", "Android");
        params.put("phone", phone);
        Log.e(TAG, "requestForRegister: phone:" + phone);

      /*  OkHttpUtils.post(targetUrl)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 用户注册" + s);
                        try {
                            Gson gson = new Gson();
                            UserRegisterBean userRegisterBean = gson.fromJson(s, UserRegisterBean.class);
                            LSUtils.showToast(RegisterActivity.this,userRegisterBean.alert);
                            if(userRegisterBean.status){
                                //注册成功，登录
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("username", phone);
                                params.put("password", password1);
                                params.put("from", "Android");
                                HttpMethod.login(params, "register");
                                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e(TAG, "onSuccess: 用户注册" + e);
                    }
                });*/

    }

    public void onEventMainThread(LoginEvent loginEvent){
        if(!loginEvent.getTag().equals("register")){
            return;
        }
       /* Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        try {
            JSONObject json = new JSONObject((String)loginEvent.getObj());
            if (json.getBoolean("status") == true) {
                //LSUtils.showToast(getApplicationContext(), json.getString("alert"));
                setUserInfo(json.getJSONObject("data").getJSONObject("userInfo"));
                startActivity(intent);

            } else {
                LSUtils.showToast(getApplicationContext(), json.getString("alert"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }


    /**
     * 将用户信息到本地
     *
     * @param data
     */
    private void setUserInfo(JSONObject data) {

        try {

            UserInfoUtils.putString(getApplicationContext(), "id", data.optString("id"));
            UserInfoUtils.putString(getApplicationContext(), "name", data.optString("name"));
            UserInfoUtils.putString(getApplicationContext(), "sex", data.optString("sex"));
            UserInfoUtils.putString(getApplicationContext(), "isanchor", data.optString("isanchor"));
            UserInfoUtils.putString(getApplicationContext(), "signature", data.optString("signature"));
            UserInfoUtils.putString(getApplicationContext(), "avator", data.optString("avator"));
            UserInfoUtils.putString(getApplicationContext(), "is_realname", data.optString("is_realname"));
            UserInfoUtils.putString(getApplicationContext(), "is_pay_pass", data.optString("is_pay_pass"));
            UserInfoUtils.putString(getApplicationContext(), "phone", data.optString("phone"));
            UserInfoUtils.putString(getApplicationContext(), "is_order_take", data.optString("is_order_take"));//1为接单，2为不接单
            UserInfoUtils.putString(getApplicationContext(), "is_contact", "2");//1为已经联系过客服，2为未联系过
            if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(password1)){

            }else {
                UserInfoUtils.putString(getApplicationContext(), "username",  phone);
                UserInfoUtils.putString(getApplicationContext(), "password", password1);
            }


            // 附加数据
            HashMap<String,String> extraData = new HashMap<String, String>();
            extraData.put("id", data.optString("id"));
            extraData.put("name", data.optString("name"));
            extraData.put("sex", data.optString("sex"));
            extraData.put("isanchor", data.optString("isanchor"));
            extraData.put("is_realname", data.optString("is_realname"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void initEvent() {
        btRegister = (Button) findViewById(R.id.bt_registers);
        btRegister.setClickable(false);
        ivCheck.setTag(true);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: 被点击了");
                if(ivCheck.getTag().equals(false)){
                    //MyToast.create(mContext,"请同意闪电鱼用户协议");
                    return;
                }
                register();
            }
        });
    }


    /**
     * 返回
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.iv_back, R.id.bt_send_key, R.id.iv_check})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
                break;
            case R.id.bt_send_key://发送验证码
                sendKey();
                break;
            case R.id.iv_check://同意协议
                aggrement();
                break;
        }
    }

    /**
     * 同意协议
     */
    private void aggrement() {
        if (ivCheck.getTag().equals(false)) {
            ivCheck.setImageResource(R.drawable.icon_tongyi_selected);
            //改变button的状态
            //改变button的状态（可用）
            btRegister.setBackgroundResource(R.drawable.bg_enable);
            btRegister.setTextColor(Color.WHITE);
            btRegister.setClickable(true);
            ivCheck.setTag(true);
        } else {
            ivCheck.setImageResource(R.drawable.icon_tongyi_normal);
            //改变button的状态
            btRegister.setBackgroundResource(R.drawable.bg_unable);
            btRegister.setTextColor(Color.WHITE);
            btRegister.setClickable(false);
            ivCheck.setTag(false);
        }
    }

    /**
     * 发送验证码
     */
    private void sendKey() {
        username = etRegisterUsername.getText().toString();
        password1 = etRegisterPassword.getText().toString();
        password2 = etRegisterPassword2.getText().toString();
        phone = etRegisterPhone.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2) ||
                TextUtils.isEmpty(phone) ) {
            LSUtils.showToast(this, "请将信息填写完整！");
            return;
        } else {
            if (username.length() <2 || username.length() > 10) {
                LSUtils.showToast(this, "请输入2到10位字符！");
                return;
            }
            if (!TextUtils.equals(password1, password2)) {
                LSUtils.showToast(this, "密码不一致！");
                return;
            }
            if (password1.length() < 6) {
                LSUtils.showToast(getApplicationContext(), "密码应不小于6位！");
                return;
            }

        }
        isUserExit();
    }

    /**
     * 注册
     */
    private void register() {
        username = etRegisterUsername.getText().toString().trim();
        password1 = etRegisterPassword.getText().toString().trim();
        password2 = etRegisterPassword2.getText().toString().trim();
        phone = etRegisterPhone.getText().toString().trim();
        currentKey = etRegisterKey.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2) ||
                TextUtils.isEmpty(phone) || TextUtils.isEmpty(currentKey)) {
            LSUtils.showToast(this, "请将信息填写完整！");
            return;
        } else {
            if (username.length() < 5 || username.length() > 30) {
                LSUtils.showToast(this, "用户名非法！");
                return;
            }
            if (!TextUtils.equals(password1, password2)) {
                LSUtils.showToast(this, "密码不一致！");
                return;
            }
            if (password1.length()<6) {
                LSUtils.showToast(getApplicationContext(), "密码应不小于6位！");
                return;
            }
            requestForRegister();
        }


    }


    /**
     * 判断用户是否存在 true为用户存在，false为用户不存在
     *
     * @return
     */
    private void isUserExit() {





       /* final String LOGIN_URL = HttpApi.getUrl(HttpApi.VERIFY_PHONE_KEY);
        OkHttpUtils.post(LOGIN_URL)
                .params("phone", phone)
                .execute(new StringDialogCallback(RegisterActivity.this) {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.e(TAG, "onSuccess: 判断用户注册" + s);
                        try {
                            JSONObject json = new JSONObject(s);
                            if (json.getBoolean("status") == true) {
                                //用户存在
                                LSUtils.showToast(getApplicationContext(), json.getString("alert"));
                                return;
                            } else {
                                //判断验证码
                                CountDownTimerUtils mCountDownTimerUtils = new CountDownTimerUtils(btSendKey, 60000, 1000);
                                mCountDownTimerUtils.start();
                                //用户不存在
                                requestKey();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });*/

    }

    /**
     * 向服务器请求验证码
     */
    private void requestKey() {
        /*Map<String, String> params = new HashMap<>();
        params.put("type", "2");
        params.put("phone", phone);
        Http.post(getApplication(), HttpApi.SEND_SMS_KEY, params, this, new Http.CallBack() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, "onSuccess: 服务器验证码:" + s);
//                Gson gson = new Gson();
//                SmsBean smsBean = gson.fromJson(s, SmsBean.class);
//                if (TextUtils.equals(smsBean.status, "0")) {
//                    LSUtils.showToast(RegisterActivity.this, smsBean.alert);
//                    serverKey = smsBean.data.msg;
//                    //填写验证码
////                    etRegisterKey.setText(serverKey);
//                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                Log.e(TAG, "onError: 服务器验证码：" + e);
            }
        });*/


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }





}
