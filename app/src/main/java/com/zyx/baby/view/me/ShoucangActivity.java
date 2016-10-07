package com.zyx.baby.view.me;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zyx.baby.R;
import com.zyx.baby.adapter.HomeAdapter1;
import com.zyx.baby.base.BaseFragmentActivity;
import com.zyx.baby.bean.HomeData;
import com.zyx.baby.callback.HomeClickListenner;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.widget.FullyLinearLayoutManager;
import com.zyx.baby.widget.HomeScrollView;
import com.zyx.baby.widget.ListItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class ShoucangActivity extends BaseFragmentActivity {


    @BindView(R.id.rv_ques)
    RecyclerView rvQues;
    @BindView(R.id.scroll_home)
    HomeScrollView scrollHome;
    @BindView(R.id.swipelayout)
    SwipeRefreshLayout swipelayout;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_top)
    RelativeLayout ivTop;

    private List<HomeData> homeDatas = new ArrayList<HomeData>();
    private HomeAdapter1 homeAdapter;
    private FullyLinearLayoutManager mLayoutManager;


    /**
     * 初始化布局
     *
     * @param arg0
     */
    @Override
    protected void init(Bundle arg0) {
        setContentView(R.layout.activity_shoucang);
    }

    @Override
    protected void initEvent() {
        ivLeft.setOnClickListener(this);
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

        mLayoutManager = new FullyLinearLayoutManager(ShoucangActivity.this, VERTICAL, false);
        rvQues.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_left:
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
                break;
        }

    }


    private void getData() {


        for (int i = 1; i <= 10; i++) {
            HomeData homeData = new HomeData();
            homeData.set_id(i + "");
            homeData.setTitle("宝宝为何喜欢咬书和撕书？");
            homeData.setAvator("http://tp2.sinaimg.cn/1619687665/50/5642384100/1");
            homeData.setName("倪妈妈");
            homeData.setPraise_up_count(2 + 10 * 2);
            homeData.setComment(22 + 10 * 3);
            homeDatas.add(homeData);
        }
        homeAdapter = new HomeAdapter1(ShoucangActivity.this, homeDatas);
        rvQues.setAdapter(homeAdapter);
        rvQues.addItemDecoration(new ListItemDecoration(ShoucangActivity.this, LinearLayoutManager.VERTICAL));
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
                LSUtils.showToast(ShoucangActivity.this, "点击了我图片");
            }

            @Override
            public void showhead(View view, int position) {
                LSUtils.showToast(ShoucangActivity.this, "点击了我头像");

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.left_in, R.anim.right_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
