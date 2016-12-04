package com.zyx.baby.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.utils.LSUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 修改 昵称
 * Created by zys 2016/11/26.
 */

public class ReSetNickActivity extends BaseActivity {


    @BindView(R.id.tv_nick)
    EditText nick;


    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_resernick);

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
        final String nickcontent=nick.getText().toString();
        if(TextUtils.isEmpty(nickcontent)){
            LSUtils.showToast(ReSetNickActivity.this,"昵称不能为空!");
        }else if(nickcontent.length() < 2 || nickcontent.length() > 10 ){
            LSUtils.showToast(ReSetNickActivity.this,"昵称长度不合法！");
        }
        else {
           /* HttpMethod.modifyInfomation("nickname", nickcontent, new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    ToastUtils.showMessage(ReSetNickActivity.this,"修改成功");
                    UserInfoUtils.putString(getContext(), "name",nickcontent);
                    finish();
                }
            });*/
        }


    }
}
