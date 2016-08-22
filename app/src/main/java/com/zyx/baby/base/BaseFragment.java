package com.zyx.baby.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.zyx.baby.utils.MyUtils;

import java.nio.Buffer;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.*;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public abstract class BaseFragment extends Fragment implements OnClickListener{

    protected MyUtils utils;
    protected LayoutInflater inflater;
    private int resLayout;
    protected Bundle savedInstanceState;// bundle对象
    private Unbinder unbinder;




    public int getLayoutRes() {
        return resLayout;
    }

    /**
     * 设置fragment关联布局
     *
     * @param resLayout 关联布局id
     */
    public void setLayoutRes(int resLayout) {
        this.resLayout = resLayout;
    }

    protected abstract void init();

    protected abstract void initEvent();

    protected abstract void setInitData();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        try {
            this.inflater = inflater;
            View view = inflater.inflate(resLayout, container, false);
            unbinder =  ButterKnife.bind(this, view);
            setInitData();
            initEvent();
            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        utils = MyUtils.getInstance();
        init();
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

   /* *//**
     * 如果没有数据处理 请设置为false ,或�?�发送一个handler信息�?以关闭dialog
     *//*
    protected boolean isFrist = false;
    protected LayoutInflater inflater;
    protected MyUtils utils;
    protected Animation animation;// 动画对象
    protected Bundle savedInstanceState;// bundle对象

    // private ProgressDialog pd;
    // private Dialog mDialog;// net loading dialog
    *//**
     * 布局文件id
     *//*
    private int resLayout;


    *//**
     * 设置fragment关联布局
     *
     * @param resLayout 关联布局id
     *//*
    public void setLayoutRes(int resLayout) {
        this.resLayout = resLayout;
    }

    *//**
     * 初始数据 在数据初始化时调用setLayoutRes(int resLayout)方法设置fragment对应的layout资源文件
     *//*
    protected abstract void init();

    *//**
     * 控件初始化
     *
     * @param rootview 在控件初始化前设置layout布局文件id ,
     *//*
    protected abstract void initView(View rootview);

    *//**
     * 设置控件属性
     *//*
    protected abstract void setViewData();

    *//**
     * 初始化控件事件
     *//*
    protected abstract void initEvent();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        try {
            this.inflater = inflater;
            View view = inflater.inflate(resLayout, container, false);
            initView(view);
            setViewData();
            initEvent();
            return view;
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }

    }

    *//**
     * 首次加载数据
     *//*
    protected void firstLoadData() {
        if (isFrist) {
            // showDialog(getActivity(), message);
            isFrist = false;
            getData();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // zjDataManager = new ZJDataManager(getActivity());
        // downLoadManager = new DLJDataDownLoadHelper(getActivity(), handler);

        utils = MyUtils.getInstance();
        init();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        animation = null;
        System.gc();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        firstLoadData();
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    *//**
     * 初次获取数据，必须在init()方法里把isFirst设置为true
     *//*
    protected abstract void getData();

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }
*/



}
