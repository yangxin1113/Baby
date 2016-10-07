package com.zyx.baby.view.baby;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.widget.CircleImageView;
import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_CANCELED;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class BabyFragment extends BaseFragment {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.rl_baby)
    RelativeLayout rlBaby;
    @BindView(R.id.iv_tongji)
    ImageView ivTongji;
    @BindView(R.id.rl_tongji)
    RelativeLayout rlTongji;
    @BindView(R.id.iv_yuce)
    ImageView ivYuce;
    @BindView(R.id.rl_yuce)
    RelativeLayout rlYuce;
    @BindView(R.id.iv_fenbu)
    ImageView ivFenbu;
    @BindView(R.id.rl_fenbu)
    RelativeLayout rlFenbu;
    @BindView(R.id.iv_niaopian)
    ImageView ivNiaopian;
    @BindView(R.id.rl_niaopain)
    RelativeLayout rlNiaopain;
    @BindView(R.id.iv_jiance)
    ImageView ivJiance;
    @BindView(R.id.rl_jiance)
    RelativeLayout rlJiance;
    @BindView(R.id.iv_miss)
    ImageView ivMiss;
    @BindView(R.id.rl_miss)
    RelativeLayout rlMiss;

    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_baby);
    }

    @Override
    protected void initEvent() {
        rlBaby.setOnClickListener(this);

    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("宝宝中心");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_baby:
                Intent i = new Intent(getActivity(), BabyInfoActivity.class);
                startActivityForResult(i,Activity.RESULT_FIRST_USER);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Activity.RESULT_FIRST_USER) {

            if (resultCode == RESULT_CANCELED) {
                Bundle bundle = data.getExtras();
                //LSUtils.showToast(getContext(), bundle.getString("datefrom"));
            }
        }
    }
}
