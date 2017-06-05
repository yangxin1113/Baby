package com.zyx.baby.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyx.baby.R;
import com.zyx.baby.adapter.DrugClassifyAdapter;
import com.zyx.baby.adapter.DrugsItemAdapter;
import com.zyx.baby.base.BaseActivityNew;
import com.zyx.baby.bean.DrugClassifyBean;
import com.zyx.baby.bean.DrugListBean;
import com.zyx.baby.bean.SearchNewsBean;
import com.zyx.baby.databinding.ActivityDrugBinding;
import com.zyx.baby.databinding.ActivityKeywordBinding;
import com.zyx.baby.presenters.DrugHelper;
import com.zyx.baby.presenters.DrugHelper;
import com.zyx.baby.presenters.viewinface.DrugClassifyView;
import com.zyx.baby.presenters.viewinface.SearchNewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 * 找药品
 */

public class DrugActivity extends BaseActivityNew<ActivityDrugBinding>implements SwipeRefreshLayout.OnRefreshListener, DrugClassifyView, BaseQuickAdapter.RequestLoadMoreListener {

    private static final String TAG = "DrugActivity";
    private Context mContext = DrugActivity.this;
    private DrugHelper mDrugHelper;
    private int id = 1;
    private int page = 1;
    private int rows = 5;
    private DrugsItemAdapter mDrugsItemAdapter;
    private DrugClassifyAdapter mDrugClassifyAdapter;
    protected LayoutInflater inflater;
    private List<DrugClassifyBean.TngouBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug);
        setTitle("药品速查");
        initPresenter();
        initView();
    }

    private void initView() {
        mData = new ArrayList<>();
        mDrugClassifyAdapter = new DrugClassifyAdapter(getApplicationContext(), mData, new DrugClassifyAdapter.OnClickListener() {
            @Override
            public void click(int position) {
                mDrugClassifyAdapter.selectItem(position);
                setRefreshing(true);
                id = mData.get(position).getId();
                page = 1;
                mDrugHelper.reqDrugs(id, page, rows);
            }
        });
        mDrugsItemAdapter = new DrugsItemAdapter(null);
        mDrugsItemAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mDrugsItemAdapter.isFirstOnly(false);
//        bindingView.recyDrug.setItemAnimator(new DefaultItemAnimator());
        bindingView.recyDrug.setLayoutManager(new LinearLayoutManager(mContext));
        bindingView.recyDrug.setAdapter(mDrugsItemAdapter);
        bindingView.recyClass.setAdapter(mDrugClassifyAdapter);
        bindingView.recyClass.setLayoutManager(new LinearLayoutManager(mContext));
        mDrugsItemAdapter.setOnLoadMoreListener(this);

        //开启loading,获取数据
        setRefreshing(true);
        onRefresh();

    }

    private void initPresenter() {
        mDrugHelper = new DrugHelper(getApplicationContext(), this);
    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, DrugActivity.class);
        mContext.startActivity(intent);
    }


    @Override
    public void reqSucc(DrugClassifyBean drugClassifyBean) {
        if(mData.size() ==0){
            mData.addAll(drugClassifyBean.getTngou());
            mDrugClassifyAdapter.notifyDataSetChanged();
        }
        mDrugClassifyAdapter.notifyDataSetChanged();
        page = 1;
        mDrugHelper.reqDrugs(id, page, rows);

    }

    @Override
    public void reqFail(String msg) {
        setRefreshing(false);
        showError();
    }

    @Override
    public void reqDrugs(DrugListBean drugListBean) {
        showContentView();
        if (page == 1) {
            mDrugsItemAdapter.setNewData(drugListBean.getTngou());
            setRefreshing(false);
        } else {
            if (drugListBean.getTngou().size() > 0) {
                mDrugsItemAdapter.addData(drugListBean.getTngou());
            } else {
                mDrugsItemAdapter.loadComplete();         //加载完成
                View noDataView = inflater.inflate(R.layout.item_no_data, (ViewGroup) bindingView.recyDrug.getParent(), false);
                mDrugsItemAdapter.addFooterView(noDataView);
            }

        }

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
        mDrugHelper.reqClassify();

    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        mDrugHelper.reqDrugs(id, page, rows);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drug, menu);//指定Toolbar上的视图文件
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                this.finish();//真正实现回退功能的代码
            default:break;

        }

        return super.onOptionsItemSelected(item);
    }
}
