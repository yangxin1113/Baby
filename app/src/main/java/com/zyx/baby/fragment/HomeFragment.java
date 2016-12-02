package com.zyx.baby.fragment;

import android.view.View;

import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.utils.ConfigUtils;
import com.zyx.baby.widget.MyTitleBar;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    private int mTotalProgress;
    private int mCurrentProgress;

    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_home);
    }

    @Override
    protected void initEvent() {
        //new Thread(new ProgressRunable()).start();
    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("宝贝尿了");
        mTotalProgress = 100;
        mCurrentProgress = 0;

    }

    @Override
    public void onClick(View v) {

    }

    class ProgressRunable implements Runnable {

        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress) {
                mCurrentProgress += 1;
                //tempe.setProgress(mCurrentProgress);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
