package com.zyx.baby.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.lzy.imagepicker.view.SystemBarTintManager;
import com.zyx.baby.R;
import com.zyx.baby.utils.MyUtils;


import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/8/8 0008.
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected MyUtils utils;

    protected static String TAG;
    protected Context mContext;

    @SuppressWarnings("unchecked")
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        init(arg0);
        setInitData();
        initEvent();
        TAG = this.getClass().getName();
        mContext = this;
    }


    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /** 子类可以重写改变状态栏颜色 *//*
    protected int setStatusBarColor() {
        return getColorPrimary();
    }*/

    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }



    /** 获取主题色 *//*
    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }*/

    /** 获取深主题色 */
    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }

    /** 初始化 Toolbar */
    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    public void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }


    /**
     * 初始化布局
     */
    protected abstract void init(Bundle arg0);

    /**
     * 初始化数据
     */
    protected abstract void setInitData();

    /**
     * 初始化控件事件
     */
    protected abstract void initEvent();


    /**
     * 切换到指定的Activity 无传递数据
     *
     * @param cls
     *            指定的Activity
     */
    public void showItemActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        //overridePendingTransition(enterAnim, exitAnim);
        // overridePendingTransition(R.anim.push_out, R.anim.scale_out);
    }


}
