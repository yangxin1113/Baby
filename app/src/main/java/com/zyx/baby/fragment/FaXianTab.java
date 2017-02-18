package com.zyx.baby.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.adapter.ViewPagerAdapter;
import com.zyx.baby.base.BaseFragment1;
import com.zyx.baby.base.BaseFragment2;
import com.zyx.baby.bean.TagsBean;
import com.zyx.baby.event.ErrorEvent;
import com.zyx.baby.event.LoginEvent;
import com.zyx.baby.event.TagsEvent;
import com.zyx.baby.http.OkGoMethod;
import com.zyx.baby.utils.LSUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class FaXianTab extends BaseFragment2 {


    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_rank_tab)
    ViewPager vpRankTab;
    private View view;

    private List<Fragment> fragmentList;
    private List<TagsBean.DataBean.ListTagsByUser1Bean> tags;
    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;
    private ViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == view) {
            view = inflater.inflate(R.layout.tab_faxian, null);
            ButterKnife.bind(this, view);
            EventBus.getDefault().register(this);
            initData();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    protected void initData() {
        //setupViewPager(vpRankTab);
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        tabLayout.setupWithViewPager(vpRankTab);
        //设置可以滑动
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        vpRankTab.setOffscreenPageLimit(1);
        isPrepared = true;
        lazyLoad();
    }

    private void request(){
        HashMap params = new HashMap();
        params.put("userid", "1");
        OkGoMethod.getTags(params,"getTags");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void TagsEventBus(TagsEvent event) {
        if (TextUtils.equals(event.getTag(),"getTags")){
            mHasLoadedOnce = true;
            LSUtils.showToast(getActivity(),event.getObj().toString());
            TagsBean tagsBean = (TagsBean) event.getObj();
            tags = tagsBean.getData().getListTagsByUser1();
            fragmentList = new ArrayList<Fragment>();
            for(int i=0; i<tags.size(); i++){
                adapter.addFragment(new FindTab1(), tags.get(i).getTag());
            }
            vpRankTab.setAdapter(adapter);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void errorEventBus(ErrorEvent message) {
        if (TextUtils.equals(message.getTag(),"getTags")){
            LSUtils.showToast(getActivity(),"服务器异常");
        }
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        request();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
