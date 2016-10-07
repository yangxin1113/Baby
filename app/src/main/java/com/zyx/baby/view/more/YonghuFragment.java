package com.zyx.baby.view.more;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zyx.baby.R;
import com.zyx.baby.adapter.SearchContentAdapter;
import com.zyx.baby.adapter.YonghuAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.bean.Question;
import com.zyx.baby.bean.YonghuData;
import com.zyx.baby.callback.GuanzhuYonghuClickListenner;
import com.zyx.baby.utils.LSUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.VERTICAL;

/**
 * @author Miguel Catalan Bañuls
 */
public class YonghuFragment extends BaseFragment {


    @BindView(R.id.yonghu)
    RecyclerView searchResult;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private YonghuAdapter yonghuAdapter;
    private List<YonghuData> yonghuDatas;
    private int lastVisibleItem;
    private LinearLayoutManager mLayoutManager;
    private int page = 1;

    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_person);
    }

    @Override
    protected void initEvent() {
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData(10);
            }
        });

        //recyclerview滚动监听实现自动加载
        searchResult.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        searchResult.setLayoutManager(mLayoutManager);
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swiperefresh.setProgressViewOffset(false, 0
                , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP
                        , 24, getResources().getDisplayMetrics()));
        setData(10);
    }


    @Override
    public void onClick(View v) {

    }

    /**
     * 接口回调实现RecyclerView的item布局中每个控件的点击事件
     */
    private void itemOnClickListenner() {
        yonghuAdapter.setGuanzhuYonghuClickListenner(new GuanzhuYonghuClickListenner() {


            @Override
            public void showHead(View view, int position) {
                LSUtils.showToast(getContext(),yonghuDatas.get(position).getAvator());
            }

            @Override
            public void showNick(View view, int position) {
                LSUtils.showToast(getContext(),yonghuDatas.get(position).getName());
            }

            @Override
            public void showQianming(View view, int position) {
                LSUtils.showToast(getContext(),yonghuDatas.get(position).getSignature());
            }

            @Override
            public void showIsGuanzhu(View view, int position) {


            }
        });
    }

    private void setData(int page) {
        swiperefresh.setRefreshing(true);
        yonghuDatas = new ArrayList<YonghuData>();
        for (int i = 1; i <= page; i++) {
            YonghuData yonghuData = new YonghuData();
            yonghuData.setId(i);
            yonghuData.setAvator("http://tp2.sinaimg.cn/1310331977/50/39997113790/0");
            yonghuData.setName("亲亲宝贝");
            yonghuData.setSignature("我家宝贝最可爱，哈哈哈");
            yonghuData.setIs_cancel(0);
            yonghuDatas.add(yonghuData);
        }
        if(yonghuAdapter==null){
            searchResult.setAdapter(yonghuAdapter = new YonghuAdapter(getActivity(),yonghuDatas));

        }else{
            yonghuAdapter.notifyDataSetChanged();
        }

        swiperefresh.setRefreshing(false);
        itemOnClickListenner();
    }
}