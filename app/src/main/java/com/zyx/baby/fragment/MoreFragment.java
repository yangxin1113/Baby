package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.adapter.MyFragmentPagerAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.base.BaseFragment2;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MoreFragment extends BaseFragment2 {

    @BindView(R.id.tv_wenbei)
    TextView tv_wenbei;
    @BindView(R.id.tv_faxian)
    TextView tv_faxian;
    @BindView(R.id.vp_more)
    ViewPager customViewPager;

    private BeiWenTab beiWenTab;
    private FaXianTab faXianTab;
    private ArrayList<Fragment> tabsList;
    private View view;

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_more, null);
            ButterKnife.bind(this, view);

        initData();
        return view;
    }

    protected void initData() {
        setInitTabs();
        initEvent();
    }
    protected void initEvent() {
        tv_wenbei.setOnClickListener(new MyOnClickListener(0));
        tv_faxian.setOnClickListener(new MyOnClickListener(1));
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

    @Override
    protected void lazyLoad() {

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
