package com.zyx.baby.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xrecyclerview.XRecyclerView;
import com.zyx.baby.R;
import com.zyx.baby.adapter.DrugsItemAdapter;
import com.zyx.baby.adapter.FragmentAdapter;
import com.zyx.baby.adapter.NewsKeyWordAdapter;
import com.zyx.baby.base.BaseActivityNew;
import com.zyx.baby.bean.DrugListBean;
import com.zyx.baby.bean.SearchNewsBean;
import com.zyx.baby.databinding.ActivityKeywordBinding;
import com.zyx.baby.databinding.ActivityYangshengBinding;
import com.zyx.baby.fragment.NewsFragment;
import com.zyx.baby.presenters.SearchHelper;
import com.zyx.baby.presenters.viewinface.SearchNewsView;

import net.nightwhistler.htmlspanner.TextUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/23.
 * 关键字
 */

public class KeywordActivity extends BaseActivityNew<ActivityKeywordBinding>implements SearchNewsView,SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    private static final String TAG = "KeywordActivity";
    private Context mContext = KeywordActivity.this;
    private SearchHelper mSearchHelper;
    private String keyWord;
    private String name;
    private int page = 1;
    private int rows = 10;
    private int classify = -1;
    private NewsKeyWordAdapter mNewsKeyWordAdapter;
    private DrugsItemAdapter mDrugsItemAdapter;

    protected LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);
        if(getIntent() != null){
            keyWord = getIntent().getStringExtra("keyWord");
            name = getIntent().getStringExtra("name");
        }
        setTitle(keyWord);
        initPresenter();
        initView();
    }

    private void initView() {
        bindingView.refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        bindingView.recyclerView.setItemAnimator(new DefaultItemAnimator());
        bindingView.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        bindingView.refreshLayout.setOnRefreshListener(this);
        if(TextUtils.equals(name, "lore")){
            mNewsKeyWordAdapter = new NewsKeyWordAdapter(null);
            mNewsKeyWordAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mNewsKeyWordAdapter.isFirstOnly(false);
            mNewsKeyWordAdapter.setOnLoadMoreListener(this);
            bindingView.recyclerView.setAdapter(mNewsKeyWordAdapter);
        }else if(TextUtils.equals(name, "drug")){
            mDrugsItemAdapter = new DrugsItemAdapter(null);
            mDrugsItemAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            mDrugsItemAdapter.isFirstOnly(false);
            mDrugsItemAdapter.setOnLoadMoreListener(this);
            bindingView.recyclerView.setAdapter(mDrugsItemAdapter);
        }


        //开启loading,获取数据
        setRefreshing(true);
        onRefresh();

    }

    private void initPresenter() {
        mSearchHelper = new SearchHelper(getApplicationContext(), this);
    }

    public static void start(Activity mContext, String keyWord, String name) {
        Intent intent = new Intent(mContext, KeywordActivity.class);
        intent.putExtra("keyWord", keyWord);
        intent.putExtra("name", name);
        mContext.startActivity(intent);
    }


    @Override
    public void reqSucc(Object searchNewsBean) {
        showContentView();

        if(TextUtils.equals(name, "lore")){
            SearchNewsBean searchNewsBean1 = (SearchNewsBean) searchNewsBean;
            if (page == 1) {
                mNewsKeyWordAdapter.setNewData(searchNewsBean1.getTngou());
                setRefreshing(false);
            } else {
                if (searchNewsBean1.getTngou().size() > 0) {
                    mNewsKeyWordAdapter.addData(searchNewsBean1.getTngou());
                } else {
                    mNewsKeyWordAdapter.loadComplete();         //加载完成
                    View noDataView = inflater.inflate(R.layout.item_no_data, (ViewGroup) bindingView.recyclerView.getParent(), false);
                    mNewsKeyWordAdapter.addFooterView(noDataView);
                }

            }
        }else if(TextUtils.equals(name, "drug")){
            DrugListBean searchNewsBean1 = (DrugListBean) searchNewsBean;
            if (page == 1) {
                mDrugsItemAdapter.setNewData(searchNewsBean1.getTngou());
                setRefreshing(false);
            } else {
                if (searchNewsBean1.getTngou().size() > 0) {
                    mDrugsItemAdapter.addData(searchNewsBean1.getTngou());
                } else {
                    mDrugsItemAdapter.loadComplete();         //加载完成
                    View noDataView = inflater.inflate(R.layout.item_no_data, (ViewGroup) bindingView.recyclerView.getParent(), false);
                    mDrugsItemAdapter.addFooterView(noDataView);
                }

            }
        }


    }

    @Override
    public void reqFail(String msg) {
        setRefreshing(false);
        showError();

    }

    public void setRefreshing(final boolean refreshing) {
        bindingView.refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                bindingView.refreshLayout.setRefreshing(refreshing);
            }
        });
    }

    @Override
    public void onRefresh() {
        page = 1;
        mSearchHelper.searchNews(keyWord, name, page, rows, classify);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        mSearchHelper.searchNews(keyWord, name, page, rows, classify);
    }
}
