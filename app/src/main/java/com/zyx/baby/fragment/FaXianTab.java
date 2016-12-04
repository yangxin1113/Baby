package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyx.baby.R;
import com.zyx.baby.adapter.ViewPagerAdapter;
import com.zyx.baby.base.BaseFragment1;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class FaXianTab extends BaseFragment1 {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_rank_tab)
    ViewPager vpRankTab;

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FindTab1(), "热门问题");
        adapter.addFragment(new FindTab2().newInstance(2), "优质分享");
        adapter.addFragment(new FindTab2().newInstance(3), "宝贝教育");
        viewPager.setAdapter(adapter);
    }



    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_faxian, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        setupViewPager(vpRankTab);
        tabLayout.setupWithViewPager(vpRankTab);
    }
}
