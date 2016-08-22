package com.zyx.baby.view.me;

import android.view.View;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MeFragment extends BaseFragment {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_me);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("宝宝中心");

    }

    @Override
    public void onClick(View v) {

    }
}
