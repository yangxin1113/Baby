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
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.zyx.baby.R;
import com.zyx.baby.adapter.NewsAdapter;
import com.zyx.baby.adapter.NewsYangShengAdapter;
import com.zyx.baby.base.BaseFragment1;
import com.zyx.baby.bean.KnowledgeList;
import com.zyx.baby.bean.NewsModel;
import com.zyx.baby.bean.NewsResponse;
import com.zyx.baby.callback.NewsCallback;
import com.zyx.baby.http.ApisUtil;
import com.zyx.baby.http.ConstantValue;
import com.zyx.baby.presenters.NewsHelper;
import com.zyx.baby.presenters.viewinface.NewsView;
import com.zyx.baby.widget.EmptyView;
import okhttp3.Call;
import okhttp3.Response;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class NewsFragment extends BaseFragment1 implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener, NewsView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    private Context context;
    private NewsYangShengAdapter newsAdapter;
    protected LayoutInflater inflater;

    EmptyView mEmptyView;
    private int mId = 1;
    private int mCurPage = 1;
    private int mRows = 10;
    private int mTotal = 0;

    private NewsHelper newsHelper;

    public static NewsFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(ConstantValue.KNOWLEDGE_ID, id);
        NewsFragment fragment = new NewsFragment();
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
        if (getArguments() != null) {
            mId = getArguments().getInt(ConstantValue.KNOWLEDGE_ID);
        }

        newsHelper = new NewsHelper(getActivity(), this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        newsAdapter = new NewsYangShengAdapter(null);
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
        mCurPage = 1;
        newsHelper.reqNews(mId, mCurPage, mRows);

    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        mCurPage++;
        newsHelper.reqNews(mId, mCurPage, mRows);

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

    @Override
    public void reqSucc(KnowledgeList knowledgeList) {
        if (mCurPage == 1) {
            newsAdapter.setNewData(knowledgeList.getTngou());
            setRefreshing(false);
        } else {
            if (knowledgeList.getTngou().size() > 0) {
                newsAdapter.addData(knowledgeList.getTngou());
            } else {
                newsAdapter.loadComplete();         //加载完成
                View noDataView = inflater.inflate(R.layout.item_no_data, (ViewGroup) recyclerView.getParent(), false);
                newsAdapter.addFooterView(noDataView);
            }

        }
    }

    @Override
    public void reqFail(String msg) {
        //显示数据加载失败,点击重试
        newsAdapter.showLoadMoreFailedView();
        //网络请求失败的回调,一般会弹个Toast
        showToast(msg);
        setRefreshing(false);


    }
}
