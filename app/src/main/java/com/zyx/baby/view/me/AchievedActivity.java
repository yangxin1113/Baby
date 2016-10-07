package com.zyx.baby.view.me;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.widget.CircleImageView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;



/**
 * 我的成就
 * Created by Administrator on 2016/7/28 0028.
 */
public class AchievedActivity extends BaseFragmentActivity {

    @BindView(R.id.iv_left)
    ImageView iv_left;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_top)
    RelativeLayout ivTop;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_friends)
    TextView tvFriends;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.tv_zantong)
    TextView tvZantong;
    @BindView(R.id.ll_zantong)
    LinearLayout llZantong;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.ll_shoucang)
    LinearLayout llShoucang;
    @BindView(R.id.tv_fenxaing)
    TextView tvFenxaing;
    @BindView(R.id.ll_fenxiang)
    LinearLayout llFenxiang;
    @BindView(R.id.ll_lan)
    LinearLayout llLan;
    @BindView(R.id.tv_dongtai)
    TextView tvDongtai;
    @BindView(R.id.tv_dongtaicount)
    TextView tvDongtaicount;
    @BindView(R.id.iv_dongtai_jiantou)
    ImageView ivDongtaiJiantou;
    @BindView(R.id.ll_dongtai)
    RelativeLayout llDongtai;
    @BindView(R.id.tv_huida)
    TextView tvHuida;
    @BindView(R.id.tv_huidacount)
    TextView tvHuidacount;
    @BindView(R.id.iv_huida_jiantou)
    ImageView ivHuidaJiantou;
    @BindView(R.id.ll_huida)
    RelativeLayout llHuida;
    @BindView(R.id.tv_tiwen)
    TextView tvTiwen;
    @BindView(R.id.tv_tiwencount)
    TextView tvTiwencount;
    @BindView(R.id.iv_tiwen_jiantou)
    ImageView ivTiwenJiantou;
    @BindView(R.id.ll_tiwen)
    RelativeLayout llTiwen;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;


    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_achieved);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getData();
    }

    @Override
    protected void setInitData() {

    }

    @Override
    protected void initEvent() {
        iv_left.setOnClickListener(this);
        ivHead.setOnClickListener(this);
        llAccount.setOnClickListener(this);
        llZantong.setOnClickListener(this);
        llShoucang.setOnClickListener(this);
        llFenxiang.setOnClickListener(this);
        llAccount.setOnClickListener(this);
        llDongtai.setOnClickListener(this);
        llHuida.setOnClickListener(this);
        llTiwen.setOnClickListener(this);
        llAccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
                break;
            case R.id.iv_head:
                showItemActivity(FriendsActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.ll_account:
                showItemActivity(FriendsActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

                break;
            case R.id.ll_zantong:
                showItemActivity(FriendsActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.ll_shoucang:
                showItemActivity(FriendsActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.ll_fenxiang:
                showItemActivity(FriendsActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.ll_dongtai:
                showItemActivity(FriendsActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.ll_huida:
                //showItemActivity(MyAnswersActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.ll_tiwen:
                //showItemActivity(MyTiwenActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;

        }
    }


    /*//用户信息
    private void getData() {
        final String USERINFO_URL = ((MyApplication) getApplication()).getApis().get("Host").toString() +
                ((MyApplication) getApplication()).getApis().get("UserInfo").toString();

        OkHttpUtils.post(USERINFO_URL)
                //.headers("cookies", PreferencesUtils.getString(getApplicationContext(), "Cookies"))
                .params("uid","me")
                .execute(new StringDialogCallback(AchievedActivity.this) {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            JSONObject json = new JSONObject(s);
                            setUserInfo(json.getJSONObject("data"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });

    }

    private void setUserInfo(JSONObject data) {
        try {
            //Picasso.with(getContext()).load(parse.isNull(userInfo.get("avator").toString().replace("http://flycat.oss-cn-hangzhou.aliyuncs.com/",""))).into(iv_head);
            Picasso.with(getApplicationContext()).load(parse.isNull(data.getString("avator")).toString().replace("http://flycat.oss-cn-hangzhou.aliyuncs.com/","")).into(ivHead);
            tvNick.setText(data.getString("name"));
            tvZantong.setText(data.getString("praise_count"));
            tvShoucang.setText(data.getString("keep_count"));
            tvFenxaing.setText(data.getString("share_count"));
            tvDongtaicount.setText(data.getString("dyncount"));
            tvHuidacount.setText(data.getString("answer_count"));
            tvTiwencount.setText(data.getString("ask_count"));
            tvFriends.setText(data.getString("concern_count"));
            tvFans.setText(data.getString("be_concern_count"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
