package com.zyx.baby.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.utils.LSUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改手机号
 * Created by zys on 2016/11/26.
 */

public class ReSetPhoneActivity extends BaseActivity {

    @BindView(R.id.tv_phongnumber)
    EditText phonenumber;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_resetphone);
    }

    @Override
    protected void setInitData() {

    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.iv_left)
    public void onBack(){
        finish();
    }

    @OnClick(R.id.tv_commint)
    public void onCommint(){
        String phone =phonenumber.getText().toString();
        if(TextUtils.isEmpty(phone)){
            LSUtils.showToast(ReSetPhoneActivity.this,"手机号不能为空!");
        }else if(!isPhoneNumberValid(phone)) {
            LSUtils.showToast(ReSetPhoneActivity.this,"请输入正确的电话号码!");
        }
        else {
            /*HttpMethod.modifyInfomation("phone", phone, new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    Log.e("sdafasdf",s);
                    ToastUtils.showMessage(ReSetPhoneActivity.this,"修改成功");
                    finish();
                }
            });*/
        }
    }

    /**手机号判断*/
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        String expression = "((^(13|15|18|17|19)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

}
