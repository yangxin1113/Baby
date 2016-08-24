package com.zyx.baby.view.more;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.adapter.MyFragmentPagerAdapter;
import com.zyx.baby.base.BaseFragment;


import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MoreFragment extends BaseFragment {

    @BindView(R.id.tv_wenbei)
    TextView tv_wenbei;
    @BindView(R.id.tv_faxian)
    TextView tv_faxian;
    @BindView(R.id.vp_more)
    ViewPager customViewPager;

    private BeiWenTab beiWenTab;
    private FaXianTab faXianTab;
    private ArrayList<Fragment> tabsList;

    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_more);

    }

    @Override
    protected void initEvent() {
        tv_wenbei.setOnClickListener(new MyOnClickListener(0));
        tv_faxian.setOnClickListener(new MyOnClickListener(1));
    }

    @Override
    protected void setInitData() {
        setInitTabs();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }

    }

    public void setInitTabs(){
        tabsList = new ArrayList<Fragment>();
        beiWenTab = new BeiWenTab();
        faXianTab = new FaXianTab();
        tabsList.add(beiWenTab);
        tabsList.add(faXianTab);
        customViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(),tabsList));
        customViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
        customViewPager.setCurrentItem(0);
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            customViewPager.setCurrentItem(index);
        }
    }

    /**
     * fragPager监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0){
                case 0:
                    tv_wenbei.setTextColor(ContextCompat.getColor(getContext(),R.color.top_bar_color));
                    tv_wenbei.setBackgroundResource(R.drawable.top_bar_l0);
                    tv_faxian.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
                    tv_faxian.setBackgroundResource(R.drawable.top_bar_r0);
                    break;
                case 1:
                    tv_wenbei.setTextColor(ContextCompat.getColor(getContext(),R.color.white));
                    tv_wenbei.setBackgroundResource(R.drawable.top_bar_l1);
                    tv_faxian.setTextColor(ContextCompat.getColor(getContext(),R.color.top_bar_color));
                    tv_faxian.setBackgroundResource(R.drawable.top_bar_r1);
                    break;
            }

        }
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}
