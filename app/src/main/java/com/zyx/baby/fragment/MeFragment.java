package com.zyx.baby.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.zyx.baby.R;
import com.zyx.baby.activity.SettingActivity;
import com.zyx.baby.activity.UserInfoActivity;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.databinding.FragmentMeBinding;
import com.zyx.baby.utils.LSUtils;

import butterknife.OnClick;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MeFragment extends BaseFragment<FragmentMeBinding> implements View.OnClickListener {

    @Override
    public int setContent() {
        return R.layout.fragment_me;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initData();
        initEvent();
    }

    private void initEvent() {
        bindingView.llChengjiu.setOnClickListener(this);
        bindingView.llGuanzhu.setOnClickListener(this);
        bindingView.llShoucang.setOnClickListener(this);
        bindingView.llShezhi.setOnClickListener(this);
    }

    private void initData() {
        bindingView.mtbTitle.setText("我的");
    }

    //进入用户信息详情页
    @OnClick(R.id.rl_top) void enterUserInfo(){
        Intent intent = new Intent(getActivity(), UserInfoActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_chengjiu:
                if(TextUtils.isEmpty(bindingView.tvNick.getText())){
                    LSUtils.showToast(getContext(),"请登录");
                }else {
                    /*Intent i = new Intent(getActivity(), AchievedActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                }
                break;
            case R.id.ll_guanzhu:
                if(TextUtils.isEmpty(bindingView.tvNick.getText())){
                    LSUtils.showToast(getContext(),"请登录");
                }else {
                    /*Intent i = new Intent(getActivity(), GuanZhuActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                }
                break;
            case R.id.ll_shoucang:
                if(TextUtils.isEmpty(bindingView.tvNick.getText())){
                    LSUtils.showToast(getContext(),"请登录");
                }else {
                    /*Intent i = new Intent(getActivity(), ShoucangActivity.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);*/
                }
                break;


            case R.id.ll_shezhi:
                Intent i = new Intent(getActivity(), SettingActivity.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;

        }
    }

}

