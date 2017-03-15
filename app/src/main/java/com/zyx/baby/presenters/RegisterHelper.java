package com.zyx.baby.presenters;

import android.content.Context;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.presenters.viewinface.RegisterView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/22.
 */

public class RegisterHelper {
    private static final String TAG = "RegisterHelper" ;
    private Context mContext;
    private RegisterView mRegisterView;
    public RegisterHelper(Context context, RegisterView registerView) {
        mContext = context;
        mRegisterView = registerView;
    }
    public void register(final HashMap<String,String> params)
    {
        OkGo.post(ApisUtil.URL_CACHE).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG,s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.optBoolean("status")) {
                       mRegisterView.registerSucc();
                    }else{
                        mRegisterView.registerFail(jsonObject.optString("alert"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    mRegisterView.registerFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mRegisterView.registerFail("服务器网络开小差，请稍等。。。");
            }
        });
    }
    public void sendCode(String phone,final int type)//type=1代表注册 type=2代表忘记密码
    {
        Map<String,String> params = new HashMap<>();
        params.put("phone",phone);
        OkGo.post(ApisUtil.URL_CACHE).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG,s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if(type==1) {
                        if (!jsonObject.optBoolean("status")) {
                            mRegisterView.sendCodeSucc();
                        } else {
                            mRegisterView.sendCodeFail(jsonObject.optString("alert"));
                        }
                    }else
                    {
                        if (jsonObject.optBoolean("status")) {
                            mRegisterView.sendCodeSucc();
                        } else {
                            mRegisterView.sendCodeFail(jsonObject.optString("alert"));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    mRegisterView.sendCodeFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mRegisterView.sendCodeFail("服务器网络开小差，请稍等。。。");
            }
        });
    }
    public void getCode(final HashMap<String,String> params)
    {
        OkGo.post(ApisUtil.URL_CACHE).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG,s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.optInt("status")==0) {
                        mRegisterView.getCodeSucc();
                    }else{
                        mRegisterView.getCodeFail(jsonObject.optString("alert"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    mRegisterView.getCodeFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mRegisterView.getCodeFail("服务器网络开小差，请稍等。。。");
            }
        });
    }
    public void forgetPwd(final HashMap<String,String> params)
    {
        OkGo.post(ApisUtil.URL_CACHE).tag(mContext).params(params).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG,s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.optInt("status")==0) {
                        mRegisterView.forgetPwdSucc();
                    }else{
                        mRegisterView.forgetPwdFail(jsonObject.optString("alert"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    mRegisterView.forgetPwdFail("服务器网络开小差，请稍等。。。");
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
                mRegisterView.forgetPwdFail("服务器网络开小差，请稍等。。。");
            }
        });
    }
}
