package com.zyx.baby.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.zyx.baby.R;
import com.zyx.baby.adapter.NewsAdapter;
import com.zyx.baby.base.BaseFragment1;
import com.zyx.baby.bean.NewsModel;
import com.zyx.baby.bean.NewsResponse;
import com.zyx.baby.callback.NewsCallback;
import com.zyx.baby.http.Apis;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class FindTab2 extends BaseFragment1 implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private Context context;
    private int currentPage;
    private NewsAdapter newsAdapter;
    private boolean isInitCache = false;
    private int index;
    private String fragmentTitle;

    public static FindTab2 newInstance(int index) {
        FindTab2 fragment = new FindTab2();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_tab2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        if (args != null) {

            index = args.getInt("index");

            switch (index){
                case 2:
                    fragmentTitle = "国内最新";
                    break;
                case 3:
                    fragmentTitle = "游戏焦点";
                    break;
                default:
                    break;
            }

        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        newsAdapter = new NewsAdapter(null);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsAdapter.isFirstOnly(false);
        recyclerView.setAdapter(newsAdapter);

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        newsAdapter.setOnLoadMoreListener(this);

        //开启loading,获取数据
        setRefreshing(true);
        onRefresh();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        OkGo.get(Apis.NEWS)//
                .params("channelName", fragmentTitle)//
                .params("page", 1)                              //初始化或者下拉刷新,默认加载第一页
                .cacheKey("TabFragment_" + fragmentTitle)       //由于该fragment会被复用,必须保证key唯一,否则数据会发生覆盖
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据
                .execute(new NewsCallback<NewsResponse<NewsModel>>() {
                    @Override
                    public void onSuccess(NewsResponse<NewsModel> newsResponse, Call call, Response response) {
                        NewsModel newsModel = newsResponse.showapi_res_body;
                        currentPage = newsModel.pagebean.currentPage;
                        newsAdapter.setNewData(newsModel.pagebean.contentlist);
                    }

                    @Override
                    public void onCacheSuccess(NewsResponse<NewsModel> newsResponse, Call call) {
                        //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
                        if (!isInitCache) {
                            //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                            onSuccess(newsResponse, call, null);
                            isInitCache = true;
                        }
                    }

                    @Override
                    public void onCacheError(Call call, Exception e) {
                        //获取缓存失败的回调方法,一般很少用到,需要就复写,不需要不用关心
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //网络请求失败的回调,一般会弹个Toast
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onAfter(@Nullable NewsResponse<NewsModel> newsResponse, @Nullable Exception e) {
                        super.onAfter(newsResponse, e);
                        //可能需要移除之前添加的布局
                        newsAdapter.removeAllFooterView();
                        //最后调用结束刷新的方法
                        setRefreshing(false);
                    }
                });
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        OkGo.get(Apis.NEWS)//
                .params("channelName", fragmentTitle)//
                .params("page", currentPage + 1)     //上拉加载更多
                .cacheMode(CacheMode.NO_CACHE)       //上拉不需要缓存
                .execute(new NewsCallback<NewsResponse<NewsModel>>() {
                    @Override
                    public void onSuccess(NewsResponse<NewsModel> newsResponse, Call call, Response response) {
                        NewsModel newsModel = newsResponse.showapi_res_body;
                        currentPage = newsModel.pagebean.currentPage;
                        newsAdapter.addData(newsModel.pagebean.contentlist);
                        //显示没有更多数据
                        if (newsModel.pagebean.allPages == currentPage) {
                            newsAdapter.loadComplete();         //加载完成
                            View noDataView = inflater.inflate(R.layout.item_no_data, (ViewGroup) recyclerView.getParent(), false);
                            newsAdapter.addFooterView(noDataView);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        //显示数据加载失败,点击重试
                        newsAdapter.showLoadMoreFailedView();
                        //网络请求失败的回调,一般会弹个Toast
                        showToast(e.getMessage());
                    }
                });
    }

    public void showToast(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }

    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }

}
