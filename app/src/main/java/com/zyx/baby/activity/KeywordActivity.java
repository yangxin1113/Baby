package com.zyx.baby.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.zyx.baby.R;
import com.zyx.baby.adapter.FragmentAdapter;
import com.zyx.baby.base.BaseActivityNew;
import com.zyx.baby.databinding.ActivityKeywordBinding;
import com.zyx.baby.databinding.ActivityYangshengBinding;
import com.zyx.baby.fragment.NewsFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/23.
 * 关键字
 */

public class KeywordActivity extends BaseActivityNew<ActivityKeywordBinding> {


    private static final String TAG = "KeywordActivity";
    private Context mContext = KeywordActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyword);
        setTitle("养生堂");
        showContentView();

    }

    public static void start(Context mContext) {
        Intent intent = new Intent(mContext, KeywordActivity.class);
        mContext.startActivity(intent);
    }







}
