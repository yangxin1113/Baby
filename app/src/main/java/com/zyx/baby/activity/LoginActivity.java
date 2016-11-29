package com.zyx.baby.activity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.utils.LSUtils;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class LoginActivity extends BaseFragmentActivity {


    @BindView(R.id.ed_account)
    EditText edAccount;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.tv_lost_pwd)
    TextView tvLostPwd;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.ll_qq)
    LinearLayout llQq;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.ll_weibo)
    LinearLayout llWeibo;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    protected void setInitData() {
        //StatusBarUtil.setStatusBarColor(LoginActivity.this, R.color.white);//设置状态栏颜色
    }

    @Override
    protected void initEvent() {
        btLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                if(isNext()){
                    showItemActivity(IndexActivity.class);

                }
                break;

        }
    }

    private boolean isNext() {
        boolean isNext = true;
        if(TextUtils.isEmpty(edAccount.getText()) || TextUtils.isEmpty(edPwd.getText())){
            LSUtils.showToast(getApplicationContext(), "请输入账号或密码");
            isNext = false;
        }else if(!edAccount.getText().toString().trim().equals("13411111111") || !edPwd.getText().toString().trim().equals("123456")){
            LSUtils.showToast(getApplicationContext(), "账号或密码错误");
            isNext = false;
        }
        return isNext;
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


    /**
     * 输入框为空时,登录按钮不可点击
     */
    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            ColorStateList csl = null;
            if (TextUtils.isEmpty(s.toString())) {
                btLogin.setBackgroundResource(R.color.unable_press_bg);
                btLogin.setTextColor(getResources().getColor(R.color.unable_press_text));
            } else {
                btLogin.setBackgroundResource(R.drawable.bt_single);
                btLogin.setTextColor(getResources().getColor(R.color.white));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {


        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

    };

}
