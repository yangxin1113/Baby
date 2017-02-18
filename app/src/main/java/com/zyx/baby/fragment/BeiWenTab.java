package com.zyx.baby.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.ninegrid.NineGridView;
import com.moxun.tagcloudlib.view.TagCloudView;
import com.zyx.baby.R;
import com.zyx.baby.activity.SearchActivity;
import com.zyx.baby.adapter.TextTagsAdapter;
import com.zyx.baby.base.BaseFragment;
import com.zyx.baby.base.BaseFragment2;
import com.zyx.baby.utils.GlideImageLoader;
import com.zyx.baby.utils.LSUtils;
import com.zyx.baby.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class BeiWenTab extends BaseFragment2 {

    @BindView(R.id.tag_cloud)
    TagCloudView tagCloudView;
    @BindView(R.id.search)
    SearchView search;
    private View view;

    //private ShowAndHideState showAndHideState; //隐藏底部导航栏接口

    String [] hotWords = new String[]{"脐带脱落后可以立即给婴儿洗澡吗？","什么是新生儿ABO溶血病？如何治疗？"
            ,"教新妈妈正确抱新生宝宝的5种方式","月子餐：超级无敌下奶汤——猪脚炖花生","什么是新生儿Rh血型不合溶血病？"
            ,"新生儿黄疸的自然消退期及处置方法","宝宝一天应该睡多长时间才算正常？","宝宝得了脐疝咋办？如何护理预防？"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == view){
            view = inflater.inflate(R.layout.tab_beiwen, null);
            ButterKnife.bind(this,view);
            initData();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        return view;
    }


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

    protected void initData() {
        TextTagsAdapter adapter = new TextTagsAdapter(myOnClickLisenenr, hotWords);
        tagCloudView.setAdapter(adapter);
        NineGridView.setImageLoader(new GlideImageLoader());
        initEvent();

    }



    private TextTagsAdapter.MyOnClickLisenenr myOnClickLisenenr = new TextTagsAdapter.MyOnClickLisenenr() {
        @Override
        public void myOnClick(int position, View v) {
           LSUtils.showToast(getContext(), hotWords[position].toString()+"2211");
        }
    };


    @Override
    protected void lazyLoad() {

    }
}
