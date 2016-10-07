package com.zyx.baby.view.more;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moxun.tagcloudlib.view.TagCloudView;


import com.zyx.baby.R;
import com.zyx.baby.adapter.ShowAndHideState;
import com.zyx.baby.adapter.TextTagsAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.bean.User;
import com.zyx.baby.http.Apis;

import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.widget.SearchView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class BeiWenTab extends BaseFragment {

    @BindView(R.id.tag_cloud)
    TagCloudView tagCloudView;
    @BindView(R.id.search)
    SearchView search;

    //private ShowAndHideState showAndHideState; //隐藏底部导航栏接口

    String [] hotWords = new String[]{"脐带脱落后可以立即给婴儿洗澡吗？","什么是新生儿ABO溶血病？如何治疗？"
            ,"教新妈妈正确抱新生宝宝的5种方式","月子餐：超级无敌下奶汤——猪脚炖花生","什么是新生儿Rh血型不合溶血病？"
            ,"新生儿黄疸的自然消退期及处置方法","宝宝一天应该睡多长时间才算正常？","宝宝得了脐疝咋办？如何护理预防？"};


   /* public BeiWenTab (ShowAndHideState showAndHideState) {
        this.showAndHideState = showAndHideState;
    }*/

    @Override
    protected void init() {
        setLayoutRes(R.layout.tab_beiwen);

    }

    @Override
    protected void initEvent() {
        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = search.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null)
                    return false;
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;
                if (event.getX() > search.getWidth()
                        - search.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    @Override
    protected void setInitData() {
        TextTagsAdapter adapter = new TextTagsAdapter(myOnClickLisenenr, hotWords);
        tagCloudView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    private TextTagsAdapter.MyOnClickLisenenr myOnClickLisenenr = new TextTagsAdapter.MyOnClickLisenenr() {
        @Override
        public void myOnClick(int position, View v) {
           LSUtils.showToast(getContext(), hotWords[position].toString()+"2211");
        }
    };



}
