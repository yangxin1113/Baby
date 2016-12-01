package com.zyx.baby.fragment;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.*;

import android.widget.*;
import com.zyx.baby.R;
import com.zyx.baby.adapter.CaogaoAdapter;
import com.zyx.baby.adapter.HomeAdapter1;
import com.zyx.baby.adapter.ViewPagerAdapter;
import com.zyx.baby.adapter.YonghuAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.base.BaseActivity;
import com.zyx.baby.bean.CaogaoData;
import com.zyx.baby.bean.HomeData;
import com.zyx.baby.bean.YonghuData;
import com.zyx.baby.callback.CaogaoClickListenner;
import com.zyx.baby.callback.GuanzhuYonghuClickListenner;
import com.zyx.baby.callback.HomeClickListenner;
import com.zyx.baby.utils.BluetoothUtils;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.activity.BluetoothChat;
import com.zyx.baby.widget.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;


import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class FaXianTab extends BaseFragment {


    @BindView(R.id.rv_ques)
    RecyclerView rvQues;
    @BindView(R.id.scroll_home)
    HomeScrollView scrollHome;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;

    private List<HomeData> homeDatas = new ArrayList<HomeData>();
    private HomeAdapter1 homeAdapter;
    private FullyLinearLayoutManager mLayoutManager;


    @Override
    protected void init() {
        setLayoutRes(R.layout.tab_faxian);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void setInitData() {

        scrollHome.setSwipeRefreshLayout(swipelayout);
        swipelayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        getData();

        mLayoutManager = new FullyLinearLayoutManager(getActivity(), VERTICAL, false);
        rvQues.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onClick(View v) {

    }


    private void getData() {


        for(int i = 1; i<=10; i++){
            HomeData homeData = new HomeData();
            homeData.set_id(i+"");
            homeData.setTitle("宝宝为何喜欢咬书和撕书？");
            homeData.setAvator("http://tp2.sinaimg.cn/1619687665/50/5642384100/1");
            homeData.setName("倪妈妈");
            homeData.setPraise_up_count(2+10*2);
            homeData.setComment(22+10*3);
            homeDatas.add(homeData);
        }
        homeAdapter = new HomeAdapter1(getContext(), homeDatas);
        rvQues.setAdapter(homeAdapter);
        rvQues.addItemDecoration(new ListItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvQues.setFocusable(false);
        scrollHome.smoothScrollTo(0, 0);
        itemOnClickListenner(); //item监听事件

    }

    /**
     * 接口回调实现RecyclerView的item布局中每个控件的点击事件
     */
    private void itemOnClickListenner() {
        homeAdapter.setHomeClickListenner(new HomeClickListenner() {
            @Override
            public void showTopic(View view, int position) {

            }

            @Override
            public void showQuestion(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getQuestion());

            }

            @Override
            public void showAnswer(View view, int position) {
                //LSUtils.showToast(getContext(), "点击了我" + homeDatas.getData().get(position).getContent());

            }

            @Override
            public void showTopicImg(View view, int position) {
                LSUtils.showToast(getContext(), "点击了我图片");
            }

            @Override
            public void showhead(View view, int position) {
                LSUtils.showToast(getContext(), "点击了我头像");

            }

            @Override
            public void showUsername(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getUsername());
            }

            @Override
            public void showAgreecount(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getAgreecount());
            }

            @Override
            public void showTalkcount(View view, int position) {
                //LSUtils.showToast(getContext(),"点击了我"+ homeDatas.get(position).getTalkcount());
            }
        });
    }

}
