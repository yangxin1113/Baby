package com.zyx.baby.view.me;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zyx.baby.R;
import com.zyx.baby.adapter.YonghuAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.bean.YonghuData;
import com.zyx.baby.callback.GuanzhuYonghuClickListenner;
import com.zyx.baby.utils.LSUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 粉丝列表
 * Created by Administrator on 2016/8/29 0029.
 */
public class FansListFragment extends BaseFragment {


    @BindView(R.id.yonghu)
    RecyclerView rvYonghu;
    private List<YonghuData> yonghuDatas = new ArrayList<YonghuData>();
    private YonghuAdapter yonghuAdapter;


    @Override
    protected void init() {
        setLayoutRes(R.layout.fragment_person);

    }

    @Override
    protected void initEvent() {
        itemOnClickListenner();
    }

    @Override
    protected void setInitData() {

        //设置LinearLayoutManager布局管理器，实现ListView效果
        rvYonghu.setLayoutManager(new LinearLayoutManager(getActivity()));

    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

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



}
