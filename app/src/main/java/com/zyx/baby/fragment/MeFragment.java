package com.zyx.baby.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.activity.SettingActivity;
import com.zyx.baby.activity.UserInfoActivity;
import com.zyx.baby.base.BaseFragment1;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.widget.CircleImageView;
import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MeFragment extends BaseFragment1 {


    @BindView(R.id.mtb_title)
    MyTitleBar mtbTitle;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_nick)
    TextView tvNick;
    @BindView(R.id.tv_qianming)
    TextView tvQianming;
    @BindView(R.id.ll_logined)
    LinearLayout llLogined;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.iv_xiaoxi)
    ImageView ivXiaoxi;
    @BindView(R.id.ll_xiaoxi)
    RelativeLayout llXiaoxi;
    @BindView(R.id.iv_chengjiu)
    ImageView ivChengjiu;
    @BindView(R.id.tv_chengjiu)
    TextView tvChengjiu;
    @BindView(R.id.ll_chengjiu)
    RelativeLayout llChengjiu;
    @BindView(R.id.iv_guanzhu)
    ImageView ivGuanzhu;
    @BindView(R.id.tv_guanzhu)
    TextView tvGuanzhu;
    @BindView(R.id.ll_guanzhu)
    RelativeLayout llGuanzhu;
    @BindView(R.id.iv_shoucang)
    ImageView ivShoucang;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.ll_shoucang)
    RelativeLayout llShoucang;
    @BindView(R.id.iv_caogao)
    ImageView ivCaogao;
    @BindView(R.id.tv_caogao)
    TextView tvCaogao;
    @BindView(R.id.ll_caogao)
    RelativeLayout llCaogao;
    @BindView(R.id.iv_shezhi)
    ImageView ivShezhi;
    @BindView(R.id.tv_shezhi)
    TextView tvShezhi;
    @BindView(R.id.ll_shezhi)
    RelativeLayout llShezhi;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        mtbTitle.setText("我的");
    }

    //进入用户信息详情页
    @OnClick(R.id.rl_top) void enterUserInfo9(){
        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }


    @OnClick({R.id.ll_xiaoxi, R.id.ll_chengjiu, R.id.ll_guanzhu, R.id.ll_shoucang, R.id.iv_caogao, R.id.ll_shezhi})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_chengjiu:
                if(TextUtils.isEmpty(tvNick.getText())){
                    LSUtils.showToast(getContext(),"请登录");
                }else {/*
                    Intent i = new Intent(getActivity(), AchievedActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                */}
                break;
            case R.id.ll_guanzhu:
                if(TextUtils.isEmpty(tvNick.getText())){
                    LSUtils.showToast(getContext(),"请登录");
                }else {
                    /*Intent i = new Intent(getActivity(), GuanZhuActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                */}
                break;
            case R.id.ll_shoucang:
                if(TextUtils.isEmpty(tvNick.getText())){
                    LSUtils.showToast(getContext(),"请登录");
                }else {
                   /* Intent i = new Intent(getActivity(), ShoucangActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                */}
                break;
            case R.id.ll_caogao:
                if(TextUtils.isEmpty(tvNick.getText())){
                    LSUtils.showToast(getContext(),"请登录");
                }else {
                    /*Intent i = new Intent(getActivity(), CaoGaoActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                */}
                break;

            case R.id.ll_shezhi:
                Intent i = new Intent(getActivity(), SettingActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;

        }

    }


}

