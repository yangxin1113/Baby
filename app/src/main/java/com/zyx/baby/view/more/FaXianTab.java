package com.zyx.baby.view.more;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zyx.baby.R;
import com.zyx.baby.adapter.HomeAdapter1;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.bean.HomeData;
import com.zyx.baby.callback.HomeClickListenner;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.widget.FullyLinearLayoutManager;
import com.zyx.baby.widget.HomeScrollView;
import com.zyx.baby.widget.ListItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



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
