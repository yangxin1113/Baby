package com.zyx.baby.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zyx.baby.R;
import com.zyx.baby.activity.BabyInfoActivity;
import com.zyx.baby.activity.PeeActivity;
import com.zyx.baby.activity.PredictActivity;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.widget.MyTitleBar;

import static android.app.Activity.RESULT_CANCELED;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class BabyFragment extends BaseFragment {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;



    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_baby);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("宝宝中心");

    }

    @OnClick({R.id.rl_baby, R.id.rl_tongji})
    public void babyClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.rl_baby:
                intent.setClass(getActivity(), BabyInfoActivity.class);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.rl_tongji:
                intent.setClass(getActivity(), PeeActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.rl_yuce:
                intent.setClass(getActivity(), PredictActivity.class);
                startActivity(intent);
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

    @Override
    public void onClick(View v) {

    }
}
