package com.zyx.baby.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.zyx.baby.R;
import com.zyx.baby.adapter.SearchContentAdapter;
import com.zyx.baby.adapter.WentiAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.bean.Question;
import com.zyx.baby.bean.WentiData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.support.v7.widget.RecyclerView.OnScrollListener;
import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class WentiFragment extends BaseFragment {

    @BindView(R.id.fresh_content)
    SwipeRefreshLayout fresh_content;
    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private WentiAdapter wentiAdapter;
    private List<WentiData> wentiDatas;
    private int lastVisibleItem ;
    private LinearLayoutManager mLayoutManager;
    private int page =1;

    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_content);
    }

    @Override
    protected void initEvent() {
        fresh_content.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData(10);
            }
        });

        //recyclerview滚动监听实现自动加载
        rv_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；

                    setData(18);

            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    @Override
    protected void setInitData() {
        mLayoutManager = new LinearLayoutManager(getActivity(),VERTICAL,false);
        rv_content.setLayoutManager(mLayoutManager);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        fresh_content.setProgressViewOffset(false, 0
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                        , 24, getResources().getDisplayMetrics()));
        setData(10);
    }

    @Override
    public void onClick(View v) {

    }


    private void setData(int page) {
        fresh_content.setRefreshing(true);
        wentiDatas = new ArrayList<WentiData>();
        for (int i = 1; i <= page; i++) {
            WentiData wentiData = new WentiData();
            wentiData.set_id(i+"");
            wentiData.setTitle("宝宝白天黑夜颠倒怎么办？");
            wentiData.setComment(i*22+10);
            wentiData.setConcern(i*6+22);
            wentiData.setAnswer(i*3+28);
            wentiDatas.add(wentiData);
        }
        if(wentiAdapter==null){
            rv_content.setAdapter(wentiAdapter = new WentiAdapter(getActivity(),wentiDatas));

        }else{
            wentiAdapter.notifyDataSetChanged();
        }

        fresh_content.setRefreshing(false);
    }
}