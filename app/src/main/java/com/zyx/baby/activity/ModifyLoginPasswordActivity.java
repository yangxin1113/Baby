package com.zyx.baby.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.zyx.baby.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyLoginPasswordActivity extends AppCompatActivity {
    private static final String TAG = "ModifyLoginActivity";
    private Context mContext = ModifyLoginPasswordActivity.this;

    @BindView(R.id.ed_oldPwd)
    EditText edOldPwd;
    @BindView(R.id.ed_newPwd1)
    EditText edNewPwd1;
    @BindView(R.id.ed_newPwd2)
    EditText edNewPwd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_login_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back://返回
                back();
                break;
            case R.id.tv_confirm://确定
                modifyPassword();
                break;
        }
    }

    /**
     * 返回
     */
    private void back() {
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    /**
     * 修改登录密码
     */
    private void modifyPassword() {
        String oldpassword = edOldPwd.getText().toString().trim();
        String newpassword = edNewPwd1.getText().toString().trim();
        String confirmpassword = edNewPwd2.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("oldpassword",oldpassword);
        params.put("newpassword",newpassword);
        params.put("confirmpassword",confirmpassword);
       /* Http.post(getApplication(), HttpApi.CHANGE_PASSWORD_KEY, params, this, new Http.CallBack() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Log.e(TAG, "onSuccess: 修改登录密码"+s );
                try {
                    JSONObject json = new JSONObject(s);
                    LSUtils.showToast(mContext,json.getString("alert"));
                    if("0".equals(json.getString("status"))){
                        back();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                Log.e(TAG, "onError: 修改登录密码"+e );

            }
        });*/

    }
}
