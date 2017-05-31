package com.zyx.baby.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.zyx.baby.R;
import com.zyx.baby.adapter.FragmentAdapter;
import com.zyx.baby.base.BaseActivityNew;
import com.zyx.baby.databinding.ActivityYangshengBinding;
import com.zyx.baby.fragment.NewsFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/23.
 * 找药
 */

public class FindDrugActivity extends BaseActivityNew<ActivityYangshengBinding> {


    private static final String TAG = "RegisterActivity";
    private Context mContext = FindDrugActivity.this;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> mTitleList = new ArrayList<>();
    private FragmentAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yangsheng);
        setTitle("养生堂");
        showContentView();
        initData();
        initFragmentList();

    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, FindDrugActivity.class);
        mContext.startActivity(intent);
    }


    private void initFragmentList() {

        String[] titles = mContext.getResources().getStringArray(R.array.tab_item_name);
        for (int i = 0; i < titles.length; i++) {
            mFragments.add(NewsFragment.newInstance(i));
        }
        myAdapter.setItems(mFragments, titles);

        bindingView.viewPager.setAdapter(myAdapter);
        // 左右预加载页面的个数
        bindingView.viewPager.setOffscreenPageLimit(4);
        myAdapter.notifyDataSetChanged();
        bindingView.tabLayout.setupWithViewPager(bindingView.viewPager);

    }

    private void initData() {
        myAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, mTitleList);

    }


    protected void initEvent() {

    }





}
