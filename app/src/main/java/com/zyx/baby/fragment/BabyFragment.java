package com.zyx.baby.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zyx.baby.R;
import com.zyx.baby.activity.BabyInfoActivity;
import com.zyx.baby.activity.DiapersActivity;
import com.zyx.baby.activity.PeeActivity;
import com.zyx.baby.activity.PredictActivity;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.databinding.FragmentBabyBinding;
import com.zyx.baby.widget.MyTitleBar;

import static android.app.Activity.RESULT_CANCELED;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class BabyFragment extends BaseFragment<FragmentBabyBinding> implements View.OnClickListener{



    @Override
    public int setContent() {
        return R.layout.fragment_baby;
    }
    protected void setInitData() {
        bindingView.rlBaby.setOnClickListener(this);
        bindingView.rlTongji.setOnClickListener(this);
        bindingView.rlYuce.setOnClickListener(this);
        bindingView.rlFenbu.setOnClickListener(this);
        bindingView.rlNiaopain.setOnClickListener(this);
        bindingView.rlJiance.setOnClickListener(this);
        bindingView.rlMiss.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingView.mtbTitle.setText("统计监测");
        showContentView();
        setInitData();
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

        Intent intent = new Intent();

        switch (v.getId()) {
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
            case R.id.rl_fenbu:
                intent.setClass(getActivity(), PredictActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.rl_niaopain:
                intent.setClass(getActivity(), DiapersActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.rl_jiance:
                intent.setClass(getActivity(), PredictActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.rl_miss:
                intent.setClass(getActivity(), PredictActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
        }
    }
}
