package com.zyx.baby.presenters.viewinface;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface RegisterView {
    void registerSucc();  //注册
    void registerFail(String msg);
    void sendCodeSucc(); //判断是否能发送验证码  是否注册过
    void sendCodeFail(String msg);
    void getCodeSucc(); //获取验证码
    void getCodeFail(String msg);
    void forgetPwdSucc(); //忘记密码
    void forgetPwdFail(String msg);
}
