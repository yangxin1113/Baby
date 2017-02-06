package com.zyx.baby.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zyx.baby.R;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.widget.MyTempView;
import com.zyx.baby.widget.MyTitleBar;
import com.zyx.baby.widget.TempControlView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.mtb_title)
    MyTitleBar myTitleBar;
    @BindView(R.id.iv_temp)
    TempControlView tempControl;
    @BindView(R.id.iv_demp)
    TempControlView dempControl;
   /* @BindView(R.id.mTemp)
    MyTempView mTempView;*/
    private int mTotalProgress;
    private int mCurrentProgress;

    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_home);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void setInitData() {
        myTitleBar.setText("宝贝尿了");
        mTotalProgress = 100;
        mCurrentProgress = 0;

        tempControl.setTemp(15, 30, 15);
       // mTempView.setTemp("37.5", 39.5f, 35.4f, getActivity(), 380);
        tempControl.setOnTempChangeListener(new TempControlView.OnTempChangeListener() {
            @Override
            public void change(int temp) {
                Toast.makeText(getActivity(), temp + "℃", Toast.LENGTH_SHORT).show();
            }
        });


        dempControl.setTemp(15, 30, 15);
        // mTempView.setTemp("37.5", 39.5f, 35.4f, getActivity(), 380);
        dempControl.setOnTempChangeListener(new TempControlView.OnTempChangeListener() {
            @Override
            public void change(int temp) {
                Toast.makeText(getActivity(), temp + "℃", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
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
