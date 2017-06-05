package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zyx.baby.R;
import com.zyx.baby.activity.DrugActivity;
import com.zyx.baby.activity.ToolActivity;
import com.zyx.baby.activity.YangShengActivity;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.databinding.FragmentLifeBinding;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class FindFragment extends BaseFragment<FragmentLifeBinding> implements View.OnClickListener {

    @Override
    public int setContent() {
        return R.layout.fragment_life;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        initData();
        initEvent();
    }

    private void initEvent() {
        bindingView.tvYangsheng.setOnClickListener(this);
        bindingView.tvDrug.setOnClickListener(this);
        bindingView.tvKepu.setOnClickListener(this);
        bindingView.tvTool.setOnClickListener(this);
    }

    private void initData() {
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_yangsheng:
                YangShengActivity.start(getActivity());
                break;
            case R.id.tv_drug:
                DrugActivity.start(getActivity());
                break;
            case R.id.tv_kepu:

                break;
            case R.id.tv_tool:
                ToolActivity.startActivity(getActivity());
                break;


        }
    }

}

